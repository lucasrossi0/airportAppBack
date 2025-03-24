package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String code;
    private String city;
    private String country;

    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departedFlights;

    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivingFlights;
}
