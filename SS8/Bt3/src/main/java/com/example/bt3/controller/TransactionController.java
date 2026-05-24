package com.example.bt3.controller;

import com.example.bt3.model.dto.request.DomesticPaymentRequest;
import com.example.bt3.model.dto.request.InternationalPaymentRequest;
import com.example.bt3.model.dto.request.RefundRequest;
import com.example.bt3.model.entity.Transaction;
import com.example.bt3.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final PaymentService paymentService;

    @PostMapping("/domestic")
    public Transaction domesticPayment(
            @Valid @RequestBody
            DomesticPaymentRequest request
    ) {

        return paymentService.processDomesticPayment(
                request.getAmount(),
                request.getCurrency()
        );
    }
    @PostMapping("/international")
    public Transaction internationalPayment(
            @RequestHeader("X-OTP") String otp,
            @Valid @RequestBody
            InternationalPaymentRequest request
    ) {

        return paymentService.processInternationalPayment(
                request.getAmount(),
                request.getCurrency()
        );
    }
    @PostMapping("/refund")
    public Transaction refund(
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody
            RefundRequest request
    ) {

        return paymentService.processRefund(
                request.getTransactionCode(),
                request.getAmount()
        );
    }


}
