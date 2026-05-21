Lý do mà việc chỉ in log trong @Before không thể ngăn phương
thức gốc chạy là vì bản chất của @Before Advice chỉ cho phép
chèn thêm code chạy trước method gốc, nhưng sau khi code trong
@Before chạy xong thì Spring sẽ luôn luôn tiếp tục gọi method
gốc bất kể bên trong @Before có viết gì. Nói đơn giản hơn,
@Before không có khả năng "dừng lại" hay "hủy bỏ" luồng chạy, 
đây chỉ là bước chạy thêm trước mà thôi.

Muốn thực sự chặn được thì bắt buộc phải ném ra một Exception bên trong @Before.
Khi một Exception được ném ra thì JVM sẽ dừng luồng chạy lại ngay lập tức 
và method gốc sẽ không bao giờ được gọi đến.