package com.example.ktt.repository;

import com.example.ktt.model.entity.Medication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long> {

    Page<Medication> findByIsDeletedFalse(Pageable pageable);

    @Query("""
        SELECT m FROM Medication m
        WHERE m.is_deleted = false
        AND (
            LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(m.manufacturer) LIKE LOWER(CONCAT('%', :keyword, '%'))
        )
    """)
    Page<Medication> searchMedication(
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
