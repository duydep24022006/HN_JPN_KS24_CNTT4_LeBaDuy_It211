package com.example.bt2.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
    private Long userId;
    private Double totalAmount;
}