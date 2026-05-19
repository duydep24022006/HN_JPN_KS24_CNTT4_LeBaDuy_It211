package com.example.demo.service.impl;

import com.example.demo.model.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepamentSeviceImpl implements DepamentService {
    private final DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department insertDepartment(Department department) {
        if (department.getDepartmentId() != null &&
                departmentRepository.existsById(department.getDepartmentId())) {

            throw new RuntimeException("Department already exists");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        departmentRepository.findById(department.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentRepository.save(department);
    }

    @Override
    public Boolean deleteDepartment(Long id) {
        return null;
    }
}
