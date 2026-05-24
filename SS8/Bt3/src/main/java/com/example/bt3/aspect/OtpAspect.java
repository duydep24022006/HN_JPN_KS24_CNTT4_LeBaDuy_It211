package com.example.bt3.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class OtpAspect {

    @Before("""
        @annotation(
            com.example.bt3.annotation.RequireOtp
        )
    """)
    public void validateOtp() {

        ServletRequestAttributes attributes =
                (ServletRequestAttributes)
                        RequestContextHolder
                                .getRequestAttributes();

        HttpServletRequest request =
                attributes.getRequest();

        String otp = request.getHeader("X-OTP");

        if (!"123456".equals(otp)) {
            throw new RuntimeException(
                    "OTP không hợp lệ"
            );
        }
    }
}
