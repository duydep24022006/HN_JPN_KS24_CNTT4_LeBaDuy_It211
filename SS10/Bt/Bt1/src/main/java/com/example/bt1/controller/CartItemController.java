package com.example.bt1.controller;

import com.example.bt1.model.dto.request.CartItemRequest;
import com.example.bt1.model.dto.response.ApiDataResponse;
import com.example.bt1.model.entity.CartItem;
import com.example.bt1.service.CartItemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<List<CartItem>>> addToCart(@Valid @RequestBody CartItemRequest cartItemRequest) {
        log.info("Nhận yêu cầu thêm sản phẩm vào giỏ hàng: userId:{}, productId:{}, quantity:{}", cartItemRequest.getUserId(), cartItemRequest.getProductId(), cartItemRequest.getQuantity());
        CartItem cartItem = cartItemService.addToCart(cartItemRequest);
        log.info("Sản phẩm đã được thêm vào giỏ hàng thành công, cartItemId: {}", cartItem.getId());
        return ResponseEntity.ok(ApiDataResponse.<List<CartItem>>builder()
                .success("true")
                .message("Thêm sản phẩm vào giỏ hàng thành công")
                .data(cartItemService.getCartItemsByUserId(cartItemRequest.getUserId()))
                .build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiDataResponse<List<CartItem>>> getCartItemsByUserId(@PathVariable String userId) {
        log.info("Nhận yêu cầu lấy danh sách sản phẩm trong giỏ hàng theo userId: {}", userId);
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
        log.info("Lấy danh sách sản phẩm trong giỏ hàng thành công, số lượng sản phẩm: {}", cartItems.size());
        return ResponseEntity.ok(ApiDataResponse.<List<CartItem>>builder()
                .success("true")
                .message("Lấy danh sách sản phẩm trong giỏ hàng thành công")
                .data(cartItems)
                .build());
    }
}
