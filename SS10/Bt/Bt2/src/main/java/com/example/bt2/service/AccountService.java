package com.example.bt2.service;

import com.example.bt2.model.dto.request.CreateAccountRequest;
import com.example.bt2.model.entity.UserAccount;

public interface AccountService {
    UserAccount createAccount(CreateAccountRequest request);

}
