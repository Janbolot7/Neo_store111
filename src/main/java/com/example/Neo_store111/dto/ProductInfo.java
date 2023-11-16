package com.example.Neo_store111.dto;

import com.example.Neo_store111.model.TypeOfProduct;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class ProductInfo {
    private String productName;
    private Double productPrice;
    private String description;
    private int quantity;
    private TypeOfProduct typeOfProduct;
}