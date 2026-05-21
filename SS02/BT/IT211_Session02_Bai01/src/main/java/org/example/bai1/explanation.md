Nguyên nhân gốc rễ nằm ở chỗ phương thức getHotProducts() 
khai báo kiểu trả về là String và dùng products.toString() 
để trả dữ liệu. Trong Java, gọi .toString() trên một List 
không tạo ra JSON — nó chỉ tạo ra một chuỗi ký tự 
là địa chỉ bộ nhớ của object, hoàn toàn vô nghĩa với client. 
Spring Boot chỉ tự động chuyển đổi sang JSON khi bạn trả về trực tiếp 
một object hoặc List Java — nếu trả về String, nó coi đó là văn bản thô 
và gửi nguyên xi, không xử lý thêm gì. Vì vậy dù các sản phẩm đã được 
thêm vào danh sách đầy đủ, phía client vẫn không nhận được các trường 
id, name, price mà chỉ nhận về một chuỗi rác không thể parse được.