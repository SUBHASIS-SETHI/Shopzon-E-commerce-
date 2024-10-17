package com.example.Shopzon.Transformer;

import com.example.Shopzon.DTO.Request.CustomerRequest;
import com.example.Shopzon.DTO.Response.CustomerResponse;
import com.example.Shopzon.Model.Customer;

public class CustomerTransformer {
    public static Customer customRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder().name(customerRequest.getName()).age(customerRequest.getAge()).gender(customerRequest.getGender()).email(customerRequest.getEmail()).build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer savedCustomer) {
        return CustomerResponse.builder()
                .name(savedCustomer.getName())
                .message("Customer was created on "+savedCustomer.getCreatedAt())
                .email(savedCustomer.getEmail()).build();
    }
}
