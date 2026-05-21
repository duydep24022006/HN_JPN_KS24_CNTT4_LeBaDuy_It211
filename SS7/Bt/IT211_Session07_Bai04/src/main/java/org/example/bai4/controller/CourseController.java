package org.example.bai4.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai4.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/course/{id}")
    public ResponseEntity<String> getCourse(@PathVariable Long id) {
        String result = courseService.getCourse(id);
        return ResponseEntity.ok(result);
    }
}