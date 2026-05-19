package com.example.demo.service;

import com.example.demo.model.entity.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<Employee> getAllEmployees(Integer page, Integer size);
}
