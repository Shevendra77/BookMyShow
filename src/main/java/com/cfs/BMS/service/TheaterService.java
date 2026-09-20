package com.cfs.BMS.service;

import com.cfs.BMS.dto.TheaterRequest;
import com.cfs.BMS.entity.City;
import com.cfs.BMS.entity.Theater;
import com.cfs.BMS.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;
    private final CityService cityService;

    // Add new theater
    public Theater addTheater(TheaterRequest request) {

        City city = cityService.getCityById(request.getCityId());

        Theater theater = Theater.builder()
                .name(request.getName())
                .address(request.getAddress())
                .city(city)
                .build();

        return theaterRepository.save(theater);
    }

    // Get all theaters
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    // Get theater by ID
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Theater not found with id: " + id
                        ));
    }

    // Get theaters by city
    public List<Theater> getTheaterByCity(Long cityId) {
        return theaterRepository.findByCityId(cityId);
    }
}