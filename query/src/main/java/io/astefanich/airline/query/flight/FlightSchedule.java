package io.astefanich.airline.query.flight;

import java.util.List;

public class FlightSchedule {

  private List<FlightEntry> flights;

  public FlightSchedule(List<FlightEntry> flights) {
    this.flights = flights;
  }

  public List<FlightEntry> getFlights() {
    return flights;
  }

}
