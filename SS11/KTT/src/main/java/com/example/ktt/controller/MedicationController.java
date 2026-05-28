package com.example.ktt.controller;

import com.example.ktt.model.dto.response.ApiResponse;
import com.example.ktt.model.entity.Medication;
import com.example.ktt.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/medication")
@RequiredArgsConstructor
@Slf4j
public class MedicationController {
    private final MedicationService medicationService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Medication>>> getPageMedication(
            @RequestParam(required = false) String keyword,
            Pageable pageable){

        return
    }
}
