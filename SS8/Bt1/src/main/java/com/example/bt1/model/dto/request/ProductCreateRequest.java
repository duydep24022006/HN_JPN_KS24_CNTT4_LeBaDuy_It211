package com.example.bt1.model.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {
    @NotBlank(message = "SKU không được để trống")
    private String sku;

    @NotBlank(message = "Name không được để trống")
    private String name;

    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private Integer quantity ;

    @NotNull(message = "Price không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private Double price;

}
