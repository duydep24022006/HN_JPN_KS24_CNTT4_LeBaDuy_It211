package com.example.bt1.digital.transaction.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {

    @Around(
            "execution(* com.example.bt1.digital.transaction.service..*(..))"

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