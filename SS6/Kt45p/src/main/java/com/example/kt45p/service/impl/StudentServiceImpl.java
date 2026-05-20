package com.example.kt45p.service.impl;

import com.example.kt45p.model.dto.response.StudentDto;
import com.example.kt45p.model.entity.Student;
import com.example.kt45p.repository.StudentRepository;
import com.example.kt45p.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Student not found with id: " + id
        ));
    }

    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student=Student.builder()
                .fullName(studentDto.getFullName())
                .email(studentDto.getEmail())
                .gpa(studentDto.getGpa())
                .build();

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, StudentDto studentDto) {

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found with id: " + id
                ));

        existingStudent.setFullName(studentDto.getFullName());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setGpa(studentDto.getGpa());

        return studentRepository.save(existingStudent);
    }

    @Override
    public boolean deleteStudent(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->  new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Student not found with id: " + id
                ));
        studentRepository.delete(existingStudent);
        return true;
    }
}
