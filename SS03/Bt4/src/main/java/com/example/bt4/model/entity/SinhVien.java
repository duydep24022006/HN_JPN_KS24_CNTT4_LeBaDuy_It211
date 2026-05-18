package com.example.bt4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SinhVien {
    private String maSV;

    private String hoTen;
    private double diemTB;

}
