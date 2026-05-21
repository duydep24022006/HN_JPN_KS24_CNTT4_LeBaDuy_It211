package com.example.demo.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {
    @NotBlank(message = "Họ tên không được để trống")
    private String fullName;
    @NotNull(message = "Giới tính không được để trống")
    private Boolean gender;
    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate birthday;
    @NotNull(message = "Địa chỉ không được để trống")
    private String address;
}
