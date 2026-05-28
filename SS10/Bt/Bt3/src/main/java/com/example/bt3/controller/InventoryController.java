package com.example.bt3.controller;

import com.example.bt3.model.dto.request.InventoryRequest;
import com.example.bt3.model.entity.Product;
import com.example.bt3.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/import")
    public Product importProduct(@RequestBody InventoryRequest request) {
        return inventoryService.importProduct(request);
    }

    @PostMapping("/export")
    public String exportProduct(@RequestBody InventoryRequest request) {
        return inventoryService.exportProduct(request);
    }

    @GetMapping("/low-stock")
    public List<Product> lowStock() {
        return inventoryService.lowStock();
    }
}
