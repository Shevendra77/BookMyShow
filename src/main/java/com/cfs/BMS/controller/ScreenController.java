package com.cfs.BMS.controller;

import com.cfs.BMS.dto.ScreenRequest;
import com.cfs.BMS.entity.Screen;
import com.cfs.BMS.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenController {

    private final ScreenService screenService;

    // Add new screen
    @PostMapping
    public ResponseEntity<Screen> addScreen(
            @RequestBody ScreenRequest request) {

        return ResponseEntity.ok(
                screenService.addScreen(request)
        );
    }

    // Get all screens
    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() {
        return ResponseEntity.ok(
                screenService.getAllScreen()
        );
    }

    // Get screen by ID
    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                screenService.getScreenById(id)
        );
    }

    // Get screens by theater
    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Screen>> getScreenByTheaterId(
            @PathVariable Long theaterId) {

        return ResponseEntity.ok(
                screenService.getScreenByTheater(theaterId)
        );
    }
}