package com.example.bt3.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class ManagerApprovalAspect {

    @Before("""
        @annotation(
            com.example.bt3.annotation.RequireManagerApproval
        )
    """)
    public void validateManagerRole() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();

        HttpServletRequest request =
                attributes.getRequest();

        String role = request.getHeader("X-Role");

        if (!"MANAGER".equalsIgnoreCase(role)) {
            throw new RuntimeException(
                    "Chỉ MANAGER mới được hoàn tiền"
            );
        }
    }
}
