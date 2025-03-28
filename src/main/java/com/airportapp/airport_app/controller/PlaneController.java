package com.airportapp.airport_app.controller;

import com.airportapp.airport_app.model.Plane;
import com.airportapp.airport_app.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/planes")
@CrossOrigin(origins = "http://localhost:5173")

public class PlaneController {

    private final PlaneService planeService;

    @Autowired
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public ResponseEntity<Page<Plane>> getAllPlanes(Pageable pageable) {
        return ResponseEntity.ok(planeService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable Long id) {
        Optional<Plane> plane = planeService.findById(id);
        return plane.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        Plane savedPlane = planeService.save(plane);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlane);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plane> updatePlane(@PathVariable Long id, @RequestBody Plane plane) {
        if (!planeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        plane.setId(id);
        return ResponseEntity.ok(planeService.save(plane));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        if (!planeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        planeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
