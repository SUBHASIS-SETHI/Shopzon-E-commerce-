package com.example.Shopzon.Transformer;

import com.example.Shopzon.DTO.Response.OrderEntityResponse;
import com.example.Shopzon.DTO.Response.ProductResponse;
import com.example.Shopzon.Enum.OrderStatus;
import com.example.Shopzon.Model.OrderEntity;
import com.example.Shopzon.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderEntityTransformer {
    public static OrderEntity orderEntityRequestToOrderEntity(double totalValue){
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .orderStatus(OrderStatus.PLACED)
                .totalValue(totalValue)
                .build();
    }
    public static OrderEntityResponse orderEntityToOrderResponse(OrderEntity orderEntity){
        OrderEntityResponse orderEntityResponse= OrderEntityResponse.builder()
                .orderNo(orderEntity.getOrderNo())
                .orderedAt(orderEntity.getOrderAt())
                .orderStatus(orderEntity.getOrderStatus())
                .totalValue(orderEntity.getTotalValue())
                .customer(CustomerTransformer.customerToCustomerResponse(orderEntity.getCustomer()))
                .build();

        List<ProductResponse> productResponseList=new ArrayList<>();

        for(Product product:orderEntity.getProductList()){
            productResponseList.add(ProductTransformer.productToProductResponse(product));
        }
        orderEntityResponse.setProducts(productResponseList);
        return orderEntityResponse;

    }
}
