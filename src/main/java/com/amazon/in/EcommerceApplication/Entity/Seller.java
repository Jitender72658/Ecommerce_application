package com.amazon.in.EcommerceApplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String mobNo;

    @Column(unique = true,nullable = false)
    private String panNo;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
     List<Product> productList = new ArrayList<>();
}
