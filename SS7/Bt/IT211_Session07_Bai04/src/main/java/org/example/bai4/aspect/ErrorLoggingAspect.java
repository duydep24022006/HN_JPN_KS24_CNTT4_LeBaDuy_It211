package org.example.bai4.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.example.bai4.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorLoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* org.example.bai4.service.*.*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint joinPoint, ResourceNotFoundException ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[INTERNAL LOG] Lỗi tại method: " + methodName);
        System.out.println("[INTERNAL LOG] Chi tiết: " + ex.getMessage());
    }
}
