package com.example.bt2.controller;

import com.example.bt2.model.dto.request.CreateAccountRequest;
import com.example.bt2.model.entity.UserAccount;
import com.example.bt2.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public UserAccount createAccount(@RequestBody CreateAccountRequest request) {
        return accountService.createAccount(request);
    }
}
