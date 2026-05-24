package com.example.bt1.service;


import com.example.bt1.model.dto.request.InventoryInspectResponse;
import com.example.bt1.model.dto.request.ProductCreateRequest;
import com.example.bt1.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductBySku(String sku, String username, String role);
    List<Product> listProducts(String username, String role);
    Product createProduct(ProductCreateRequest request, String username, String role);

    void stockIn(String sku, Integer quantity, String username, String role);

    void stockOut(String sku, Integer quantity, String username, String role);

    InventoryInspectResponse inspect(String username, String role);

    void deleteProduct(Long id, String username, String role);
}
