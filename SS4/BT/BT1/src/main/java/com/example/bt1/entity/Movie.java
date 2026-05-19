package com.example.bt1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Movie {
    private String movieId;
    private String title;
    private String genre;
    private double rating;
}
