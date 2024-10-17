package com.example.Shopzon.DTO.Response;

import com.example.Shopzon.Enum.Category;
import com.example.Shopzon.Enum.ProductStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    String productName;

    double price;

    Category category;

    int quantity;

    ProductStatus productStatus;

    SellerResponse seller;

}
