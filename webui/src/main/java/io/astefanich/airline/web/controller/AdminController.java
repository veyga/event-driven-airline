package io.astefanich.airline.web.controller;


import io.astefanich.airline.common.commands.AddFlightToFlightScheduleCommand;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.Schedule;
import io.astefanich.airline.web.request.AddFlightToFlightScheduleRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.Future;

@RestController("/admin")
@Slf4j
public class AdminController {

  private CommandGateway gateway;

  public AdminController(CommandGateway gateway) {
    this.gateway = gateway;
  }

  @PostMapping("/flights/add")
  public Future<FlightNumber> addFlightToSchedule(@RequestBody AddFlightToFlightScheduleRequest request) {
    FlightNumber flightNumber = new FlightNumber(request.getFlightNumber());
    Schedule schedule = new Schedule(request.getOrigin(), request.getDestination(), new Date(request.getDepartureDate()));
    return gateway.send(new AddFlightToFlightScheduleCommand(flightNumber, schedule, request.getNumberOfSeats()));
  }
}
