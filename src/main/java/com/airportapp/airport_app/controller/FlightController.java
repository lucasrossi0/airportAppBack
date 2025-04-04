package com.airportapp.airport_app.controller;


import com.airportapp.airport_app.dto.FlightResponse;
import com.airportapp.airport_app.model.Flight;
import com.airportapp.airport_app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flights")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<FlightResponse> getAllFlights(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "departureTime,asc") String[] sort) {

        List<Sort.Order> orders = new ArrayList<>();

        for (String sortField : sort) {
            String[] parts = sortField.split(",");
            String property = parts[0];

            Sort.Direction direction = Optional.ofNullable(parts.length > 1 ? parts[1] : null)
                    .map(String::toUpperCase)
                    .map(Sort.Direction::valueOf)
                    .orElse(Sort.Direction.ASC);

            orders.add(new Sort.Order(direction, property));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        Page<Flight> flightPage = flightService.findAll(pageable);

        FlightResponse response = new FlightResponse(
                flightPage.getContent(),
                flightPage.getNumber(),
                flightPage.getTotalPages(),
                flightPage.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        return flightService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.save(flight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return flightService.findById(id)
                .map(existingFlight -> {
                    flight.setId(id);
                    return ResponseEntity.ok(flightService.save(flight));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        return flightService.findById(id)
                .map(flight -> {
                    flightService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}

