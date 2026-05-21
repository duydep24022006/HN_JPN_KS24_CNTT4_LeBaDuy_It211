# Phân tích vấn đề

## 1. Bối cảnh

Trong hệ thống ngân hàng số, các phương thức xử lý giao dịch cần được đo thời gian thực thi để:
- Theo dõi hiệu năng hệ thống.
- Phát hiện bottleneck.
- Kiểm tra các method xử lý chậm.

Hiện tại việc đo thời gian đang được viết thủ công trong từng method bằng:

```java
long startTime = System.currentTimeMillis();

...

long endTime = System.currentTimeMillis();

System.out.println(
    "LOG: processPayment chạy trong "
    + (endTime - startTime)
    + "ms"
);
```

---

# 2. Các vấn đề của cách làm hiện tại

## 2.1 Code bị lặp lại

Đoạn code đo thời gian phải viết lại ở nhiều method khác nhau như:
- processPayment()
- transferMoney()
- withdraw()
- deposit()

Điều này làm code bị trùng lặp và khó bảo trì.

---

## 2.2 Vi phạm nguyên lý SRP

Theo nguyên lý Single Responsibility Principle (SRP), một class chỉ nên có một trách nhiệm duy nhất.

Tuy nhiên `TransactionService` hiện tại đang:
- Xử lý nghiệp vụ giao dịch.
- Đo thời gian thực thi.
- Ghi log hiệu năng.

=> Class đang có nhiều trách nhiệm khác nhau.

---

## 2.3 Code Tangling

Business logic và logging logic bị trộn lẫn với nhau.

Điều này gây ra:
- Code khó đọc.
- Khó maintain.
- Khó mở rộng.
- Dễ phát sinh lỗi khi chỉnh sửa.

---

# 3. Giải pháp

Sử dụng Spring AOP để tách phần logging hiệu năng ra khỏi business logic.

Áp dụng:
- @Aspect
- @Around Advice

để tự động:
- Đo thời gian thực thi method.
- Ghi log tên method.
- In execution time.

---

# 4. Lợi ích của Spring AOP

Sau khi áp dụng AOP:
- Không cần viết logging thủ công.
- Service chỉ còn xử lý business logic.
- Dễ bảo trì.
- Dễ mở rộng.
- Có thể áp dụng cho toàn bộ package bằng Pointcut.

---

# 5. TransactionService sau khi refactor

```java
package com.bank.digital.transaction.service;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public boolean processPayment(
            String accountNumber,
            double amount
    ) {

        System.out.println(
            "SERVICE: Đang xử lý thanh toán cho tài khoản "
            + accountNumber
        );

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }
}
```

---

# 6. Aspect xử lý logging hiệu năng

## PerformanceLoggingAspect.java

```java
package com.bank.digital.transaction.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {

    @Around(
        "execution(* com.bank.digital.transaction.service..*(..))"
    )
    public Object logExecutionTime(
            ProceedingJoinPoint joinPoint
    ) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;

        System.out.println(
            "AOP LOG: Method "
            + joinPoint.getSignature().getName()
            + " chạy trong "
            + executionTime
            + "ms"
        );

        return result;
    }
}
```

---

# 7. Dependency cần thêm

## build.gradle

```gradle
implementation 'org.springframework.boot:spring-boot-starter-aop'
```

---

# 8. Kết quả khi chạy

```text
SERVICE: Đang xử lý thanh toán cho tài khoản 123456

AOP LOG: Method processPayment chạy trong 150ms
```

---

# 9. Kết luận

Spring AOP giúp:
- Tách biệt logging khỏi business logic.
- Giảm code duplication.
- Tuân thủ nguyên lý SRP.
- Tăng khả năng maintain và mở rộng hệ thống.