package org.example.bai3.service;

import org.example.bai3.annotation.RequiresOTP;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @RequiresOTP
    public String withdraw(double amount, String otp) {
        return "Rút tiền thành công";
    }

    @RequiresOTP
    public String transfer(String toUser, double amount, String otp) {
        return "Chuyển khoản thành công";
    }

    public String getBalance() {
        return "Số dư hiện tại: 1,000,000 VND";
    }
}
