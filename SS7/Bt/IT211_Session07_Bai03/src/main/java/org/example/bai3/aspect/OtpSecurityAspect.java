package org.example.bai3.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OtpSecurityAspect {

    @Before("@annotation(org.example.bai3.annotation.RequiresOTP)")
    public void checkOtp(JoinPoint joinPoint) {
        String otp = null;

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof String) {
                otp = (String) arg;
            }
        }

        if (otp == null || otp.trim().isEmpty()) {
            throw new RuntimeException("Giao dịch thất bại: OTP không được để trống.");
        }

        if (!otp.equals("123456")) {
            throw new RuntimeException("Giao dịch thất bại: OTP không hợp lệ.");
        }
    }
}
