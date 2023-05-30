package com.amazon.in.EcommerceApplication.Service;

import com.amazon.in.EcommerceApplication.Convertor.OrderConvertor;
import com.amazon.in.EcommerceApplication.Enum.ProductStatus;
import com.amazon.in.EcommerceApplication.RequestDto.OrderRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;
import com.amazon.in.EcommerceApplication.ResponseDto.OrderResponseDto;
import com.amazon.in.EcommerceApplication.Entity.*;
import com.amazon.in.EcommerceApplication.Exception.CustomerNotExistException;
import com.amazon.in.EcommerceApplication.Exception.ProductNotExistException;
import com.amazon.in.EcommerceApplication.Repository.CustomerRepository;
import com.amazon.in.EcommerceApplication.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    JavaMailSender emailSender;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotExistException, ProductNotExistException,Exception{
        Customer customer;
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        }
        catch(Exception e){
            throw new CustomerNotExistException("Sorry, Invalid Customer id.");
        }

        Product product;
        try{
            product = productRepository.findById(orderRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotExistException("Sorry, Invalid Product Id.");
        }

        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new Exception("Sorry! Required quantity not available");
        }

        // creating an order
        Orders order = new Orders();
        int totalPrice = orderRequestDto.getRequiredQuantity()* product.getPrice();
        order.setTotalAmount(totalPrice);
        if(totalPrice<500){
            order.setDeliveryCharges(40);}
        else{
            order.setDeliveryCharges(0);
        }

        //  setting the masked card for order
        Card card = customer.getCards().get(0);
        String cardNo = "";
        for(int i=0;i<card.getCardNo().length()-4;i++)
            cardNo += 'X';
        cardNo += card.getCardNo().substring(card.getCardNo().length()-4);
        order.setCardUsedForPayment(cardNo);

        // creating an item

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrders(order);

        // adding item in the order list
        order.getItems().add(item);
        order.setCustomer(customer);

       // updating the product status

        int leftQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        if(leftQuantity<=0) {
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        product.setQuantity(leftQuantity);


        // updating the customer with new order
        customer.getOrders().add(order);
        Customer updatedCustomer = customerRepository.save(customer);
        Orders savedOrder = updatedCustomer.getOrders().get(updatedCustomer.getOrders().size()-1);

        List<Item > items = order.getItems();
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item1 : items){
            ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                    .productStatus(item1.getProduct().getProductStatus())
                    .name(item1.getProduct().getName())
                    .price(item1.getProduct().getPrice())
                    .productCategory(item1.getProduct().getProductCategory())
                    .quantityRequired(item1.getRequiredQuantity())
                    .build() ;

        }

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderDate(savedOrder.getOrderDate())
                .cardUsedForPayment(cardNo)
                .totalCost(order.getTotalAmount())
                .itemList(itemResponseDtos)
                .deliveryCharge(savedOrder.getDeliveryCharges())
                .build();

        // sending an email
        String text = "Congrats! Your order with total value "+order.getTotalAmount()+" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("111111111@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placement");
        message.setText(text);
        emailSender.send(message);

        return orderResponseDto;

    }

    public List<OrderResponseDto> getAllOrdersOfACustomer( int customerId) throws CustomerNotExistException{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotExistException("Sorry, Invalid Customer id.");
        }
        List<Orders> ordersList = customer.getOrders();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();

        for(Orders order :ordersList){
           OrderResponseDto orderResponseDto = OrderConvertor.orderToOrderResponseDto(order);
             orderResponseDtoList.add(orderResponseDto);
        }
        return orderResponseDtoList;
    }
}
