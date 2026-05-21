Đề xuất kiến trúc cho hệ thống tài chính
1. Legacy Core Banking → SOAP

Hệ thống ngân hàng lõi xử lý chuyển tiền, thanh toán — sai một bước là mất tiền thật, nên cần giao thức có độ tin cậy cao nhất
SOAP hỗ trợ WS-AtomicTransaction — đảm bảo một giao dịch gồm nhiều bước (trừ tiền A, cộng tiền B) phải thành công toàn bộ hoặc hủy toàn bộ, không bao giờ bị dở dang
WS-Security cho phép mã hóa và ký số ngay trong nội dung message, không chỉ ở tầng truyền tải — đáp ứng các tiêu chuẩn tài chính quốc tế như ISO 20022
WSDL định nghĩa rõ ràng interface — cả hai hệ thống đều biết chính xác phải gửi và nhận gì, giảm thiểu lỗi tích hợp
Hệ thống legacy thường không chạy trên HTTP thuần — SOAP hỗ trợ nhiều giao thức truyền tải khác như SMTP, JMS

2. Microservices (Mobile/Web) → REST

Ứng dụng mobile cần load nhanh — JSON nhẹ hơn XML nhiều lần, tiết kiệm băng thông, phù hợp người dùng dùng 4G/5G
Stateless nghĩa là mỗi request độc lập — server không cần nhớ trạng thái, dễ dàng thêm server mới khi lượng người dùng tăng đột biến
Developer mới có thể học và viết REST API trong vài ngày, không cần hiểu sâu về WSDL hay XML schema
Dễ test, dễ debug — chỉ cần Postman hoặc browser là kiểm tra được ngay
Hầu hết các thư viện, framework hiện đại (React, Flutter, Swift) đều hỗ trợ REST rất tốt


Glossary – Các khái niệm cốt lõi
Thuật ngữ
Giải thích đơn giản
SOAP: Giao thức web service dùng XML, có cấu trúc chặt chẽ
REST: Kiểu thiết kế API dùng HTTP, thường trả về JSON
WSDL: File mô tả SOAP service — giống như "menu" liệt kê các chức năng có sẵn
WS-Security: Chuẩn bảo mật của SOAP — mã hóa và ký số trong message
WS-AtomicTransaction: Cơ chế đảm bảo giao dịch phân tán thành công toàn bộ hoặc rollback toàn bộ
JSON: Định dạng dữ liệu nhẹ, dễ đọc, dùng phổ biến trong REST
XML: Định dạng dữ liệu có cấu trúc chặt chẽ hơn, dùng trong SOAP
Stateless: Mỗi request tự chứa đủ thông tin, server không lưu trạng thái
Stateful: Server nhớ trạng thái giữa các request
HTTP Methods: GET, POST, PUT, DELETE — các "động từ" của REST
Endpoint: Địa chỉ URL của một API cụ thể
Content Negotiation: Client tự chọn muốn nhận JSON hay XML qua header Accept