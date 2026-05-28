package com.example.bt1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlashSaleService {

    private static final Logger logger = LoggerFactory.getLogger(FlashSaleService.class);

    public void applyDiscount(String userId,String code){
        logger.info("Đang xử lý mã: {} cho user: {}", code, userId);
        try {
            // ... logic
            logger.info("Thành công! userId={}, code={}", userId, code);
        } catch (Exception e) {
            logger.error("Lỗi khi áp dụng mã giảm giá. userId={}, code={}", userId, code, e);
        }
    }
}
