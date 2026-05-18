package com.example.bt1.controller;

import com.example.bt1.model.dto.request.ApiDataRepose;
import com.example.bt1.model.entity.Product;
import com.example.bt1.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiDataRepose<List<Product>>> getAllProducts() {
        return new ResponseEntity<>(new ApiDataRepose<>(
                true,
                "Lấy danh sách sản phẩm thành công",
                productService.getProducts(),
                HttpStatus.OK.value()
        ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiDataRepose<Product>> insertProduct(@RequestBody Product product) {
        return new ResponseEntity<>(new ApiDataRepose<>(
                true,
                "Thêm sản phẩm thành công",
                productService.insertProduct(product),
                HttpStatus.CREATED.value()
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataRepose<Product>> updateProduct(@PathVariable String id, @RequestBody Product product) {
        try {

            product.setId(id);

            return ResponseEntity.ok(
                    new ApiDataRepose<>(
                            true,
                            "Cập nhật sản phẩm thành công",
                            productService.updateProduct(product),
                            HttpStatus.OK.value()
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            new ApiDataRepose<>(
                                    false,
                                    e.getMessage(),
                                    null,
                                    HttpStatus.NOT_FOUND.value()
                            )
                    );
        }
    }


}

