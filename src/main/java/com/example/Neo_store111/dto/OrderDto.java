package com.example.Neo_store111.dto;

import com.example.Neo_store111.model.Enum.MethodOfPurchases;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class OrderDto {
    private Long userId;
    private double totalPrice;
    private String address;
    private MethodOfPurchases methodOfPurchases;
    private List<OrderDetailsDto> details;
}
