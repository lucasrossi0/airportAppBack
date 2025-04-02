package com.airportapp.airport_app.dto;

import com.airportapp.airport_app.model.Flight;
import lombok.Value;


import java.util.List;

@Value // all fields private final, generates getters, creates allargs constructor, equals, hashcode and tostring
public class FlightResponse {
     List<Flight> flights;
     int currentPage;
     int totalPages;
     long totalFlights;
}
