package com.example.bt3.controller;

import com.example.bt3.model.entity.Product;
import com.example.bt3.model.response.ApiResponse;
import com.example.bt3.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> response = ApiResponse.<List<Product>>builder()
                .success(true)
                .message("Products retrieved successfully")
                .data(products)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product retrieved successfully")
                .data(product)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(Product product) {
        Product createdProduct = productService.createProduct(product);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product created successfully")
                .data(createdProduct)
                .build();
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable Long id, Product product)
    {
        Product updatedProduct = productService.updateProduct(id, product);
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product updated successfully")
                .data(updatedProduct)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(deleted)
                .message(deleted ? "Product deleted successfully" : "Product not found")
                .build();
        return ResponseEntity.ok(response);
    }

}
