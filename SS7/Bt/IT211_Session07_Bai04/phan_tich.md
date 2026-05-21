@RestControllerAdvice hoạt động ở tầng Web,
tức là chỉ bắt được exception từ Controller trở ra.
Có khả năng can thiệp hoàn toàn vào HTTP Response, 
tức là tùy chỉnh status code, body JSON thoải mái. 
Không phù hợp để ghi log vì đây là tầng trả response cho client.
@AfterThrowing hoạt động ở tầng Service, 
bắt được exception ngay tại nơi nó xảy ra trước khi nó nổi lên Controller.
Không có khả năng can thiệp vào HTTP Response vì không nằm ở tầng Web.
Rất phù hợp để ghi log bí mật vì chạy ngầm hoàn toàn tách biệt khỏi logic nghiệp vụ.

Lựa chọn:
Dùng @RestControllerAdvice để xử lý HTTP Response trả JSON chuẩn về cho client. Dùng @AfterThrowing để ghi audit log nội bộ ở tầng Service.