package com.example.ktt.service.impl;

import com.example.ktt.model.dto.request.MedicationRequest;
import com.example.ktt.model.entity.Medication;
import com.example.ktt.repository.MedicationRepository;
import com.example.ktt.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MedicationServiceImpl implements MedicationService {
    private final MedicationRepository medicationRepository;

    @Override
    public Page<Medication> findAllMedication(
            String keyword,
            Pageable pageable
    ) {
        if (keyword == null || keyword.isBlank()) {
            return medicationRepository.findByIsDeletedFalse(pageable);
        }

        return medicationRepository.searchMedication(keyword, pageable);
    }

    @Override
    public Medication createMedication(MedicationRequest request) {
        Medication medication=Medication.builder()
                .name(request.getName())
                .manufacturer(request.getManufacturer())
                .price(request.getPrice())
                .status(request.getStatus())
                .build();
        log.info("Thêm thuốc thành công name :{}, manufactuner :{} ");
        return medicationRepository.save(medication);
    }

    @Override
    public Medication updateMedication(Long id, MedicationRequest request) {
        Medication medication=medicationRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy thuốc có id: "+id));
        if(request.getName() !=null){
            medication.setName((request.getName()));
        }
        if(request.getManufacturer() !=null){
            medication.setManufacturer((request.getManufacturer()));
        }
        if(request.getPrice() !=null){
            medication.setPrice((request.getPrice()));
        }
        if(request.getStatus() !=null){
            medication.setStatus((request.getStatus()));
        }

        return medicationRepository.save(medication);
    }


    @Override
    public Boolean deleteMedicationById(Long id) {
        Medication medication=medicationRepository.findById(id).orElseThrow(()->
                new RuntimeException("Không tìm thấy thuốc có id: "+id));
        medication.set_deleted(true);
        medicationRepository.save(medication);
        return  true;
    }
}
