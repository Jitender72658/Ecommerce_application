package com.amazon.in.EcommerceApplication.Convertor;

import com.amazon.in.EcommerceApplication.Entity.Customer;
import com.amazon.in.EcommerceApplication.RequestDto.CustomerRequestDto;
import com.amazon.in.EcommerceApplication.ResponseDto.CustomerResponseDto;


public class CustomerConvertor {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        Customer customer = Customer.builder()
                .age(customerRequestDto.getAge())
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .email(customerRequestDto.getEmail())
                .build();
        return customer;
    }
    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer ){
        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .mobNo(customer.getMobNo())
                .build();
        return customerResponseDto;
    }
}
