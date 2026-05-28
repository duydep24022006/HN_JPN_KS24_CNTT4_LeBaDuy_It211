Bước 1: User A thêm sản phẩm vào giỏ

Sản phẩm:

Laptop X
Stock = 10
Price = 20.000.000

User A:

Add 5 sản phẩm vào cart

Giỏ hàng:

Laptop X - quantity = 5
Bước 2: User B mua hàng

User B checkout:

Mua 8 sản phẩm

Tồn kho mới:

Stock = 2
Bước 3: User A cập nhật giỏ hàng

User A muốn update:

5 -> 7

Nhưng tồn kho hiện tại:

Chỉ còn 2
Những lỗi tiềm ẩn nếu ShoppingCartService thiết kế sai
1. Overselling (Bán vượt tồn kho)

Nếu service chỉ kiểm tra:

newQuantity > oldQuantity

mà KHÔNG kiểm tra stock mới nhất từ database:

=> hệ thống cho phép:

Cart = 7
Stock thật = 2

=> checkout lỗi.

2. Race Condition

2 user cùng update cart đồng thời:

User A add 3
User B add 4
Stock = 5

Nếu không lock/validate:

=> tổng = 7 > stock.

3. Dữ liệu cart không đồng bộ product

Product bị xóa khỏi hệ thống nhưng cart vẫn giữ item:

productRepository.findById(id)
= Optional.empty()

Nếu service không xử lý:

=> NullPointerException.

4. Giá sản phẩm sai

Nếu cart lưu giá cũ:

Add cart lúc giá 100
Admin update giá = 150

Checkout vẫn tính 100.

=> mất doanh thu.

5. Quantity <= 0

Nếu cho phép:

quantity = 0
quantity = -5

=> cart sai logic.