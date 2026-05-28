package com.example.ktt.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {
    private String success;
    private String message;
    private T data;
    private HttpStatus httpStatus;
}
