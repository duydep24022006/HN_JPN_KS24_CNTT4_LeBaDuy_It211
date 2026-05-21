Input/Output:
Input là các tham số đầu vào của method, 
cụ thể là otp kiểu String có thể là null, chuỗi rỗng, hoặc chuỗi OTP hợp lệ. 
Output là một String trả về thông báo thành công hoặc Exception nếu OTP không hợp lệ.

Tại sao dùng Custom Annotation thay vì Pointcut quét tên hàm:
Nếu dùng Pointcut quét theo tên hàm kiểu execution(* *.withdraw(..)) 
thì Aspect chỉ hoạt động đúng khi tên hàm khớp đúng pattern đã đặt ra. 
Khi thêm hàm mới tên khác như sendMoney hay payBill thì phải nhớ vào 
sửa lại Pointcut, rất dễ bỏ sót và không có gì nhắc nhở cả.

Còn nếu dùng Custom Annotation @RequiresOTP thì chỉ cần đánh dấu 
annotation lên hàm nào cần bảo mật là xong. Tên hàm đặt gì cũng được,
Aspect sẽ tự động nhận ra. Việc bảo mật hay không được thể hiện rõ 
ràng ngay tại chỗ khai báo hàm, dễ đọc và không lo bỏ sót.