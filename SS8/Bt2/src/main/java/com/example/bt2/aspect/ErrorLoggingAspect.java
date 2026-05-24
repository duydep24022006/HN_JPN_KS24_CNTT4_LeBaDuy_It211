package com.example.bt2.aspect;

import com.example.bt2.model.entity.ErrorLog;
import com.example.bt2.repository.ErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class ErrorLoggingAspect {

    private final ErrorLogRepository errorLogRepository;

    @AfterThrowing(
            pointcut = """
            execution(* com.example.bt2.service.*.*(..))
            """,
            throwing = "ex"
    )
    public void logError(
            JoinPoint joinPoint,
            Exception ex
    ) {

        ErrorLog errorLog = ErrorLog.builder()
                .timestamp(LocalDateTime.now())
                .methodName(
                        joinPoint.getSignature().getName()
                )
                .exceptionMessage(ex.getMessage())
                .build();

        errorLogRepository.save(errorLog);
    }
}
