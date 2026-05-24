package com.example.bt3.service.impl;


import com.example.bt3.annotation.RequireManagerApproval;
import com.example.bt3.annotation.RequireOtp;
import com.example.bt3.model.entity.Transaction;
import com.example.bt3.repository.TransactionRepository;
import com.example.bt3.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction processDomesticPayment(
            Double amount,
            String currency
    ) {

        Transaction transaction = Transaction.builder()
                .transactionCode(UUID.randomUUID().toString())
                .amount(amount)
                .currency(currency)
                .type("DOMESTIC")
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    @RequireOtp
    public Transaction processInternationalPayment(
            Double amount,
            String currency
    ) {

        Transaction transaction = Transaction.builder()
                .transactionCode(UUID.randomUUID().toString())
                .amount(amount)
                .currency(currency)
                .type("INTERNATIONAL")
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    @RequireManagerApproval
    public Transaction processRefund(
            String transactionCode,
            Double amount
    ) {

        Transaction transaction = Transaction.builder()
                .transactionCode(transactionCode)
                .amount(amount)
                .currency("REFUND")
                .type("REFUND")
                .build();

        return transactionRepository.save(transaction);
    }
}
