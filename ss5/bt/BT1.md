# [Bài tập 1]: Nguyên tắc cơ bản của REST

## 1. REST yêu cầu dùng danh từ (số nhiều) cho URL, không dùng động từ. Đúng hay sai?

→ Đúng.

Trong REST API, URL đại diện cho tài nguyên nên thường dùng danh từ số nhiều thay vì động từ.

### Ví dụ đúng:
- `/students`
- `/products`

### Ví dụ sai:
- `/getStudents`
- `/createProduct`

---

# 2. Ví dụ đúng và sai cho API quản lý sinh viên

## Ví dụ đúng
- `GET /students`
- `POST /students`

## Ví dụ sai
- `GET /getAllStudents`
- `POST /createStudent`

---

# 3. Các phương thức HTTP dùng để làm gì?

| Method | Mục đích chính | Có body không? (thường) |
|---|---|---|
| GET | Lấy dữ liệu | Không |
| POST | Tạo mới dữ liệu | Có |
| PUT | Cập nhật toàn bộ dữ liệu | Có |
| PATCH | Cập nhật một phần dữ liệu | Có |
| DELETE | Xóa dữ liệu | Không |

---

# 4. Phân biệt PUT và PATCH

Giả sử có sản phẩm:

```json
{
  "id": 1,
  "name": "Bút bi",
  "price": 5000
}
```

## Nếu dùng PUT /products/1

Body:

```json
{
  "name": "Bút mực"
}
```

### Kết quả:

```json
{
  "id": 1,
  "name": "Bút mực",
  "price": null
}
```

### Giải thích:
PUT cập nhật toàn bộ dữ liệu nên field `price` không gửi lên sẽ bị mất hoặc null.

---

## Nếu dùng PATCH /products/1

Body:

```json
{
  "name": "Bút mực"
}
```

### Kết quả:

```json
{
  "id": 1,
  "name": "Bút mực",
  "price": 5000
}
```

### Giải thích:
PATCH chỉ cập nhật field được gửi lên nên `price` vẫn giữ nguyên.

---

# 5. HTTP Status Codes

| Mã | Ý nghĩa | Tình huống ví dụ |
|---|---|---|
| 200 | OK – Thành công | Lấy danh sách sinh viên thành công |
| 201 | Created – Tạo mới thành công | Thêm sinh viên mới thành công |
| 204 | No Content – Thành công nhưng không có dữ liệu trả về | Xóa sinh viên thành công |
| 400 | Bad Request – Request sai dữ liệu | Thiếu field bắt buộc |
| 404 | Not Found – Không tìm thấy tài nguyên | Không tìm thấy sinh viên id=10 |
| 500 | Internal Server Error – Lỗi server | Lỗi kết nối database |