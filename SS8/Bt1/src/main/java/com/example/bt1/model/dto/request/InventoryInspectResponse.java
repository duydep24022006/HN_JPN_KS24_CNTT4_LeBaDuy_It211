package com.example.bt1.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryInspectResponse {
    private Integer totalQuantity;

    private Double totalValue;
}
