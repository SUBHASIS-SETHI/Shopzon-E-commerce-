package com.example.Shopzon.DTO.Response;

import jakarta.persistence.Column;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SellerResponse {
    String name;
    String email;
    String panNo;
}
