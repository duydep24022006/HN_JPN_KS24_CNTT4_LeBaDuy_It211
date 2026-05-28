# Phân tích: Vì sao Parameterized Logging tối ưu hơn dấu +

- Khi dùng dấu +, Java sẽ nối chuỗi trước khi logger kiểm tra level log có được bật hay không, gây tốn CPU và bộ nhớ không cần thiết.

- Với Parameterized Logging sử dụng {}, dữ liệu chỉ được format khi log level thực sự được ghi ra file hoặc console.

- Parameterized Logging giúp code ngắn gọn, dễ đọc và đúng chuẩn logging hiện đại.