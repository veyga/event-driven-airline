package io.astefanich.airline.command.aggregate.customer;

import io.astefanich.airline.common.commands.AddBookingToCustomerHistoryCommand;
import io.astefanich.airline.common.commands.RegisterCustomerCommand;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.BookingAddedToCustomerHistoryEvent;
import io.astefanich.airline.common.events.CustomerRegisteredEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class CustomerCommandHandlerTest {

  private static final String FIRST_NAME = "John";

  private static final String LAST_NAME = "Smith";

  private FlightNumber flightNumber;

  private PassengerName passengerName;

  private BookingNumber bookingNumber;

  private AggregateTestFixture<Customer> fixture;

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture<>(Customer.class);
    CustomerCommandHandler commandHandler = new CustomerCommandHandler(fixture.getRepository(),
            fixture.getEventBus());
    fixture.registerAnnotatedCommandHandler(commandHandler);
    passengerName = new PassengerName(FIRST_NAME, LAST_NAME);
    bookingNumber = new BookingNumber();
    flightNumber = new FlightNumber();
  }

  @Test
  public void testRegisterCustomer() throws Exception {
    fixture.givenNoPriorActivity()
            .when(new RegisterCustomerCommand(bookingNumber, passengerName))
            .expectEvents(new CustomerRegisteredEvent(bookingNumber, passengerName));
  }

  @Test
  public void testAddBookingToPassengerHistory() throws Exception {
    fixture.given(new CustomerRegisteredEvent(bookingNumber, passengerName))
            .when(new AddBookingToCustomerHistoryCommand(bookingNumber, flightNumber, passengerName))
            .expectEvents(new BookingAddedToCustomerHistoryEvent(bookingNumber, passengerName));
  }

}