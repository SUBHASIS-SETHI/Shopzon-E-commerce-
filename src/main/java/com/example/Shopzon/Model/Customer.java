package com.example.Shopzon.Model;

import com.example.Shopzon.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(unique = true,nullable = false)
    String email;

    @CreationTimestamp
    Date createdAt;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orderEntityList=new ArrayList<>();


}
