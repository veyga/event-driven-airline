package io.astefanich.airline.common.events;

import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.Schedule;
import lombok.Value;

@Value
public class FlightAddedToFlightScheduleEvent {

  private FlightNumber flightNumber;

  private Schedule schedule;

  private int numberOfSeats;
}
