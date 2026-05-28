package com.example.ktt.model.dto.request;

import com.example.ktt.model.entity.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicationRequest {
    @NotBlank(message = "Không đc bỏ trống tên thuốc")
    private String name;
    @NotBlank(message = "Không đc bỏ trống nhà sản xuất")
    private String manufacturer;
    @NotNull(message = "Không đc bỏ trống giá tiền")
    @Min(value = 0,message = "Không đc để giá tiền thấp hơn 0đ")
    private Double price;
    @NotNull(message = "Không đc bỏ trống trạng thái bán hàng của đơn thuốc")
    private Status status;
}
