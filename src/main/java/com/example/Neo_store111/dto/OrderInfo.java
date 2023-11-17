package com.example.Neo_store111.dto;

import com.example.Neo_store111.model.Enum.MethodOfPurchases;
import lombok.*;

import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private Long userId;
    private MethodOfPurchases methodOfPurchases;
    private String address;
    private double totalPrice;
}
