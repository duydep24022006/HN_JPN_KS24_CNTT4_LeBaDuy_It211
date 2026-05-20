package com.example.bt3.service;

import com.example.bt3.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    boolean deleteProduct(Long id);

}
