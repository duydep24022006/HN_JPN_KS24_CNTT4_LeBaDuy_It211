package com.example.bt2.controller;

import com.example.bt2.model.dto.request.CreateOrderRequest;
import com.example.bt2.model.entity.Order;
import com.example.bt2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
}