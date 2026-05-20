package com.example.kt45p.controller;

import com.example.kt45p.model.dto.response.ApiDataResponse;
import com.example.kt45p.model.entity.Product;
import com.example.kt45p.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<Page<Product>>> getProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size
    ) {

        return new ResponseEntity<>(
                ApiDataResponse.<Page<Product>>builder()
                        .success(true)
                        .message("Lấy danh sách sản phẩm thành công")
                        .data(productService.getProducts(page, size))
                        .httpStatus(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<?>> getProductById(@PathVariable Long id) {

        try {

            Product product = productService.getProductById(id);

            return new ResponseEntity<>(
                    ApiDataResponse.builder()
                            .success(true)
                            .message("Lấy sản phẩm thành công")
                            .data(product)
                            .httpStatus(HttpStatus.OK)
                            .build(),
                    HttpStatus.OK
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>(
                    ApiDataResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .data(null)
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .build(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Product>> createProduct(
            @RequestBody Product product
    ) {

        return new ResponseEntity<>(
                ApiDataResponse.<Product>builder()
                        .success(true)
                        .message("Tạo sản phẩm thành công")
                        .data(productService.createProduct(product))
                        .httpStatus(HttpStatus.CREATED)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Product>> updateProduct(
            @PathVariable Long id,
            @RequestBody Product product
    ) {

        return new ResponseEntity<>(
                ApiDataResponse.<Product>builder()
                        .success(true)
                        .message("Cập nhật sản phẩm thành công")
                        .data(productService.updateProduct(id, product))
                        .httpStatus(HttpStatus.OK)
                        .build(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Void>> deleteProduct(
            @PathVariable Long id
    ) {

        boolean deleted = productService.deleteProduct(id);

        if (deleted) {
            return new ResponseEntity<>(
                    ApiDataResponse.<Void>builder()
                            .success(true)
                            .message("Xóa sản phẩm thành công")
                            .data(null)
                            .httpStatus(HttpStatus.NO_CONTENT)
                            .build(),
                    HttpStatus.NO_CONTENT
            );
        }

        return new ResponseEntity<>(
                ApiDataResponse.<Void>builder()
                        .success(false)
                        .message("Sản phẩm không tồn tại")
                        .data(null)
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
}