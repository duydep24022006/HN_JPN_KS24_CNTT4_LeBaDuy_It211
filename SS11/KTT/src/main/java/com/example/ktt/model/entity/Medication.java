package com.example.ktt.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medication")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Medication {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String name;
    private String manufacturer;
    private Double price;
    private Status status;
    private boolean is_deleted=false;

}
