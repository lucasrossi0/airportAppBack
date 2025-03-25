package com.airportapp.airport_app.service;

import com.airportapp.airport_app.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FlightService {
    Page<Flight> findAll(Pageable pageable);
    Optional<Flight> findById(Long id);
    Flight save(Flight flight);
    void deleteById(Long id);
}
