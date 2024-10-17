package com.example.Shopzon.Exception;

public class SellerDuplicateEntryFoundException extends RuntimeException{
    public SellerDuplicateEntryFoundException(String message){
        super(message);
    };
}
