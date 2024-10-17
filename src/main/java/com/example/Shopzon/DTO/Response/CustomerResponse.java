package com.example.Shopzon.DTO.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {

    String name;
    String email;
    String message;
}
