package com.example.Shopzon.DTO.Request;

import com.example.Shopzon.Enum.Category;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {

    String productName;


    double price;



    Category category;


    int quantity;
}
