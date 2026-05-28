package com.example.bt2.service.impl;

import com.example.bt2.model.dto.request.CreateAccountRequest;
import com.example.bt2.model.entity.User;
import com.example.bt2.model.entity.UserAccount;
import com.example.bt2.repository.UserAccountRepository;
import com.example.bt2.repository.UserRepository;
import com.example.bt2.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final UserAccountRepository accountRepository;

    @Override
    public UserAccount createAccount(CreateAccountRequest request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            UserAccount account = new UserAccount();
            account.setUser(user);
            account.setBalance(request.getBalance());

            log.info("Tạo tài khoản thanh toán thành công cho userId={}", request.getUserId());
            return accountRepository.save(account);

        } catch (RuntimeException e) {
            log.warn("Tạo tài khoản thất bại: {}", e.getMessage());
            throw e;
        }
    }
}
