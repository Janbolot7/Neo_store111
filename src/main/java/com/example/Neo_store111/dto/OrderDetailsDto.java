package com.example.Neo_store111.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetailsDto {
    private int quantity;
    private Long productId;
    private double totalPrice;
}
