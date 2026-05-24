package com.example.bt1.controller;

import com.example.bt1.model.dto.request.InventoryInspectResponse;
import com.example.bt1.model.dto.request.ProductCreateRequest;
import com.example.bt1.model.dto.request.StockRequest;
import com.example.bt1.model.entity.Product;
import com.example.bt1.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> listProducts(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role) {
        return productService.listProducts(username, role);
    }
    @PostMapping
    public Product createProduct(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody ProductCreateRequest request
            ) {
        return productService.createProduct(request, username, role);
    }

    @PostMapping("/stock-in")
    public String stockIn(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequest request
    ){
        productService.stockIn(request.getSku(), request.getQuantity(), username, role);
        return "Nhập kho thành công";
    }

    @PostMapping("/stock-out")
    public String stockOut(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role,
            @Valid @RequestBody StockRequest request) {
   productService.stockOut(request.getSku(), request.getQuantity(), username, role);
        return "Xuất kho thành công";
    }

    @GetMapping("/inspect")
    public InventoryInspectResponse inspect(
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role) {
        return productService.inspect(username, role);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id,
            @RequestHeader("X-User") String username,
            @RequestHeader("X-Role") String role) {
        productService.deleteProduct(id, username, role);
        return "Xóa sản phẩm thành công";
    }

}
