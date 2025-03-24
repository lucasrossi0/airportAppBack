package com.airportapp.airport_app.controller;

import com.airportapp.airport_app.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airportapp.airport_app.repository.PlaneRepository;

import java.util.List;

@RestController
@RequestMapping("/api/planes")
public class PlaneController {

    @Autowired
    public PlaneRepository planeRepository;

    @GetMapping
    public ResponseEntity<List<Plane>> getAllPlanes() {
        List<Plane> planes = planeRepository.findAll();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }
}
