package com.example.Shopzon.DTO.Response;

import com.example.Shopzon.Enum.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntityResponse {
    String orderNo;  // UUID

    double totalValue;

    OrderStatus orderStatus;

    Date orderedAt;

    CustomerResponse customer;

    List<ProductResponse> products;
}
