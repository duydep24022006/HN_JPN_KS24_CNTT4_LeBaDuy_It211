package com.example.bt1.digital.transaction.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public boolean processPayment(
            String accountNumber,
            double amount
    ) {

        System.out.println(
                "SERVICE: Đang xử lý thanh toán cho tài khoản "
                        + accountNumber
        );

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "SERVICE: Thanh toán thành công số tiền "
                        + amount
        );

        return true;
    }
}