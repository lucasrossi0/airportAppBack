package com.airportapp.airport_app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus flightStatus;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "departedFlights", "arrivingFlights"})
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "departedFlights", "arrivingFlights"})
    private Airport arrivalAirport;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;
}
