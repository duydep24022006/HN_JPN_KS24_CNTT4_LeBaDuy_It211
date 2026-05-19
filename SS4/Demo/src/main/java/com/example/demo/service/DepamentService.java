package com.example.demo.service;

import com.example.demo.model.entity.Department;

import java.util.List;

public interface DepamentService {
    List<Department> getAllDepartments();
    Department insertDepartment(Department department);
    Department updateDepartment(Department department);
    Boolean deleteDepartment(Long id);
}
