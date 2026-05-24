package com.example.bt1.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {
    @NotBlank(message = "SKU không được để trống")
    private String sku;
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity ;
}
