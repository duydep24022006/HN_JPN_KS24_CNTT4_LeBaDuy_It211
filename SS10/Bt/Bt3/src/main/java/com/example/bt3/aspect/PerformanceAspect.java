package com.example.bt3.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("execution(* com.example.bt3.service.InventoryService.*(..))")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long time = System.currentTimeMillis() - start;
            String methodName = joinPoint.getSignature().getName();

            log.info("Hàm {} chạy mất {} ms", methodName, time);

            if (time > 500) {
                log.warn("[Performance Alert] Hàm {} quá chậm ({} ms)", methodName, time);
            }

            return result;

        } catch (Throwable e) {
            long time = System.currentTimeMillis() - start;
            String methodName = joinPoint.getSignature().getName();

            log.warn("Hàm {} lỗi sau {} ms: {}", methodName, time, e.getMessage());

            throw e;
        }
    }
}