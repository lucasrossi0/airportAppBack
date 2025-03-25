package com.airportapp.airport_app.service;

import com.airportapp.airport_app.model.Plane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PlaneService {
    Page<Plane> findAll(Pageable pageable);
    Optional<Plane> findById(Long id);
    Plane save(Plane plane);
    void deleteById(Long id);
}
