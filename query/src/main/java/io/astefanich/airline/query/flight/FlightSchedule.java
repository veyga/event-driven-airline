package io.astefanich.airline.query.flight;

import java.util.List;

public class FlightSchedule {

  private List<FlightEntry> flightEntries;

  public FlightSchedule(List<FlightEntry> flightEntries) {
    this.flightEntries = flightEntries;
  }

  public List<FlightEntry> flights() {
    return flightEntries;
  }
}
