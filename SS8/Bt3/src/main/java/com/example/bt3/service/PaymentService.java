package com.example.bt3.service;

import com.example.bt3.model.entity.Transaction;

public interface PaymentService {
    Transaction processDomesticPayment(
            Double amount,
            String currency
    );

    Transaction processInternationalPayment(
            Double amount,
            String currency
    );

    Transaction processRefund(
            String transactionCode,
            Double amount
    );
}
