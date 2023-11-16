package com.example.Neo_store111.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Name;
import lombok.*;

import java.util.List;

@Data
@ToString @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Name("product_id")
    private Long productId;

    @NotNull
    @Column(unique = true, nullable = false, name = "product_name")
    private String productName;

    @NotNull
    @Column(nullable = false, name = "product_price")
    private Double productPrice;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Column
    private int quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeOfProduct typeOfProduct;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}