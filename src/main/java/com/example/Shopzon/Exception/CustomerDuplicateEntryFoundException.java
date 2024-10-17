package com.example.Shopzon.Exception;

public class CustomerDuplicateEntryFoundException extends RuntimeException{
    public CustomerDuplicateEntryFoundException(String message) {
        super(message);
    }
}
