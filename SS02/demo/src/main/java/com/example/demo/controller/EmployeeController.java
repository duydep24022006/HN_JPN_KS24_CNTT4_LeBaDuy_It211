package com.example.demo.controller;

import com.example.demo.model.dto.response.ApiDataResponse;
import com.example.demo.model.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Employee>>> getAllEmployees() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách nhân viên thành công!",
                employeeService.getEmployees(),
                HttpStatus.OK
        ),HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ApiDataResponse<Employee>> insertEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm nhân viên thành công!",
                employeeService.insertEmployee(employee),
                HttpStatus.CREATED
        ),HttpStatus.CREATED);
    }

}
