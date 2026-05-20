package com.example.kt45p.service;

import com.example.kt45p.model.dto.response.StudentDto;
import com.example.kt45p.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student createStudent(StudentDto studentDto);
    Student updateStudent(Long id,StudentDto studentDto);
    boolean deleteStudent(Long id);;
}
