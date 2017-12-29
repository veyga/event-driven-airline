package io.astefanich.airline.command.saga;

import io.astefanich.airline.common.commands.*;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Saga
@Slf4j
public class BookingManagerSaga implements Serializable {

  private BookingNumber bookingNumber;

  private FlightNumber flightNumber;

  private PassengerName passengerName;

  private transient CommandGateway commandGateway;

  @StartSaga
  @SagaEventHandler(associationProperty = "bookingNumber")
  public void handle(BookingCreatedEvent event) {
    this.bookingNumber = event.getBookingNumber();
    this.flightNumber = event.getFlightNumber();
    this.passengerName = event.getPassengerName();
    commandGateway.send(new AddBookingToCustomerHistoryCommand(bookingNumber, flightNumber, passengerName));
  }

  @SagaEventHandler(associationProperty = "bookingNumber")
  public void handle(CustomerNotRegisteredEvent event) {
    commandGateway.send(new RegisterCustomerCommand(bookingNumber, passengerName));
  }

  @SagaEventHandler(associationProperty = "bookingNumber")
  public void handle(CustomerRegisteredEvent event) {
    commandGateway.send(new AddBookingToCustomerHistoryCommand(bookingNumber, flightNumber, passengerName));
  }

  @SagaEventHandler(associationProperty = "bookingNumber")
  public void handle(BookingAddedToCustomerHistoryEvent event) {
    commandGateway.send(new AddPassengerToFlightCommand(bookingNumber, flightNumber, passengerName));
  }

  @SagaEventHandler(associationProperty = "bookingNumber")
  @EndSaga
  public void handle(PassengerAddedToFlightEvent event) {
    commandGateway.send(new MarkBookingConfirmedCommand(bookingNumber, flightNumber, passengerName));
  }

  @SagaEventHandler(associationProperty = "bookingNumber")
  @EndSaga
  public void handle(PassengerFlightAssignmentFailedEvent event) {
    commandGateway.send(new MarkBookingRejectedCommand(bookingNumber, flightNumber, passengerName));
  }

  public PassengerName getPassengerName() {
    return passengerName;
  }

  public BookingNumber getBookingNumber() {
    return bookingNumber;
  }

  public FlightNumber getFlightNumber() {
    return flightNumber;
  }

  public CommandGateway getCommandGateway() {
    return commandGateway;
  }

  @Autowired
  public void setCommandGateway(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

}
