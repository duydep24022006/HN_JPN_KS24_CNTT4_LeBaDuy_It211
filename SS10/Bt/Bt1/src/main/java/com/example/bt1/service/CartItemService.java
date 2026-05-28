package com.example.bt1.service;

import com.example.bt1.model.dto.request.CartItemRequest;
import com.example.bt1.model.entity.CartItem;

import java.util.List;

public interface CartItemService {
    CartItem addToCart(CartItemRequest cartItemRequest);
    List<CartItem> getCartItemsByUserId(String userId);
}
