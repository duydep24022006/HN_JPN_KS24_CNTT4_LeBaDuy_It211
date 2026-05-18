package com.example.bt4.controller;

import com.example.bt4.model.entity.SinhVien;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

public class SinhVienController {
@GetMapping(value = "/sinhvien",
produces = {
        MediaType.APPLICATION_ATOM_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE,
})
    public List<SinhVien> getSinhVien() {
    List<SinhVien> danhSach = Arrays.asList(
            new SinhVien(
            "SV001",
            "Nguyễn Văn A",
            8.5
    ),
    new SinhVien(
            "SV002",
            "Nguyễn Văn B",
            8.5
    ),
    new SinhVien(
            "SV003",
            "Nguyễn Văn C",
            8.5
    )
    );

    return danhSach;
    }
}
