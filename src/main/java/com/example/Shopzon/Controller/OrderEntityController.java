package com.example.Shopzon.Controller;

import com.example.Shopzon.Model.OrderEntity;
import com.example.Shopzon.Service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderEntityController {
    @Autowired
    OrderEntityService orderEntityService;
    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody OrderEntity orderEntity){
        OrderEntity savedOrderEntity= orderEntityService.addOrder(orderEntity);
        return new ResponseEntity<>(savedOrderEntity, HttpStatus.CREATED);
    }
}
