package io.astefanich.airline.command.aggregate.booking;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.BookingStatus;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.BookingConfirmedEvent;
import io.astefanich.airline.common.events.BookingCreatedEvent;
import io.astefanich.airline.common.events.BookingRejectedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@NoArgsConstructor
public class Booking {

  @AggregateIdentifier
  private BookingNumber bookingNumber;

  private FlightNumber flightNumber;

  private PassengerName passengerName;

  private BookingStatus status;

  public Booking(BookingNumber bookingNumber, FlightNumber flightNumber, PassengerName passengerName) {
    apply(new BookingCreatedEvent(bookingNumber, flightNumber, passengerName));
  }

  void markBookingConfirmed() {
    apply(new BookingConfirmedEvent(bookingNumber, flightNumber, passengerName));
  }

  void markBookingRejected() {
    apply(new BookingRejectedEvent(bookingNumber, flightNumber, passengerName));
  }


  @EventSourcingHandler
  public void on(BookingCreatedEvent event) {
    this.bookingNumber = event.getBookingNumber();
    this.flightNumber = event.getFlightNumber();
    this.passengerName = event.getPassengerName();
    this.status = BookingStatus.RECEIVED;
  }

  @EventSourcingHandler
  public void on(BookingConfirmedEvent event) {
    this.status = BookingStatus.CONFIRMED;
  }

  @EventSourcingHandler
  public void on(BookingRejectedEvent event) {
    this.status = BookingStatus.REJECTED;
  }

}
