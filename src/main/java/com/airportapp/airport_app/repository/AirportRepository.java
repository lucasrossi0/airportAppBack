package com.airportapp.airport_app.repository;

import com.airportapp.airport_app.model.Airport;
import com.airportapp.airport_app.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    @Query("SELECT f FROM Flight f WHERE f.departureAirport.id = :airportId")
    List<Flight> findDepartures(@Param("airportId") Long airportId);

    @Query("SELECT f FROM Flight f WHERE f.arrivalAirport.id = :airportId")
    List<Flight> findArrivals(@Param("airportId") Long airportId);
}

