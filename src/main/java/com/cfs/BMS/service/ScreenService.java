package com.cfs.BMS.service;

import com.cfs.BMS.dto.ScreenRequest;
import com.cfs.BMS.entity.Screen;
import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.repository.ScreenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterService theaterService;

    // Add new screen
    public Screen addScreen(ScreenRequest request) {

        Theater theater = theaterService.getTheaterById(
                request.getTheaterId()
        );

        Screen screen = Screen.builder()
                .name(request.getName())
                .totalSeats(request.getTotalSeats())
                .theater(theater)
                .build();

        return screenRepository.save(screen);
    }

    // Get all screens
    public List<Screen> getAllScreen() {
        return screenRepository.findAll();
    }

    // Get screen by ID
    public Screen getScreenById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Screen not found with id: " + id
                        ));
    }

    // Get screens by theater
    public List<Screen> getScreenByTheater(Long theaterId) {
        return screenRepository.findByTheaterId(theaterId);
    }
}