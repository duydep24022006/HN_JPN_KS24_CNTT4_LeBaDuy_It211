# Phân tích: Thiết lập Logback đa môi trường

- Môi trường Dev dùng để lập trình và kiểm thử, nên log cần in trực tiếp ra Console để lập trình viên dễ quan sát lỗi ngay khi chạy ứng dụng.

- Ở môi trường Dev, mức log DEBUG phù hợp vì cần xem nhiều thông tin chi tiết phục vụ quá trình sửa lỗi và kiểm tra luồng xử lý.

- Môi trường Prod là môi trường chạy thật cho người dùng, nên log cần được lưu ra file để có thể truy vết lỗi sau khi hệ thống gặp sự cố hoặc server khởi động lại.

- Ở môi trường Prod, mức log INFO phù hợp hơn DEBUG vì chỉ cần ghi nhận thông tin quan trọng, tránh sinh quá nhiều log gây nặng hệ thống.

- Prod cần cơ chế RollingFileAppender để tự động cắt file khi log vượt 10MB và giữ tối đa 30 ngày, tránh làm đầy ổ cứng server.