package com.example.Shopzon.Model;

import com.example.Shopzon.Enum.Category;
import com.example.Shopzon.Enum.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String productName;

    @Column(nullable = false)
    double price;


    @Enumerated(EnumType.STRING)
    Category category;

    @Column(nullable = false)
    int quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    Seller seller;

    @ManyToMany(mappedBy = "productList")
    @JsonIgnore
    List<OrderEntity> orderEntityList=new ArrayList<>();




}
