package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "department")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Department {
    @Id
    @Column(name = "department_id",nullable = false,unique = false)
    private String departmentId;
    @Column(name = "department_name",length = 50,nullable = false,unique = true)
    private String departmentName;
    private Boolean status;
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;

}
