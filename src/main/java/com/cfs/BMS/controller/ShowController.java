package com.cfs.BMS.controller;

import com.cfs.BMS.dto.ShowRequest;
import com.cfs.BMS.entity.Show;
import com.cfs.BMS.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;


    // ==============================
    // GET ALL SHOWS
    // ==============================

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {

        return ResponseEntity.ok(
                showService.getAllShow()
        );
    }


    // ==============================
    // GET SHOW BY ID
    // ==============================

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                showService.getShowById(id)
        );
    }


    // ==============================
    // GET SHOWS BY MOVIE
    // ==============================

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowByMovie(
            @PathVariable Long movieId) {

        return ResponseEntity.ok(
                showService.getShowByMovie(movieId)
        );
    }


    // ==============================
    // GET SHOWS BY MOVIE + DATE
    // ==============================

    @GetMapping("/movie/{movieId}/date")
    public ResponseEntity<List<Show>> getShowByMovieAndDate(
            @PathVariable Long movieId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                showService.getShowByMovieAndDate(
                        movieId,
                        date
                )
        );
    }


    // ==============================
    // ADD SHOW
    // ==============================

    @PostMapping
    public ResponseEntity<Show> addShow(
            @RequestBody ShowRequest request) {

        return ResponseEntity.ok(
                showService.addShow(request)
        );
    }
}