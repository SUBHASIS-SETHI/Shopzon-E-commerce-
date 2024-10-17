package com.example.Shopzon.Transformer;

import com.example.Shopzon.DTO.Request.ProductRequest;
import com.example.Shopzon.DTO.Response.ProductResponse;
import com.example.Shopzon.Enum.ProductStatus;
import com.example.Shopzon.Model.Product;

public class ProductTransformer {
    public static Product productRequestToProduct(ProductRequest productRequest){
       return Product.builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .category(productRequest.getCategory())
                .productStatus(ProductStatus.AVAILABLE)
               .build();
    }
    public static ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .category(product.getCategory())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .seller(SellerTransformer.sellerToSellerResponse(product.getSeller()))
                .build();
    }

}
