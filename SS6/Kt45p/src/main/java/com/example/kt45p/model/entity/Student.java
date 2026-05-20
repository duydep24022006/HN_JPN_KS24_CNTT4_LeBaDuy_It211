package com.example.kt45p.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false,length = 50)
    private String fullName;
    @Column(length = 30)
    private String email;
    private Double gpa;
}
