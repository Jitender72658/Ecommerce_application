package com.amazon.in.EcommerceApplication.Service;

import com.amazon.in.EcommerceApplication.Entity.*;
import com.amazon.in.EcommerceApplication.Enum.ProductStatus;
import com.amazon.in.EcommerceApplication.Repository.CartRepository;
import com.amazon.in.EcommerceApplication.ResponseDto.CartCheckoutResponseDto;
import com.amazon.in.EcommerceApplication.Exception.CustomerNotExistException;
import com.amazon.in.EcommerceApplication.Exception.ProductNotExistException;
import com.amazon.in.EcommerceApplication.Exception.ProductOutOfStockException;
import com.amazon.in.EcommerceApplication.Repository.CustomerRepository;
import com.amazon.in.EcommerceApplication.Repository.ItemRepository;
import com.amazon.in.EcommerceApplication.Repository.ProductRepository;
import com.amazon.in.EcommerceApplication.RequestDto.CartRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private JavaMailSender emailSender;

    public List<ItemResponseDto> addItemToCart(CartRequestDto cartRequestDto) throws CustomerNotExistException, ProductNotExistException, ProductOutOfStockException {
        Customer customer;
        try{
            customer = customerRepository.findById(cartRequestDto.getCustomerId()).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Sorry, Customer doesn't exist.");
        }

        Product product;
        try{
            product = productRepository.findById(cartRequestDto.getProductId()).get();
        }
        catch (Exception e){
            throw new ProductNotExistException("Sorry, Product doesn't exist.");
        }

        if(product.getQuantity()<cartRequestDto.getRequiredQuantity()){
            throw new ProductOutOfStockException("Sorry, Required quantity not available.");
        }

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+product.getPrice()*cartRequestDto.getRequiredQuantity());


        Item item = new Item();
        item.setRequiredQuantity(cartRequestDto.getRequiredQuantity());
        item.setProduct(product);
        cart.getItems().add(item);
        item.setCart(cart);
        customerRepository.save(customer);

         List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
         List<Item> items = cart.getItems();
         for(Item item1: items){
             ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                     .name(item1.getProduct().getName())
                     .price(item1.getProduct().getPrice())
                     .productCategory(item1.getProduct().getProductCategory())
                     .quantityRequired(item1.getRequiredQuantity())
                     .productStatus(item1.getProduct().getProductStatus())
                                               .build();
             itemResponseDtos.add(itemResponseDto);
         }

        return itemResponseDtos;
    }


    public CartCheckoutResponseDto checkoutCart(int customerId) throws CustomerNotExistException{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Sorry, Customer doesn't exist.");
        }

        Cart cart = customer.getCart();

        Orders order = new Orders();
        order.setTotalAmount(cart.getCartTotal());
        order.setCardUsedForPayment(customer.getCards().get(0).getCardNo());
        order.setCustomer(customer);
        order.setItems(cart.getItems());
        if(cart.getCartTotal()>499){
            order.setDeliveryCharges(0);
        }
        else{
            order.setDeliveryCharges(40);
        }

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        List<Item> items = cart.getItems();
        for(Item item1: items){
            ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                    .name(item1.getProduct().getName())
                    .price(item1.getProduct().getPrice())
                    .productCategory(item1.getProduct().getProductCategory())
                    .quantityRequired(item1.getRequiredQuantity())
                    .productStatus(item1.getProduct().getProductStatus())
                    .build();
            itemResponseDtos.add(itemResponseDto);
            itemRepository.findById(item1.getId()).get().setOrders(order);
            Product product = productRepository.findById(item1.getProduct().getId()).get();

            int remainingQuantity = product.getQuantity()-item1.getRequiredQuantity();
            product.setQuantity(remainingQuantity);
            if(remainingQuantity<=0) product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }



        List<Orders>  ordersList = customer.getOrders();
        ordersList.add(order);
        customer.setOrders(ordersList);


        CartCheckoutResponseDto cartCheckoutResponseDto = CartCheckoutResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .itemList(itemResponseDtos)
                .cardUsedForPayment(customer.getCards().get(0).getCardNo())
                .build();

        int totalPrice = cart.getCartTotal();
        cart.setCartTotal(0);
        List<Item> itemList = new ArrayList<>();
        cart.setItems(itemList);
        customerRepository.save(customer);

        String text = "Congrats your order with total value "+totalPrice+" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jyadav1512@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed from Amazon");
        message.setText(text);
        emailSender.send(message);

        return cartCheckoutResponseDto;
    }
}
