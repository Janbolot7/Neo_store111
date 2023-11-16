package com.example.Neo_store111.model;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "type_of_product")
public class TypeOfProduct {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Name("type_id")
        private Long typeId;

        @Column(unique = true, name = "category_name")
        private String typeName;

        @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private List<Product> products;
}
