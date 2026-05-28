package com.example.bt3.controller;

import com.example.bt3.model.dto.request.ProductRequest;
import com.example.bt3.model.entity.Product;
import com.example.bt3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }
}
