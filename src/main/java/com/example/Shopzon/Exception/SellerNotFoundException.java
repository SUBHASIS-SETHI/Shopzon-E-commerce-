package com.example.Shopzon.Exception;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(String message){
        super(message);
    }
}
