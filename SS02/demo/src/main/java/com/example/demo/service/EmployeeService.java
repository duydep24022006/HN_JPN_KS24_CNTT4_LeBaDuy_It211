package com.example.demo.service;


import com.example.demo.model.entity.Employee;

import java.util.List;

public interface EmployeeService  {
    List<Employee> getEmployees();
    Employee getEmployeeById(Long id);
    Employee insertEmployee(Employee employee);
    Employee updateEmployee(Employee employee,Long id);
    boolean deleteEmployee(Employee employee);
    List<Employee>getEmployeesByName(String name);



}
