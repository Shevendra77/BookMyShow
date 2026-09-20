package com.cfs.BMS.service;

import com.cfs.BMS.entity.City;
import com.cfs.BMS.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    // Add new city
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    // Get all cities
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Get city by ID
    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("City not found with id: " + id));
    }
}