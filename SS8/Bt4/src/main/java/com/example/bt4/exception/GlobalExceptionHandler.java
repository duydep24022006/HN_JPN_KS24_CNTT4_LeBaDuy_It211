package com.example.bt4.exception;

import jakarta.validation.ConstraintViolationException;
import org.aspectj.lang.JoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<?> handleNotFound(

            ResourceNotFoundException ex
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("status", 404);

        response.put("error", "Not Found");

        response.put(
                "message",
                ex.getMessage()
        );

        response.put(
                "timestamp",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    @ExceptionHandler(
            AccessDeniedException.class
    )
    public ResponseEntity<?> handleAccessDenied(

            AccessDeniedException ex
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("status", 403);

        response.put("error", "Forbidden");

        response.put(
                "message",
                ex.getMessage()
        );

        response.put(
                "timestamp",
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }

    @ExceptionHandler(
            ConstraintViolationException.class
    )
    public ResponseEntity<?> handleValidation(

            ConstraintViolationException ex
    ) {

        Map<String, Object> response =
                new HashMap<>();

        response.put("status", 400);

        response.put("error", "Bad Request");

        response.put(
                "message",
                ex.getMessage()
        );

        response.put(
                "timestamp",
                LocalDateTime.now()
        );

        return ResponseEntity
                .badRequest()
                .body(response);
    }
    public void logException(

            JoinPoint joinPoint,

            Exception ex
    ) {

        String className =
                joinPoint
                        .getTarget()
                        .getClass()
                        .getSimpleName();

        String methodName =
                joinPoint
                        .getSignature()
                        .getName();

        System.out.println(
                "[SECURITY LOG]"
        );

        System.out.println(
                "Class: " + className
        );

        System.out.println(
                "Method: " + methodName
        );

        System.out.println(
                "Error: " + ex.getMessage()
        );
    }
}

