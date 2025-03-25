package com.airportapp.airport_app.service;

import com.airportapp.airport_app.model.Flight;
import com.airportapp.airport_app.repository.AirportRepository;
import com.airportapp.airport_app.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    @Override
    public Page<Flight> findAll(Pageable pageable){
        return flightRepository.findAll(pageable);
    }

    @Override
    public Optional<Flight> findById(Long id){
        return flightRepository.findById(id);
    }

    @Override
    public Flight save(Flight flight){
        return flightRepository.save(flight);
    }

    @Override
    public void deleteById(Long id){
        flightRepository.deleteById(id);
    }
}
