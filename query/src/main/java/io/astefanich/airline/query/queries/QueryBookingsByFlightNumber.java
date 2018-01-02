package io.astefanich.airline.query.queries;

import io.astefanich.airline.common.domain.FlightNumber;

public class QueryBookingsByFlightNumber {

  private FlightNumber flightNumber;

  public QueryBookingsByFlightNumber(FlightNumber flightNumber) {
    this.flightNumber = flightNumber;
  }

  public FlightNumber getFlightNumber() {
    return flightNumber;
  }
}
