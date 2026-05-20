package com.example.kt45p.controller;

import com.example.kt45p.model.dto.response.ApiDataResponse;
import com.example.kt45p.model.dto.response.StudentDto;
import com.example.kt45p.model.entity.Student;
import com.example.kt45p.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Student>>> getAllStudents(){
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Get all students successfully",
                        studentService.getAllStudents(),
                        HttpStatus.OK
                ),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> getStudentById(@PathVariable Long id){
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Get student by id successfully",
                        studentService.getStudentById(id),
                        HttpStatus.OK
                ),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiDataResponse<Student>> createStudent(@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Create student successfully",
                        studentService.createStudent(studentDto),
                        HttpStatus.CREATED
                ),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> putUpdateStudent(@PathVariable Long id,@RequestBody StudentDto student) {
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Update student successfully",
                        studentService.updateStudent(id, student),
                        HttpStatus.OK
                ), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Student>> patchUpdateStudent(@PathVariable Long id,@RequestBody StudentDto student) {
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Update student successfully",
                        studentService.updateStudent(id, student),
                        HttpStatus.OK
                ), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Boolean>> deleteStudent(@PathVariable Long id) {
        return new ResponseEntity<>(
                new ApiDataResponse<>(
                        true,
                        "Delete student successfully",
                        studentService.deleteStudent(id),
                        HttpStatus.OK
                ), HttpStatus.OK);
    }

}
