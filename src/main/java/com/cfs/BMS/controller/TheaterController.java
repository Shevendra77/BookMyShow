package com.cfs.BMS.controller;

import com.cfs.BMS.dto.TheaterRequest;
import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    // Add new theater
    @PostMapping
    public ResponseEntity<Theater> addTheater(
            @RequestBody TheaterRequest request) {

        return ResponseEntity.ok(
                theaterService.addTheater(request)
        );
    }

    // Get all theaters
    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        return ResponseEntity.ok(
                theaterService.getAllTheaters()
        );
    }

    // Get theater by ID
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                theaterService.getTheaterById(id)
        );
    }

    // Get theaters by city
    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Theater>> getTheaterByCity(
            @PathVariable Long cityId) {

        return ResponseEntity.ok(
                theaterService.getTheaterByCity(cityId)
        );
    }
}