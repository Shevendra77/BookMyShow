package com.cfs.BMS.service;

import com.cfs.BMS.entity.Movie;
import com.cfs.BMS.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    // Add Movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Get All Movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get Movie By ID
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movie not found with id: " + id
                        )
                );
    }

    // Search Movies By Title
    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    // Get Movies By Genre
    public List<Movie> getByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    // Get Movies By Language
    public List<Movie> getByLanguage(String language) {
        return movieRepository.findByLanguage(language);
    }

    // Update Movie
    // Delete Movie
}