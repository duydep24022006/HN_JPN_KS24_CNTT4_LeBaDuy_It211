package com.example.bt3.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomesticPaymentRequest {

    @NotNull(message = "Amount không được để trống")
    @Positive(message = "Amount phải lớn hơn 0")
    private Double amount;

    @NotBlank(message = "Currency không được để trống")
    private String currency;
}
