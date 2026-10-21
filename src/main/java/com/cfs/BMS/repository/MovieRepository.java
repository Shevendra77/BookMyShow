package com.cfs.BMS.repository;

import com.cfs.BMS.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

  // All movies, newest first
  List<Movie> findAllByOrderByIdDesc();

  // Search by title, newest first
  List<Movie> findByTitleContainingIgnoreCaseOrderByIdDesc(String title);

  // Filter by genre, newest first
  List<Movie> findByGenreOrderByIdDesc(String genre);

  // Filter by language, newest first
  List<Movie> findByLanguageOrderByIdDesc(String language);
}