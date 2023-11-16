package com.example.Neo_store111.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    private String uniqueCode;
}
