package io.astefanich.airline.command.aggregate.booking;

import io.astefanich.airline.common.commands.CreateBookingCommand;
import io.astefanich.airline.common.commands.MarkBookingConfirmedCommand;
import io.astefanich.airline.common.commands.MarkBookingRejectedCommand;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.BookingConfirmedEvent;
import io.astefanich.airline.common.events.BookingCreatedEvent;
import io.astefanich.airline.common.events.BookingRejectedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class BookingCommandHandlerTest {

  private static final String FIRST_NAME = "John";

  private static final String LAST_NAME = "Smith";

  private AggregateTestFixture<Booking> fixture;

  private FlightNumber flightNumber;

  private PassengerName passengerName;

  private BookingNumber bookingNumber;

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture<>(Booking.class);
    BookingCommandHandler commandHandler = new BookingCommandHandler(fixture.getRepository(),
            fixture.getEventBus());
    fixture.registerAnnotatedCommandHandler(commandHandler);
    flightNumber = new FlightNumber();
    passengerName = new PassengerName(FIRST_NAME, LAST_NAME);
    bookingNumber = new BookingNumber();
  }

  @Test
  public void testCreateBooking() throws Exception {
    CreateBookingCommand command = new CreateBookingCommand(bookingNumber, flightNumber, passengerName);
    fixture.given()
            .when(command)
            .expectEvents(new BookingCreatedEvent(bookingNumber, flightNumber, passengerName));

  }

  @Test
  public void testMarkBookingConfirmed() throws Exception {
    MarkBookingConfirmedCommand command = new MarkBookingConfirmedCommand(bookingNumber, flightNumber, passengerName);
    fixture.given(new BookingCreatedEvent(bookingNumber, flightNumber, passengerName))
            .when(command)
            .expectEvents(new BookingConfirmedEvent(bookingNumber, flightNumber, passengerName));

  }

  @Test
  public void testMarkBookingRejected() throws Exception {
    MarkBookingRejectedCommand command = new MarkBookingRejectedCommand(bookingNumber, flightNumber, passengerName);
    fixture.given(new BookingCreatedEvent(bookingNumber, flightNumber, passengerName))
            .when(command)
            .expectEvents(new BookingRejectedEvent(bookingNumber, flightNumber, passengerName));

  }

}