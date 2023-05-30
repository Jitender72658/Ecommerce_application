package com.amazon.in.EcommerceApplication.Exception;

public class ProductCategoryNotExistException extends  Exception{
    public ProductCategoryNotExistException(String message){
        super(message);
    }
}
