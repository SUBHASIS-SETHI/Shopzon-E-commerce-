package com.example.Shopzon.Controller;

import com.example.Shopzon.DTO.Request.ProductRequest;
import com.example.Shopzon.DTO.Response.ProductResponse;
import com.example.Shopzon.Exception.SellerNotFoundException;
import com.example.Shopzon.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest, @RequestParam("q") String sellerEmail) {

        try {
            ProductResponse productResponse=productService.addProduct(productRequest,sellerEmail);
            return new ResponseEntity(productResponse,HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
