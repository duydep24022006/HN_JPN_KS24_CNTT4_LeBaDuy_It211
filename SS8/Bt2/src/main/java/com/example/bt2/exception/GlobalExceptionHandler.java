package com.example.bt2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public Map<String,Object> handleRuntimeException(RuntimeException ex){
        Map<String,Object> response=new HashMap<>();
        response.put("success",false);
        response.put("message",ex.getMessage());
        return response;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,Object> response=new HashMap<>();
        response.put("success",false);
        String message=ex.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();
        response.put("message",message);
        return response;
    }
}
