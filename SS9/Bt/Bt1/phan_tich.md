# Phân tích: Vì sao không dùng System.out.println() trên Production

- System.out.println() không lưu log lâu dài, khi server restart hoặc crash thì thông tin lỗi dễ bị mất, Dev khó truy vết nguyên nhân lỗi.

- System.out.println() không phân loại mức độ log như INFO, WARN, ERROR nên khó lọc log khi hệ thống chạy thật trên Production.

- System.out.println() không ghi đầy đủ stack trace và context lỗi theo chuẩn logging, gây khó khăn khi debug các lỗi phát sinh trong môi trường thực tế.