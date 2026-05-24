package com.example.bt3.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequest {

    @Pattern(
            regexp = "^[A-Z0-9]+$",
            message = "TransactionCode không hợp lệ"
    )
    private String transactionCode;

    @NotNull(message = "Amount không được để trống")
    @Positive(message = "Amount phải lớn hơn 0")
    private Double amount;
}