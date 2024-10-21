package com.example.Shopzon.Model;

import com.example.Shopzon.Enum.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String orderNo;//random uuid

    @Column(nullable = false)
    double totalValue;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @CreationTimestamp
    Date orderAt;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Customer customer;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name="product_orders",
            joinColumns = @JoinColumn(name="order_entity_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    List<Product> productList = new ArrayList<>();



}
