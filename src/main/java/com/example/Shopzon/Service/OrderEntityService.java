package com.example.Shopzon.Service;

import com.example.Shopzon.Model.OrderEntity;
import com.example.Shopzon.Repository.OrderEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class OrderEntityService {
    @Autowired
    OrderEntityRepository orderEntityRepository;

    public OrderEntity addOrder(OrderEntity orderEntity) {
          OrderEntity savedOrderEntity=orderEntityRepository.save(orderEntity);
          return savedOrderEntity;
    }
}
