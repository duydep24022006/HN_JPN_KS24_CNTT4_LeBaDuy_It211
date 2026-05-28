package com.example.bt2.service;

import com.example.bt2.model.dto.request.CreateOrderRequest;
import com.example.bt2.model.entity.Order;

public interface OrderService {
    Order createOrder(CreateOrderRequest request);
}
