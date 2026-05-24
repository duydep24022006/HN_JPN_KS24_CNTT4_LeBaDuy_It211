package com.example.bt4.service.impl;

import com.example.bt4.exception.AccessDeniedException;
import com.example.bt4.exception.ResourceNotFoundException;
import com.example.bt4.model.entity.LessonVideo;
import com.example.bt4.repository.LessonVideoRepository;
import com.example.bt4.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl
        implements LessonService {

    private final LessonVideoRepository lessonVideoRepository;

    @Override
    public String watchLesson(
            Long lessonId,
            String username
    ) {

        LessonVideo lesson =
                lessonVideoRepository
                        .findById(lessonId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Lesson video not found."
                                )
                        );

        if (!lesson.getIsFree()) {

            boolean purchased = false;

            if (!purchased) {
                throw new AccessDeniedException(
                        "You have not purchased this course yet."
                );
            }
        }

        return lesson.getVideoUrl();
    }
}
