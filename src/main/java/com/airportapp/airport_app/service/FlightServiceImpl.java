package com.airportapp.airport_app.service;

import com.airportapp.airport_app.model.Flight;
import com.airportapp.airport_app.repository.AirportRepository;
import com.airportapp.airport_app.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService{

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    @Override
    public Page<Flight> findAll(FlightDTO flightDTO){
        return flightRepository.findAll(createPageable(flightDTO));
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

    private Pageable createPageable(String[] sort, int page, int size){
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

        return PageRequest.of(page, size, Sort.by(orders));
    }
}
