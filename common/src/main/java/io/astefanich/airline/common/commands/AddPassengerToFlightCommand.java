package io.astefanich.airline.common.commands;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class AddPassengerToFlightCommand {

  private BookingNumber bookingNumber;

  @TargetAggregateIdentifier
  private FlightNumber flightNumber;

  private PassengerName passenger;

  public AddPassengerToFlightCommand(BookingNumber bookingNumber, FlightNumber flightNumber,
          PassengerName passenger) {
    this.bookingNumber = bookingNumber;
    this.flightNumber = flightNumber;
    this.passenger = passenger;
  }

  public BookingNumber getBookingNumber() {
    return bookingNumber;
  }

  public FlightNumber getFlightNumber() {
    return flightNumber;
  }

  public PassengerName getPassenger() {
    return passenger;
  }
}
