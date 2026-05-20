# [Bài tập 2]: Thiết kế REST API hệ thống mượn sách

## I. Mục tiêu

Thiết kế các endpoint RESTful cho chức năng quản lý Sách (Book) và Thẻ mượn (Loan).

- Book gồm:
  - id
  - title
  - author
  - year
  - quantity

- Loan gồm:
  - id
  - bookId
  - borrowerName
  - borrowDate
  - returnDate

---

# II. Thiết kế REST API

| Chức năng | Method | URL | Query params (nếu có) | Status thành công | Status lỗi (ví dụ) |
|---|---|---|---|---|---|
| Lấy danh sách sách | GET | `/books` | `?page=1&limit=10&author=...` | 200 OK | 400 Bad Request |
| Lấy chi tiết sách theo id | GET | `/books/{id}` | Không | 200 OK | 404 Not Found |
| Thêm sách mới | POST | `/books` | Không | 201 Created | 400 Bad Request |
| Cập nhật toàn bộ sách | PUT | `/books/{id}` | Không | 200 OK | 404 Not Found |
| Cập nhật số lượng sách | PATCH | `/books/{id}` | Không | 200 OK | 400 Bad Request |
| Xóa sách | DELETE | `/books/{id}` | Không | 204 No Content | 404 Not Found |
| Lấy danh sách thẻ mượn của một sách | GET | `/books/{id}/loans` | `?page=1&limit=5` | 200 OK | 404 Not Found |
| Tạo thẻ mượn mới | POST | `/loans` | Không | 201 Created | 400 Bad Request |
| Lấy thông tin thẻ mượn | GET | `/loans/{id}` | Không | 200 OK | 404 Not Found |
| Trả sách (cập nhật ngày trả) | PATCH | `/loans/{id}` | Không | 200 OK | 404 Not Found |
| Xóa thẻ mượn | DELETE | `/loans/{id}` | Không | 204 No Content | 404 Not Found |

---

# III. Ví dụ sử dụng API

## 1. Lấy tất cả sách của tác giả Nguyễn Nhật Ánh

```http
GET /books?author=Nguyễn Nhật Ánh
```

---

## 2. Thêm sách mới

```http
POST /books
```

Body:

```json
{
  "title": "Dế Mèn Phiêu Lưu Ký",
  "author": "Tô Hoài",
  "year": 1941,
  "quantity": 20
}
```

---

## 3. Cập nhật số lượng sách

```http
PATCH /books/1
```

Body:

```json
{
  "quantity": 15
}
```

---

## 4. Tạo thẻ mượn

```http
POST /loans
```

Body:

```json
{
  "bookId": 1,
  "borrowerName": "Nguyễn Văn A",
  "borrowDate": "2026-05-20"
}
```

---

## 5. Trả sách

```http
PATCH /loans/1
```

Body:

```json
{
  "returnDate": "2026-05-30"
}
```