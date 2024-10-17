package com.example.Shopzon.Controller;

import com.example.Shopzon.DTO.Request.SellerRequest;
import com.example.Shopzon.DTO.Response.SellerResponse;
import com.example.Shopzon.Exception.SellerDuplicateEntryFoundException;
import com.example.Shopzon.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequest sellerRequest){
        try {
            SellerResponse savedSeller = sellerService.addSeller(sellerRequest);
            return new ResponseEntity<>(savedSeller,HttpStatus.CREATED);

        }catch(SellerDuplicateEntryFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
}
