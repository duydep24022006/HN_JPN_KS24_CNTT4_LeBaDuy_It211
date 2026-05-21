package org.example.bai3.controller;

import org.example.bai3.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam double amount, @RequestParam String otp) {
        return ResponseEntity.ok(transactionService.withdraw(amount, otp));
    }

    @GetMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String toUser, @RequestParam double amount, @RequestParam String otp) {
        return ResponseEntity.ok(transactionService.transfer(toUser, amount, otp));
    }

    @GetMapping("/balance")
    public ResponseEntity<String> balance() {
        return ResponseEntity.ok(transactionService.getBalance());
    }
}
