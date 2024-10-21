package com.example.Shopzon.DTO.Request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderEntityRequest {
    String customerEmail;
    List<ItemRequest> itemRequestList;
}
