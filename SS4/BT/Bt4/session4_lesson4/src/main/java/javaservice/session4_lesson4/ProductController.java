package javaservice.session4_lesson4;
//Sai HTTP Method Annotation
//
//Đang dùng @PostMapping cho cập nhật.
//
//Theo chuẩn RESTful, cập nhật toàn bộ tài nguyên phải dùng PUT (@PutMapping).
//
//POST chỉ dành cho tạo mới.
//
//Sai ý nghĩa URL
//
//Endpoint /products/{productId} với @PostMapping ngụ ý tạo mới một sản phẩm tại ID cụ thể, nhưng đó không hợp lý vì ID thường do hệ thống sinh ra.
//
//Với cập nhật, ta phải dùng PUT /products/{productId} để thay thế thông tin sản phẩm có ID đó.
//
//        Ngoài ra, còn một vấn đề tiềm ẩn: nếu productId không tồn tại, cần xử lý trả về lỗi thay vì cập nhật.
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    static class Product {
        private String productId;
        private String name;
        private double price;

        public Product(String productId, String name, double price) {
            this.productId = productId;
            this.name = name;
            this.price = price;
        }

        // Getters & setters
        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }

    // Giả lập database bằng Map
    private final Map<String, Product> productStore = new HashMap<>();

    // PUT: Cập nhật sản phẩm theo ID
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable String productId,
                                           @RequestBody Product updatedProduct) {
        if (!productStore.containsKey(productId)) {
            return ResponseEntity.status(404).body("Product with ID " + productId + " not found");
        }
        // Cập nhật toàn bộ thông tin
        updatedProduct.setProductId(productId);
        productStore.put(productId, updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE: Xóa sản phẩm theo ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        if (!productStore.containsKey(productId)) {
            return ResponseEntity.status(404).body("Product with ID " + productId + " not found");
        }
        productStore.remove(productId);
        return ResponseEntity.ok("Product with ID " + productId + " deleted successfully");
    }
}
