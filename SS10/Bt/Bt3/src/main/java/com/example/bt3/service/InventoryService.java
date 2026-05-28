package com.example.bt3.service;

import com.example.bt3.model.dto.request.InventoryRequest;
import com.example.bt3.model.entity.Product;

import java.util.List;

public interface InventoryService {
    Product importProduct(InventoryRequest request);

    String exportProduct(InventoryRequest request);

    List<Product> lowStock();
}
