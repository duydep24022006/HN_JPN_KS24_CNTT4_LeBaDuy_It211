package com.example.demo.service.impl;

import com.example.demo.model.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmployeeSeviceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }



    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new NoSuchElementException("Không tìm thấy nhân viên với id: " + id));

    }

        @Override
        public Employee insertEmployee(Employee employee) {
            return employeeRepository.save(employee);
        }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        employeeRepository.findById(id).orElseThrow(()->new NoSuchElementException("Không tìm thấy nhân viên với id: " + id));
        employee.setEmpid(id);

        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        employeeRepository.findById(employee.getEmpid()).orElseThrow(()->new NoSuchElementException("Không tìm thấy nhân viên với id: " + employee.getEmpid()));
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.getEmployeesByEmpNameContaining(name);
    }
}
