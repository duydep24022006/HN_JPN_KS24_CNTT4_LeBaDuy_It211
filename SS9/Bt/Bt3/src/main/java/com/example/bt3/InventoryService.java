package com.example.bt3;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InventoryService {
    public void updateStock(String productId,int qty){
        log.info("Bắt đầu cập nhât kho cho sản phẩm: {}, số lượng: {}", productId, qty);
    }
}
