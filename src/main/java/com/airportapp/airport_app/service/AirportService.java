package com.airportapp.airport_app.service;

import com.airportapp.airport_app.model.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface AirportService {
    Page<Airport> findAll(Pageable pageable);
    Optional<Airport> findById(Long id);
    Airport save(Airport airport);
    void deleteById(Long id);
}

