package com.example.bt4.controller;

import com.example.bt4.service.LessonService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@Validated
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/watch")
    public String watchLesson(
            @RequestHeader("X-User")
            String username,
            @RequestParam
            @Min(
                    value = 1,
                    message = "lessonId phải lớn hơn 0"
            )
            Long lessonId
    ) {

        return lessonService.watchLesson(
                lessonId,
                username
        );
    }
}
