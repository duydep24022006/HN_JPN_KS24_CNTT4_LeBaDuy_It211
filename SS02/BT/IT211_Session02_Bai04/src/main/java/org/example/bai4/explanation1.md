So sánh theo từng tiêu chí:
Hiệu suất:

SOAP dùng XML, file nặng hơn, xử lý chậm hơn
REST dùng JSON, nhẹ và nhanh hơn nhiều
Microservices phục vụ hàng ngàn người dùng cùng lúc thì REST rõ ràng hơn

Bảo mật:

SOAP có WS-Security built-in — mã hóa, chữ ký số ngay trong message, chuẩn quốc tế
REST dùng HTTPS + JWT/OAuth2 — bảo mật tốt nhưng phải tự setup
Ngân hàng cần chuẩn bảo mật nghiêm ngặt thì SOAP phù hợp hơn

Độ phức tạp:

SOAP phức tạp — phải viết WSDL, generate code, nhiều bước hơn
REST đơn giản — viết endpoint, test bằng Postman là xong
Team mới, deadline gấp thì REST dễ làm hơn nhiều

Tốc độ phát triển:

SOAP chậm hơn vì có nhiều quy tắc cần tuân theo
REST nhanh hơn, linh hoạt hơn, thêm tính năng mới dễ dàng
Microservices cần deploy liên tục thì REST phù hợp

Khả năng tích hợp:

SOAP tích hợp tốt với các hệ thống cũ, enterprise, không phụ thuộc HTTP
REST tích hợp tốt với mọi thứ hiện đại — mobile, web, third-party API


Kết luận:

Legacy Core Banking nên dùng SOAP vì cần bảo mật cao, giao dịch phân tán, tuân thủ chuẩn quốc tế
Microservices nên dùng REST vì cần nhanh, nhẹ, dễ mở rộng, dễ tích hợp với app mobile/web