package com.example.Shopzon.Controller;

import com.example.Shopzon.DTO.Request.CustomerRequest;
import com.example.Shopzon.DTO.Response.CustomerResponse;
import com.example.Shopzon.Exception.CustomerDuplicateEntryFoundException;
import com.example.Shopzon.Exception.CustomerNotFoundException;
import com.example.Shopzon.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
   @PostMapping("/add")
   public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
     try {
         CustomerResponse savedCustomer = customerService.addCustomer(customerRequest);
         return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
     }
     catch(CustomerDuplicateEntryFoundException e){//if duplicate email found
         return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
      }
    }
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity findById(@PathVariable String email){
       try{
           CustomerResponse customerResponse=customerService.findByEmail(email);
           return new ResponseEntity<>(customerResponse,HttpStatus.OK);
       }catch(CustomerNotFoundException e){//if customer not found
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }
}
