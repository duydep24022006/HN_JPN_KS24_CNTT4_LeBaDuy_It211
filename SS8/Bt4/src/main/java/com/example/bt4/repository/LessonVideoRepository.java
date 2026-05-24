package com.example.bt4.repository;

import com.example.bt4.model.entity.LessonVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonVideoRepository
        extends JpaRepository<LessonVideo, Long> {
}
