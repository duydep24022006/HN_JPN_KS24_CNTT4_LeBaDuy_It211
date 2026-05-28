package com.example.bt3.service;

import com.example.bt3.model.dto.request.ProductRequest;
import com.example.bt3.model.entity.Product;

public interface ProductService {
    Product createProduct(ProductRequest request);
}
