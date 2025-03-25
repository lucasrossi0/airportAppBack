package com.airportapp.airport_app.service;

import com.airportapp.airport_app.model.Plane;
import com.airportapp.airport_app.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService{

    private final PlaneRepository planeRepository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository){
        this.planeRepository = planeRepository;
    }

    @Override
    public Page<Plane> findAll(Pageable pageable){
        return planeRepository.findAll(pageable);
    }

    @Override
    public Optional<Plane> findById(Long id){
        return planeRepository.findById(id);
    }

    @Override
    public Plane save(Plane plane){
        return planeRepository.save(plane);
    }

    @Override
    public void deleteById(Long id){
        planeRepository.deleteById(id);
    }

}
