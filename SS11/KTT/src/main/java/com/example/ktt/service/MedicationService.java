package com.example.ktt.service;

import com.example.ktt.model.dto.request.MedicationRequest;
import com.example.ktt.model.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicationService {
    Page<Medication> findAllMedication (String keyword,
                                        Pageable pageable);
    Medication createMedication(MedicationRequest request);
    Medication updateMedication(Long id,MedicationRequest request);
    Boolean deleteMedicationById(Long id);


}
