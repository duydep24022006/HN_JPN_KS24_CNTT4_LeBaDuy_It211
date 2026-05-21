package org.example.bai4.service;

import org.example.bai4.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    public String getCourse(Long id) {
        if (id != 1L) {
            throw new ResourceNotFoundException("Không tìm thấy khóa học với id: " + id);
        }
        return "Khóa học Java AOP";
    }
}
