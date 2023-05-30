package com.amazon.in.EcommerceApplication.Exception;

public class CustomerNotExistException extends Exception{
    public CustomerNotExistException(String message){
        super(message);
    }
}
