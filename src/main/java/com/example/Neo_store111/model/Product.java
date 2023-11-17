package com.example.Neo_store111.model;

import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;
}
