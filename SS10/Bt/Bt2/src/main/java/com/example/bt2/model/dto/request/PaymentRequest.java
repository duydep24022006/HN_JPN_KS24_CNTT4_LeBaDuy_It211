package com.example.bt2.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Long orderId;
    private Long userId;
    private Double amount;
}
