package io.astefanich.airline.common.commands;

import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.Schedule;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class AddFlightToFlightScheduleCommand {

  @TargetAggregateIdentifier
  private FlightNumber flightNumber;

  private Schedule schedule;

  private int numberOfSeats;
}
