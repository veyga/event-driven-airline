package io.astefanich.airline.command.saga;

import io.astefanich.airline.common.commands.AddBookingToCustomerHistoryCommand;
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
    fixture.givenAggregate(bookingNumber.getIdentifier()).published()
            .whenAggregate(bookingNumber.getIdentifier()).publishes(new BookingCreatedEvent(bookingNumber,
            flightNumber, passengerName)).expectActiveSagas(1)
            .expectDispatchedCommands(new AddBookingToCustomerHistoryCommand(bookingNumber,
                    flightNumber, passengerName));
  }

  @Test
  public void testBookingAddedToCustomerHistory() throws Exception {
    fixture.givenAggregate(bookingNumber.getIdentifier()).published()
            .andThenAggregate(flightNumber.getIdentifier()).published(new PassengerAddedToFlightEvent
            (bookingNumber, flightNumber, passengerName)).whenAggregate(passengerName.getFullName())
            .publishes(new BookingAddedToCustomerHistoryEvent(bookingNumber, passengerName))
            .expectActiveSagas(0);

  }

  @Test
  public void testPassengerAddedToFlight() throws Exception {
    fixture.givenAggregate(bookingNumber.getIdentifier()).published(new BookingCreatedEvent
            (bookingNumber, flightNumber, passengerName))
            .andThenAggregate(passengerName.getFullName()).published(new
            BookingAddedToCustomerHistoryEvent(bookingNumber, passengerName)).whenAggregate
            (flightNumber.getIdentifier()).publishes(new PassengerAddedToFlightEvent(bookingNumber,
            flightNumber, passengerName)).expectDispatchedCommands(new
            MarkBookingConfirmedCommand(bookingNumber, flightNumber, passengerName));
  }


}