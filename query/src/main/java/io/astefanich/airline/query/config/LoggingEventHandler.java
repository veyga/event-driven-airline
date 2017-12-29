package io.astefanich.airline.query.config;

import io.astefanich.airline.common.domain.Schedule;
import io.astefanich.airline.common.events.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingEventHandler {

  @EventHandler
  public void on(FlightAddedToFlightScheduleEvent event) {
    Schedule receivedSchedule = event.getSchedule();
    log.info(String.format("Flight # %s added to schedule. Origin: %s, Destination: %s, " +
                    "DepartureDate: %s, Initial Capacity %s", event.getFlightNumber(), receivedSchedule.getOrigin(),
            receivedSchedule.getDestination(), receivedSchedule.getDestination(), event.getNumberOfSeats()));
  }

  @EventHandler
  public void on(BookingCreatedEvent event) {
    log.info(String.format("Booking # %s received for passenger %s, process initialized",
            event.getBookingNumber(), event.getPassengerName()));
  }

  @EventHandler
  public void on(CustomerNotRegisteredEvent event) {
    log.info(String.format("%s not yet registered, attempting registration", event.getPassengerName()));
  }

  @EventHandler
  public void on(CustomerRegisteredEvent event) {
    log.info(String.format("%s has successfully registered", event.getPassengerName()));
  }

  @EventHandler
  public void on(BookingAddedToCustomerHistoryEvent event) {
    log.info(String.format("Booking # %s received; added to purchase history of %s", event
            .getBookingNumber(), event.getPassengerName()));
  }

  @EventHandler
  public void on(PassengerAddedToFlightEvent event) {
    log.info(String.format("%s added to manifest of flight # %s", event.getPassengerName(), event.getFlightNumber()));
  }

  @EventHandler
  public void on(BookingConfirmedEvent event) {
    log.info(String.format("Booking # %s confirmed!! Flight: %s, Passenger: %s", event
            .getBookingNumber(), event.getFlightNumber(), event.getPassengerName()));
  }

  @EventHandler
  public void on(BookingRejectedEvent event) {
    log.info(String.format("Booking # %s rejected!! Flight: %s, Passenger: %s", event
            .getBookingNumber(), event.getFlightNumber(), event.getPassengerName()));
  }



}
