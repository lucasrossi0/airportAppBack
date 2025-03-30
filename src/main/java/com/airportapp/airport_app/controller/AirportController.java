package com.airportapp.airport_app.controller;

import com.airportapp.airport_app.model.Airport;
import com.airportapp.airport_app.model.Flight;
import com.airportapp.airport_app.repository.AirportRepository;
import com.airportapp.airport_app.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@CrossOrigin(origins = "http://localhost:5173")
public class AirportController {

    private final AirportService airportService;
    private final AirportRepository airportRepository;

    @Autowired
    public AirportController(AirportService airportService, AirportRepository airportRepository) {
        this.airportService = airportService;
        this.airportRepository = airportRepository;
    }

    @GetMapping
    public Page<Airport> getAllAirports(Pageable pageable) {
        return airportService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        return airportService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Airport createAirport(@RequestBody Airport airport) {
        return airportService.save(airport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return airportService.findById(id)
                .map(existingAirport -> {
                    airport.setId(id);
                    return ResponseEntity.ok(airportService.save(airport));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        return airportService.findById(id)
                .map(airport -> {
                    airportService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/departures")
    public ResponseEntity<List<Flight>> getDepartures(@PathVariable Long id){
        return ResponseEntity.ok(airportRepository.findDepartures(id));
    }

    @GetMapping("/{id}/arrivals")
    public ResponseEntity<List<Flight>> getArrivals(@PathVariable Long id){
        return ResponseEntity.ok(airportRepository.findArrivals(id));
    }
//    @GetMapping("/search")
//    public List<Airport> searchAirports(@RequestParam String keyword) {
//        return airportService.searchAirports(keyword);
//    }
}