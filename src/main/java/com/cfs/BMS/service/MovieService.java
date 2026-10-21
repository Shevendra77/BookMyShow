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

    // Get All Movies (newest first)
    public List<Movie> getAllMovies() {
        return movieRepository.findAllByOrderByIdDesc();
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

    // Search Movies By Title (newest first)
    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(title);
    }

    // Get Movies By Genre (newest first)
    public List<Movie> getByGenre(String genre) {
        return movieRepository.findByGenreOrderByIdDesc(genre);
    }

    // Get Movies By Language (newest first)
    public List<Movie> getByLanguage(String language) {
        return movieRepository.findByLanguageOrderByIdDesc(language);
    }

    // Delete Movie
    public void deleteMovie(Long id) {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
    }

    // Update Movie
    // (add this once you share your Movie entity fields)
}