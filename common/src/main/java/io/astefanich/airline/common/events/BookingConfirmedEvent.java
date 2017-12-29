package io.astefanich.airline.common.events;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import lombok.Getter;

@Getter
public class BookingConfirmedEvent extends AbstractBookingEvent {

  private FlightNumber flightNumber;

  private PassengerName passengerName;


  public BookingConfirmedEvent(BookingNumber bookingNumber, FlightNumber flightNumber, PassengerName passengerName) {
    super(bookingNumber);
    this.flightNumber = flightNumber;
    this.passengerName = passengerName;
  }
}
