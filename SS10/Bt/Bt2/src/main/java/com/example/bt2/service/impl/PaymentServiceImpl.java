package com.example.bt2.service.impl;

import com.example.bt2.model.dto.request.PaymentRequest;
import com.example.bt2.model.entity.Order;
import com.example.bt2.model.entity.User;
import com.example.bt2.model.entity.UserAccount;
import com.example.bt2.repository.OrderRepository;
import com.example.bt2.repository.UserAccountRepository;
import com.example.bt2.repository.UserRepository;
import com.example.bt2.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final UserRepository userRepository;
    private final UserAccountRepository accountRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public String pay(PaymentRequest request) {
        try {
            if (request.getAmount() == 9999) {
                throw new RuntimeException("Đứt kết nối DB");
            }

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            Order order = orderRepository.findById(request.getOrderId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));

            UserAccount account = accountRepository.findByUserId(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản thanh toán"));

            if (!order.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("Đơn hàng không thuộc về người dùng này");
            }

            if ("PAID".equals(order.getStatus())) {
                throw new RuntimeException("Đơn hàng đã được thanh toán");
            }

            if (account.getBalance() < request.getAmount()) {
                throw new RuntimeException("Số dư không đủ để thanh toán");
            }

            account.setBalance(account.getBalance() - request.getAmount());
            order.setStatus("PAID");

            accountRepository.save(account);
            orderRepository.save(order);

            log.info("Thanh toán thành công: userId={}, orderId={}, amount={}",
                    request.getUserId(),
                    request.getOrderId(),
                    request.getAmount());

            return "Thanh toán thành công";

        } catch (RuntimeException e) {
            if ("Đứt kết nối DB".equals(e.getMessage())) {
                log.error("Lỗi hệ thống khi thanh toán", e);
            } else {
                log.warn("Thanh toán thất bại: {}", e.getMessage());
            }

            throw e;
        }
    }
}
