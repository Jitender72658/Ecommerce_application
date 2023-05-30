package com.amazon.in.EcommerceApplication.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int totalAmount;
    private String cardUsedForPayment;

    private int deliveryCharges;

    @CreationTimestamp
    private Date orderDate;
    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
