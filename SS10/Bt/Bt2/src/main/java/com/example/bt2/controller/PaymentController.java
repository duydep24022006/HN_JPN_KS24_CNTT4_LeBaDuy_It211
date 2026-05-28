package com.example.bt2.controller;

import com.example.bt2.model.dto.request.PaymentRequest;
import com.example.bt2.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/pay")
    public String pay(@RequestBody PaymentRequest request) {
        return paymentService.pay(request);
    }
}
