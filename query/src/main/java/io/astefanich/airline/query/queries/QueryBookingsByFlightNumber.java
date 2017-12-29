package io.astefanich.airline.query.queries;

public class QueryBookingsByFlightNumber {

  private String flightNumber;

  public QueryBookingsByFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String number() {
    return flightNumber;
  }
}
