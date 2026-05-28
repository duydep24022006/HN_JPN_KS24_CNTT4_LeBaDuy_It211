package com.example.bt1.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiDataResponse <T>{
    private String success;
    private String message;
    private String error;
    private T data;
    private HttpStatus HttpStatus;
}
