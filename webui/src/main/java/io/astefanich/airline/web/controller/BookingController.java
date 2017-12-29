package io.astefanich.airline.web.controller;

import io.astefanich.airline.common.commands.CreateBookingCommand;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.web.request.BookTicketRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
public class BookingController {

  private CommandGateway gateway;

  @Autowired
  public BookingController(CommandGateway gateway) {
    this.gateway = gateway;
  }

  @PostMapping("/book")
  public CompletableFuture<BookingNumber> bookTickets(@RequestBody BookTicketRequest request) {
    PassengerName passenger = new PassengerName(request.getPassengerFirstName(), request.getPassengerLastName());
    FlightNumber flightNumber = new FlightNumber(request.getFlightNumber());
    BookingNumber bookingNumber = new BookingNumber();

    CreateBookingCommand command = new CreateBookingCommand(bookingNumber, flightNumber, passenger);
    return gateway.send(command);

  }
}