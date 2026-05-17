BÀI 1 - GỬI DỮ LIỆU KHÁCH HÀNG: POST HAY PUT?
1. Bối cảnh nghiệp vụ

Hệ thống ngân hàng điện tử cần hỗ trợ:

Tạo mới khách hàng.
Cập nhật thông tin khách hàng.
Đảm bảo không bị trùng lặp dữ liệu.
Đảm bảo dữ liệu luôn chính xác và nhất quán.

Trong hệ thống hiện tại, API đang sử dụng POST cho cả tạo mới và cập nhật dữ liệu khách hàng. Điều này dẫn đến việc khi client gửi request cập nhật nhưng ID không tồn tại, hệ thống lại tạo thêm bản ghi mới thay vì báo lỗi.

2. Phân tích HTTP POST và HTTP PUT
   2.1. HTTP POST

POST được sử dụng để tạo mới tài nguyên.

Ví dụ:

POST /api/customers

POST thường:

tạo dữ liệu mới
sinh ID mới
không idempotent
Idempotent là gì?

Idempotent nghĩa là:

"Gửi nhiều request giống nhau thì kết quả cuối cùng vẫn như nhau"

POST không idempotent.

Ví dụ:

Gửi 2 request POST giống nhau:

{
"name": "Nguyen Van A"
}

có thể tạo ra:

Customer 1
Customer 2

=> bị trùng dữ liệu.

2.2. HTTP PUT

PUT được dùng để cập nhật tài nguyên đã tồn tại.

Ví dụ:

PUT /api/customers/1

PUT là idempotent.

Ví dụ:

Gửi nhiều request PUT giống nhau:

{
"name": "Nguyen Van A"
}

thì dữ liệu cuối cùng vẫn chỉ là:

{
"id": 1,
"name": "Nguyen Van A"
}

không tạo thêm bản ghi mới.

3. Vấn đề của hệ thống hiện tại

Hệ thống đang dùng POST cho cả create và update.

Logic hiện tại:

if (customer.getId() == null) {
// create
} else {
// update
}

Nếu ID không tồn tại:

customers.add(customer);

=> hệ thống lại tạo mới dữ liệu.

Điều này gây:

trùng lặp dữ liệu
mất tính nhất quán
lỗi nghiệp vụ
khó kiểm soát dữ liệu khách hàng
4. Giải pháp

Tách API thành:

POST
POST /api/customers

chỉ dùng để tạo mới.

PUT
PUT /api/customers/{id}

chỉ dùng để cập nhật.

Nếu khách hàng không tồn tại:

404 NOT FOUND
5. HTTP Status Codes sử dụng
   Trường hợp	Status
   Tạo thành công	201 CREATED
   Cập nhật thành công	200 OK
   Không tìm thấy khách hàng	404 NOT FOUND
   Dữ liệu không hợp lệ	400 BAD REQUEST
6. Kết luận bài 1

Việc sử dụng đúng HTTP Method giúp:

tránh lỗi dữ liệu
đảm bảo RESTful API
tăng tính ổn định hệ thống
frontend dễ xử lý hơn
đảm bảo tính idempotent cho update

POST phù hợp cho tạo mới.

PUT phù hợp cho cập nhật.

============================================================

BÀI 2 - TỐI ƯU VÀ KIỂM SOÁT API QUẢN LÝ KHO HÀNG
1. Bối cảnh nghiệp vụ

Hệ thống quản lý kho hàng cần:

quản lý item
hỗ trợ CRUD
xử lý lỗi rõ ràng
hỗ trợ JSON và XML
đảm bảo client dễ tích hợp

Vấn đề hiện tại:

API trả null khi lỗi
không có status code rõ ràng
response không thống nhất
không hỗ trợ XML
2. Vai trò của HTTP Status Codes

HTTP Status Code giúp client biết chính xác kết quả request.

Ví dụ:

200 OK

Request thành công.

201 CREATED

Tạo dữ liệu thành công.

404 NOT FOUND

Không tìm thấy dữ liệu.

400 BAD REQUEST

Request không hợp lệ.

500 INTERNAL SERVER ERROR

Lỗi phía server.

3. Tại sao không nên trả về null?

Ví dụ:

return null;

Frontend sẽ không biết:

dữ liệu không tồn tại?
server lỗi?
request sai?

Điều này gây khó debug.

Nên trả:

ResponseEntity.notFound().build();

hoặc:

404 NOT FOUND

để client xử lý dễ dàng.

4. Content Negotiation là gì?

Content Negotiation cho phép client lựa chọn định dạng dữ liệu.

Ví dụ:

JSON
Accept: application/json
XML
Accept: application/xml

Server sẽ trả dữ liệu theo đúng format client yêu cầu.

5. Tại sao cần Jackson Dataformat XML?

Spring Boot mặc định hỗ trợ JSON.

Muốn hỗ trợ XML cần:

implementation 'com.fasterxml.jackson.dataformat:jackson-dataformat-xml'

Dependency này giúp:

