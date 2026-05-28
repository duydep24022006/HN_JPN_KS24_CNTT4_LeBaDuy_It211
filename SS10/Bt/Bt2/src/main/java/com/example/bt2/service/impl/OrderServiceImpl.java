package com.example.bt2.service.impl;

import com.example.bt2.model.dto.request.CreateOrderRequest;
import com.example.bt2.model.entity.Order;
import com.example.bt2.model.entity.User;
import com.example.bt2.repository.OrderRepository;
import com.example.bt2.repository.UserRepository;
import com.example.bt2.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;


    @Override
    public Order createOrder(CreateOrderRequest request) {
        try {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            Order order = new Order();
            order.setUser(user);
            order.setTotalAmount(request.getTotalAmount());
            order.setStatus("PENDING");

            log.info("Tạo đơn hàng thành công cho userId={}, totalAmount={}",
                    request.getUserId(),
                    request.getTotalAmount());

            return orderRepository.save(order);

        } catch (RuntimeException e) {
            log.warn("Tạo đơn hàng thất bại: {}", e.getMessage());
            throw e;
        }
    }
}
