package com.example.bt1.service.impl;

import com.example.bt1.model.dto.request.CartItemRequest;
import com.example.bt1.model.entity.CartItem;
import com.example.bt1.repository.CartItemRepository;
import com.example.bt1.service.CartItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    @Override
    public CartItem addToCart(CartItemRequest cartItemRequest) {
        log.info("Bắt đầu thêm sản phẩm vào giỏ hàng userId:{}, productId:{}, quantity:{}", cartItemRequest.getUserId(), cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(cartItemRequest.getUserId(), cartItemRequest.getProductId()).map(item->
        {
            item.setQuantity(item.getQuantity()+cartItemRequest.getQuantity());
            log.info("Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng mới: {}", item.getQuantity());
            return item;
        }).orElseGet(()->{
            log.info("Sản phẩm chưa tồn tại trong giỏ hàng, tạo mới cart item");
            return CartItem.builder()
                    .userId(cartItemRequest.getUserId())
                    .productId(cartItemRequest.getProductId())
                    .quantity(cartItemRequest.getQuantity())
                    .build();
        });

        cartItemRepository.save(cartItem);
        log.info("Thêm sản phẩm vào giỏ hàng thành công, cartItemId: {}", cartItem.getId());
        return cartItem;
    }

    @Override
    public List<CartItem> getCartItemsByUserId(String userId) {
        log.info("Lấy danh sách sản phẩm trong giỏ hàng theo userId: {}", userId);
        return cartItemRepository.findByUserId(userId);
    }
}
