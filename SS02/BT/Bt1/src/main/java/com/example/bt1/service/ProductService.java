package com.example.bt1.service;

import com.example.bt1.model.entity.Product;
import com.example.bt1.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product insertProduct(Product product);
    Product updateProduct(Product product);
    Product getProductById(String id);
    Boolean deleteProduct(String id);
}
