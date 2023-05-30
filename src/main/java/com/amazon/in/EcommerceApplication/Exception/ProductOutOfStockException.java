package com.amazon.in.EcommerceApplication.Exception;

public class ProductOutOfStockException  extends Exception{
    public ProductOutOfStockException(String message){
        super(message);
    }
}
