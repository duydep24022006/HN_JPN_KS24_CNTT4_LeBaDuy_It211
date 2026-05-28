package com.example.bt3.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
    private String sku;
    private Long quantity;
    private Long keeperId;
}
