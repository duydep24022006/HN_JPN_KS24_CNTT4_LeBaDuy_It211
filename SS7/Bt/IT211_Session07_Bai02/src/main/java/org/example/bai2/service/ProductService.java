package org.example.bai2.service;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public void addProduct(String productName) {
        System.out.println("SERVICE: Đã thêm sản phẩm " + productName);
    }
}
