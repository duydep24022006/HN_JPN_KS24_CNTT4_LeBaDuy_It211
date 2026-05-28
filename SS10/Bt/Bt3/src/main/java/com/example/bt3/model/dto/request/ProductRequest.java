package com.example.bt3.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private Long quantity;
    private String sku;
}