package io.astefanich.airline.query.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightEntryRepository extends JpaRepository<FlightEntry, String> {

  List<FlightEntry> findByOriginAndDestinationAndFlightDate(String origin, String destination, String flightDate);

  List<FlightEntry> findByOriginAndDestination(String origin, String destination);

  FlightEntry findByFlightNumberAndFlightDate(String flightNumber, String flightDate);

  FlightEntry findByFlightNumber(String flightNumber);
}
