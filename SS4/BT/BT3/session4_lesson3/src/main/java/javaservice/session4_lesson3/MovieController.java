package javaservice.session4_lesson3;
//So sánh @PathVariable và @RequestParam:
//
//Tiêu chí	@PathVariable	@RequestParam
//Vị trí trên URL	Nằm trong đường dẫn (path) của URL.	Nằm trong query string sau dấu ?.
//Mục đích sử dụng	Dùng để xác định một tài nguyên cụ thể (định danh duy nhất).	Dùng để lọc, tìm kiếm, truyền tham số tùy chọn.
//Ví dụ thực tế	/api/v1/movies/M001 → lấy phim có ID M001.	/api/v1/movies?genre=Sci-Fi → lọc danh sách phim theo thể loại Sci-Fi.
//
//
//Giải thích:
//
//Tình huống A (xem chi tiết phim theo ID) cần định danh duy nhất → dùng @PathVariable.
//
//Tình huống B (lọc theo thể loại) là tìm kiếm, lọc dữ liệu → dùng @RequestParam.
//
//Không thể hoán đổi cho nhau vì:
//
//Nếu dùng @RequestParam cho ID, URL sẽ thành /api/v1/movies?movieId=M001, không đúng chuẩn RESTful (ID nên nằm trong path).
//
//Nếu dùng @PathVariable cho lọc, URL sẽ thành /api/v1/movies/Sci-Fi, gây nhầm lẫn vì trông giống ID chứ không phải điều kiện lọc.
//
//Chuẩn RESTful:
//
//Định danh tài nguyên → /api/v1/movies/{id}
//
//Lọc/tìm kiếm → /api/v1/movies?genre=...

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    static class Movie {
        private String movieId;
        private String title;
        private String genre;
        private double rating;

        public Movie(String movieId, String title, String genre, double rating) {
            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.rating = rating;
        }

        // Getters & setters
        public String getMovieId() { return movieId; }
        public void setMovieId(String movieId) { this.movieId = movieId; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }

        public double getRating() { return rating; }
        public void setRating(double rating) { this.rating = rating; }
    }

    private final List<Movie> movies = Arrays.asList(
            new Movie("M001", "Inception", "Sci-Fi", 8.8),
            new Movie("M002", "Parasite", "Drama", 8.6),
            new Movie("M003", "Interstellar", "Sci-Fi", 8.7)
    );

    // Tình huống A: Xem chi tiết phim theo ID
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") String id) {
        return movies.stream()
                .filter(m -> m.getMovieId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Tình huống B: Lọc phim theo thể loại
    @GetMapping
    public List<Movie> getMoviesByGenre(@RequestParam(value = "genre", required = false) String genre) {
        if (genre == null) {
            return movies; // nếu không truyền genre thì trả về tất cả
        }
        return movies.stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .toList();
    }
}
