# Phân tích: Phân loại Log Levels

- Thành công: dùng INFO  
  Vì đây là hành vi bình thường của hệ thống, cần ghi nhận để theo dõi lịch sử xử lý.

- Người dùng nhập sai mã hoặc mã hết hạn: dùng WARN  
  Vì đây là lỗi nghiệp vụ do dữ liệu người dùng nhập vào, hệ thống vẫn chạy bình thường, không phải lỗi nghiêm trọng.

- Lỗi chết logic hoặc lỗi database: dùng ERROR  
  Vì đây là lỗi hệ thống nghiêm trọng, cần Dev kiểm tra và sửa gấp.