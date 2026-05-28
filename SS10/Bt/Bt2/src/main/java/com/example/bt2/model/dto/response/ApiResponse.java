package com.example.bt2.model.dto.response;

import org.springframework.http.HttpStatus;

public class ApiResponse <T>{
    private String success;
    private String message;
    private T data;
    private HttpStatus status;
}
