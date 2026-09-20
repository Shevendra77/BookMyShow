package com.cfs.BMS.service;

import com.cfs.BMS.dto.SeatRequest;
import com.cfs.BMS.entity.Screen;
import com.cfs.BMS.entity.Seat;
import com.cfs.BMS.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    private final ScreenService screenService;

    // Add Seat
    public Seat addSeat(SeatRequest request) {

        Screen screen = screenService.getScreenById(
                request.getScreenId()
        );

        Seat seat = Seat.builder()
                .seatNumber(request.getSeatNumber())
                .row(request.getRow())
                .col(request.getCol())
                .seatType(request.getSeatType())
                .screen(screen)
                .build();

        return seatRepository.save(seat);
    }

    // Get All Seats
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    // Get Seats By Screen
    public List<Seat> getSeatsByScreen(Long screenId) {
        return seatRepository.findByScreenId(screenId);
    }

    // Get Seat By ID
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Seat not found with id: " + id
                        )
                );
    }
}