convert object sang XML
parse XML request/response
hỗ trợ content negotiation
6. Thiết kế API
   Method	Endpoint	Chức năng
   GET	/api/v1/products	Lấy danh sách
   GET	/api/v1/products/{id}	Lấy theo ID
   POST	/api/v1/products	Tạo mới
   PUT	/api/v1/products/{id}	Cập nhật
   DELETE	/api/v1/products/{id}	Xóa
7. HTTP Status Codes áp dụng
   Trường hợp	Status
   GET thành công	200 OK
   POST thành công	201 CREATED
   PUT thành công	200 OK
   DELETE thành công	204 NO CONTENT
   Không tìm thấy dữ liệu	404 NOT FOUND
   Dữ liệu không hợp lệ	400 BAD REQUEST
8. ApiResponse Wrapper

Hệ thống sử dụng:

ApiResponse<T>

để đồng nhất response.

Ví dụ:

{
"success": true,
"message": "Lấy dữ liệu thành công",
"data": {
"id": "PR001"
},
"status": 200
}

Ưu điểm:

frontend dễ xử lý
API đồng nhất
dễ mở rộng
chuyên nghiệp hơn
9. Sử dụng Lombok

Hệ thống sử dụng Lombok:

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

Lợi ích:

giảm boilerplate code
code ngắn gọn
dễ maintain
phổ biến trong Spring Boot
10. Kết luận bài 2

Hệ thống API đã:

hỗ trợ JSON/XML
dùng HTTP Status Code chuẩn
xử lý lỗi rõ ràng
dùng ResponseEntity
hỗ trợ RESTful API
hỗ trợ Content Negotiation
sử dụng Lombok tối ưu code

============================================================

BÀI 3 - REST CHO MICROSERVICES HAY SOAP CHO LEGACY?
1. Bối cảnh nghiệp vụ

Công ty tài chính có 2 hệ thống:

Legacy Core Banking

Yêu cầu:

bảo mật cực cao
transaction phân tán
độ tin cậy cao
tuân thủ chuẩn tài chính
Microservices hiện đại

Yêu cầu:

hiệu suất cao
scalability
triển khai nhanh
hỗ trợ mobile/web
2. Tổng quan SOAP và REST
   Tiêu chí	SOAP	REST
   Kiểu	Protocol	Architectural Style
   Dữ liệu	XML	JSON/XML
   Hiệu suất	Chậm hơn	Nhanh hơn
   Bảo mật	Rất mạnh	Tốt
   Độ phức tạp	Cao	Thấp
   Khả năng mở rộng	Khó hơn	Tốt hơn
   Phù hợp	Enterprise	Web/Mobile
3. Phân tích SOAP
   Ưu điểm

SOAP hỗ trợ:

WS-Security
WS-ReliableMessaging
Distributed Transactions
XML chuẩn hóa

SOAP rất phù hợp cho:

ngân hàng
bảo hiểm
enterprise systems
Nhược điểm
XML nặng
xử lý chậm
khó phát triển
phức tạp hơn REST
4. Phân tích REST
   Ưu điểm

REST:

nhanh hơn SOAP
sử dụng JSON nhẹ
dễ scale
dễ tích hợp frontend
phù hợp microservices

REST phù hợp:

mobile app
web app
microservices
cloud systems
Nhược điểm
bảo mật không mạnh bằng SOAP
không hỗ trợ WS-* standards
5. So sánh theo nghiệp vụ
   Hiệu suất

REST tốt hơn do JSON nhẹ hơn XML.

Bảo mật

SOAP mạnh hơn nhờ WS-Security.

Độ phức tạp

REST đơn giản hơn.

Phát triển nhanh

REST linh hoạt hơn.

Khả năng tích hợp

REST phù hợp frontend hiện đại.

SOAP phù hợp hệ thống enterprise cũ.

6. Đề xuất kiến trúc
   Legacy Core Banking

Nên sử dụng SOAP.

Lý do:

bảo mật mạnh
transaction an toàn
reliability cao
phù hợp chuẩn tài chính
Microservices mới

Nên sử dụng REST.

Lý do:

hiệu suất cao
dễ scale
dễ deploy
phù hợp mobile/web
phát triển nhanh
7. Glossary
   SOAP

Simple Object Access Protocol.

REST

Representational State Transfer.

WSDL

Web Service Description Language.

JSON

JavaScript Object Notation.

XML

Extensible Markup Language.

Stateless

Server không lưu trạng thái giữa các request.

WS-Security

Chuẩn bảo mật cho SOAP.

8. Kết luận bài 3

Công ty nên sử dụng mô hình hybrid:

SOAP cho core banking
REST cho microservices

Điều này giúp:

tận dụng ưu điểm từng công nghệ
đảm bảo bảo mật
tối ưu hiệu suất
dễ mở rộng hệ thống
giảm chi phí phát triển

REST phù hợp hệ thống hiện đại.

SOAP phù hợp hệ thống enterprise truyền thống.