package com.cfs.BMS.controller;

import com.cfs.BMS.entity.Movie;
import com.cfs.BMS.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    // Add Movie
    @PostMapping
    public ResponseEntity<Movie> addMovie(
            @RequestBody Movie movie) {

        return ResponseEntity.ok(
                movieService.addMovie(movie)
        );
    }

    // Get All Movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {

        return ResponseEntity.ok(
                movieService.getAllMovies()
        );
    }

    // Get Movie By ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                movieService.getMovieById(id)
        );
    }

    // Search Movies
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam String title) {

        return ResponseEntity.ok(
                movieService.searchByTitle(title)
        );
    }

    // Get By Genre
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Movie>> getByGenre(
            @PathVariable String genre) {

        return ResponseEntity.ok(
                movieService.getByGenre(genre)
        );
    }

    // Get By Language
    @GetMapping("/language/{language}")
    public ResponseEntity<List<Movie>> getByLanguage(
            @PathVariable String language) {

        return ResponseEntity.ok(
                movieService.getByLanguage(language)
        );
    }
}