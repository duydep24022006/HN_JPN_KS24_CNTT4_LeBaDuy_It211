package com.example.bt1.controller;

import com.example.bt1.entity.Movie;
import com.example.bt1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }
}
