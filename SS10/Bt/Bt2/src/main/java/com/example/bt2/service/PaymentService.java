package com.example.bt2.service;

import com.example.bt2.model.dto.request.PaymentRequest;

public interface PaymentService {
    String pay(PaymentRequest request);
}
