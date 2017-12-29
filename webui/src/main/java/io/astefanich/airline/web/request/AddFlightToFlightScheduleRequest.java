package io.astefanich.airline.web.request;

import lombok.Value;

@Value
public class AddFlightToFlightScheduleRequest {

  private String flightNumber;

  private String origin;

  private String destination;

  private String departureDate;

  private int numberOfSeats;

}
