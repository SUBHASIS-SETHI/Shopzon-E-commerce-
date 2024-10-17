package com.example.Shopzon.DTO.Request;

import com.example.Shopzon.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    String name;

    int age;

    String email;

    Gender gender;
}
