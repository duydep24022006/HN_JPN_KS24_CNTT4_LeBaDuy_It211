package com.example.bt2.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketRequest {

    @NotBlank(message = "Mã chuyến bay không được để trống")
    private String flightNumber;

    @NotBlank(message = "Tên hành khách không được để trống")
    private String passengerName;
}
