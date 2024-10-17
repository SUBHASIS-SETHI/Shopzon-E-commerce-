package com.example.Shopzon.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;

    @Column(unique = true,nullable = false)
    String email;

    @Column(nullable = false)
    String panNo;

    @OneToMany(mappedBy = "seller",cascade =CascadeType.ALL)
    List<Product> productList=new ArrayList<>();
}
