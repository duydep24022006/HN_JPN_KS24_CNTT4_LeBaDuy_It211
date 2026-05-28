package com.example.bt1.repository;

import com.example.bt1.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    Optional<CartItem> findByUserIdAndProductId(String userId, String productId);
    List<CartItem> findByUserId(String userId);
}
