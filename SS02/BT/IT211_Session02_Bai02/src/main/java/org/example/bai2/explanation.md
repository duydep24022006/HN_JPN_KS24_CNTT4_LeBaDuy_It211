- POST vs PUT và tính idempotent:
POST không có tính idempotent, gọi đi gọi lại nhiều lần sẽ tạo ra nhiều bản ghi khác nhau. 
PUT có tính idempotent, gọi bao nhiêu lần với cùng dữ liệu thì kết quả vẫn như nhau, 
chỉ cập nhật đúng một bản ghi duy nhất. 
Đây là lý do tại sao REST quy ước POST cho tạo mới, PUT cho cập nhật.


- Tại sao dùng POST cho cả hai lại gây trùng lặp:
Trong code hiện tại, khi client gửi POST kèm ID nhưng ID đó không 
tìm thấy trong danh sách (ví dụ do race condition khi bấm quá nhanh, hoặc ID bị sai),
hệ thống rơi vào nhánh else và tạo ra một bản ghi mới với ID đó thay vì báo lỗi. 
Kết quả là cùng một khách hàng có thể tồn tại nhiều lần trong danh sách với các ID khác nhau.