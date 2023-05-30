package com.amazon.in.EcommerceApplication.Service;

import com.amazon.in.EcommerceApplication.Convertor.CustomerConvertor;
import com.amazon.in.EcommerceApplication.Entity.Cart;
import com.amazon.in.EcommerceApplication.Entity.Customer;
import com.amazon.in.EcommerceApplication.Exception.CustomerNotExistException;
import com.amazon.in.EcommerceApplication.Repository.*;
import com.amazon.in.EcommerceApplication.RequestDto.CustomerRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.CustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    JavaMailSender emailSender;
    public String addCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = CustomerConvertor.customerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();
        customer.setCart(cart);
        customerRepository.save(customer);

        String text = "Congrats Dear! your registration is successful.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("11111111@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Registration on ecommerce");
        message.setText(text);
        emailSender.send(message);
        return "Welcome to Amazon. Your signup is successful.";
    }
    public CustomerResponseDto getById(int customerId) throws CustomerNotExistException{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Invalid customer Id. Try again entering correct customer Id.");
        }
        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }
    public List<CustomerResponseDto> getAll(){
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        for(Customer customer: customers){
            CustomerResponseDto customerResponseDto = CustomerConvertor.customerToCustomerResponseDto(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }
    public CustomerResponseDto getByEmail(String email) throws CustomerNotExistException{
        Customer customer = customerRepository.findByEmail(email);
        if(customer==null){
            throw new CustomerNotExistException("Invalid email. No customer exist with this email. Try again entering correct email.");
        }
        return CustomerConvertor.customerToCustomerResponseDto(customer);
    }


    public String deleteById( int customerId) throws CustomerNotExistException{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch (Exception e){
            throw new CustomerNotExistException("Invalid customer Id. Try again entering correct customer Id.");
        }
            Cart cart = customer.getCart();

            itemRepository.removeItemFromData(cart.getId());


            orderRepository.removeItemFromData(customerId);


            cardRepository.deleteAllCards(customerId);


            customerRepository.deleteById(customerId);



        return "Customer has been deleted Successfully";
    }
}
