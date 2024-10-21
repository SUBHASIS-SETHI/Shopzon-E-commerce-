package com.example.Shopzon.Exception;

public class ProductNotAvailableException extends RuntimeException{
    public ProductNotAvailableException(String message){
        super(message);
    }
}
