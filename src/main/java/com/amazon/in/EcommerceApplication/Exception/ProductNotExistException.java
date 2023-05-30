package com.amazon.in.EcommerceApplication.Exception;

public class ProductNotExistException extends  Exception{
    public ProductNotExistException(String message){
        super(message);
    }
}
