package io.astefanich.airline.command.saga;

import io.astefanich.airline.common.commands.AddBookingToCustomerHistoryCommand;
import io.astefanich.airline.common.commands.AddPassengerToFlightCommand;
import io.astefanich.airline.common.commands.MarkBookingConfirmedCommand;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.BookingAddedToCustomerHistoryEvent;
import io.astefanich.airline.common.events.BookingCreatedEvent;
import io.astefanich.airline.common.events.PassengerAddedToFlightEvent;
import org.axonframework.test.saga.FixtureConfiguration;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;

public class BookingManagerSagaTest {


  private static final String FIRST_NAME = "John";

  private static final String LAST_NAME = "Smith";


  private PassengerName passengerName = new PassengerName(FIRST_NAME, LAST_NAME);

  private FlightNumber flightNumber = new FlightNumber();

  private BookingNumber bookingNumber = new BookingNumber();

  private FixtureConfiguration fixture;


  @Before
  public void setUp() throws Exception {
    fixture = new SagaTestFixture<>(BookingManagerSaga.class);
  }

  @Test
  public void testBookingCreated() throws Exception {
    fixture.givenAggregate(bookingNumber.identifier()).published()
            .whenAggregate(bookingNumber.identifier()).publishes(new BookingCreatedEvent(bookingNumber,
            flightNumber, passengerName)).expectActiveSagas(1)
            .expectDispatchedCommands(new AddBookingToCustomerHistoryCommand(bookingNumber,
                    flightNumber, passengerName));
  }

  @Test
  public void testBookingAddedToCustomerHistory() throws Exception {
    fixture.givenAggregate(bookingNumber.identifier()).published()
            .andThenAggregate(flightNumber.identifier()).published(new PassengerAddedToFlightEvent
            (bookingNumber, flightNumber, passengerName)).whenAggregate(passengerName.fullname())
            .publishes(new BookingAddedToCustomerHistoryEvent(bookingNumber, passengerName))
            .expectActiveSagas(0);

  }

  @Test
  public void testPassengerAddedToFlight() throws Exception {
    fixture.givenAggregate(bookingNumber.identifier()).published(new BookingCreatedEvent
            (bookingNumber, flightNumber, passengerName))
            .andThenAggregate(passengerName.fullname()).published(new
            BookingAddedToCustomerHistoryEvent(bookingNumber, passengerName)).whenAggregate
            (flightNumber.identifier()).publishes(new PassengerAddedToFlightEvent(bookingNumber,
            flightNumber, passengerName)).expectDispatchedCommands(new
            MarkBookingConfirmedCommand(bookingNumber, flightNumber, passengerName));
  }


}