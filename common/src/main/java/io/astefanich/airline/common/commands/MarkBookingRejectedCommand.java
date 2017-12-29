package io.astefanich.airline.common.commands;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import lombok.Getter;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
public class MarkBookingRejectedCommand {

  @TargetAggregateIdentifier
  private BookingNumber bookingNumber;

  private FlightNumber flightNumber;

  private PassengerName passengerName;

  public MarkBookingRejectedCommand(BookingNumber bookingNumber, FlightNumber flightNumber, PassengerName passengerName) {
    this.bookingNumber = bookingNumber;
    this.flightNumber = flightNumber;
    this.passengerName = passengerName;
  }
}
