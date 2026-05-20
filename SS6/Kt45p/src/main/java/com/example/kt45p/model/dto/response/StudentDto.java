package com.example.kt45p.model.dto.response;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentDto {
        @NotBlank(message = "Tên không được để trống")
        private String fullName;

        @NotBlank(message = "Email không được để trống")
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email không hợp lệ")
        private String email;

        @NotNull(message = "Điểm trung bình không được để trống")
        @DecimalMin(value = "0.0", message = "Điểm trung bình phải là số dương")
        @DecimalMax(value = "4.0", message = "Điểm trung bình không được vượt quá 4.0")
        private Double gpa;

}
