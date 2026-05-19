package com.example.bt1.service.impl;

import com.example.bt1.entity.Movie;
import com.example.bt1.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> getMovies() {
        return List.of(
                new Movie("M001", "Inception", "Sci-Fi", 8.8),
                new Movie("M002", "Parasite", "Drama", 8.6),
                new Movie("M003", "Avengers", "Action", 9.0)
        );
    }
}
