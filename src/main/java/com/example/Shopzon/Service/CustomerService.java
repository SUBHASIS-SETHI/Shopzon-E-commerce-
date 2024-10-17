package com.example.Shopzon.Service;

import com.example.Shopzon.DTO.Request.CustomerRequest;
import com.example.Shopzon.DTO.Response.CustomerResponse;
import com.example.Shopzon.Exception.CustomerDuplicateEntryFoundException;
import com.example.Shopzon.Exception.CustomerNotFoundException;
import com.example.Shopzon.Model.Customer;
import com.example.Shopzon.Repository.CustomerRepository;
import com.example.Shopzon.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer=customerRepository.findByEmail(customerRequest.getEmail());
        if(!optionalCustomer.isEmpty()) {
            throw new CustomerDuplicateEntryFoundException("Email already exists,Customer can't be added again");
        }
        Customer customer= CustomerTransformer.customRequestToCustomer(customerRequest);
        Customer savedCustomer=customerRepository.save(customer);
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse findByEmail(String email) {
            Optional<Customer> customerOptional=customerRepository.findByEmail(email);
            if(customerOptional.isEmpty()){
                throw new CustomerNotFoundException("Customer not found give valid details");
            }
            Customer customer=customerOptional.get();
            return CustomerTransformer.customerToCustomerResponse(customer);
    }
}
