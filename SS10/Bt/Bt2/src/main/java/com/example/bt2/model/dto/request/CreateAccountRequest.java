package com.example.bt2.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountRequest {
    private Long userId;
    private Double balance;
}
