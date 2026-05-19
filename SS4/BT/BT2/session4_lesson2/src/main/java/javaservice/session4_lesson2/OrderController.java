package javaservice.session4_lesson2;
//Phần 1 – Lý thuyết
//        So sánh POST và PUT:
//
//        Tiêu chí	POST	PUT
//        Mục đích	Tạo mới một tài nguyên trong hệ thống. Server sẽ sinh ra ID.	Cập nhật hoặc thay thế toàn bộ một tài nguyên đã tồn tại. Client thường phải biết ID trước.
//        Idempotent	Không idempotent: gọi nhiều lần sẽ tạo nhiều bản ghi khác nhau.	Idempotent: gọi nhiều lần với cùng dữ liệu sẽ cho cùng một kết quả (tài nguyên được cập nhật giống nhau).
//        Khi nào dùng	Khi client gửi dữ liệu để tạo mới (ví dụ: đơn hàng, user, bài viết).	Khi client muốn cập nhật hoặc thay thế một tài nguyên đã có sẵn (ví dụ: chỉnh sửa thông tin đơn hàng với ID đã biết).
//
//        Kết luận:
//        Trong tình huống này, hệ thống cần tạo đơn hàng mới và server sẽ tự sinh orderId. Đây là hành vi POST chứ không phải PUT. Vì vậy, đồng nghiệp B đúng: phải dùng POST /api/v1/orders.
//


import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    static class Order {
        private String orderId;
        private String customerName;
        private String address;
        private String productId;
        private int quantity;

        public Order(String orderId, String customerName, String address, String productId, int quantity) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.address = address;
            this.productId = productId;
            this.quantity = quantity;
        }
        public String getOrderId() { return orderId; }
        public void setOrderId(String orderId) { this.orderId = orderId; }

        public String getCustomerName() { return customerName; }
        public void setCustomerName(String customerName) { this.customerName = customerName; }

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    @PostMapping
    public Order createOrder(@RequestBody Order orderRequest) {
        // Sinh orderId tự động bằng UUID
        String generatedId = UUID.randomUUID().toString();
        Order newOrder = new Order(
                generatedId,
                orderRequest.getCustomerName(),
                orderRequest.getAddress(),
                orderRequest.getProductId(),
                orderRequest.getQuantity()
        );
        return newOrder;
    }
}