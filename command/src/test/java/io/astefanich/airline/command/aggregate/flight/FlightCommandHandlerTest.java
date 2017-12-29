package io.astefanich.airline.command.aggregate.flight;

import io.astefanich.airline.common.commands.AddFlightToFlightScheduleCommand;
import io.astefanich.airline.common.commands.AddPassengerToFlightCommand;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.domain.Schedule;
import io.astefanich.airline.common.events.FlightAddedToFlightScheduleEvent;
import io.astefanich.airline.common.events.PassengerAddedToFlightEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class FlightCommandHandlerTest {


  private static final String ORIGIN = "SEATTLE";

  private static final String DESTINATION = "DALLAS";

  private static final Date DEPARTURE_DATE = new Date(12, 10, 2017);

  private static final int CAPACITY = 300;

  private static final String FIRST_NAME = "John";

  private static final String LAST_NAME = "Smith";

  private AggregateTestFixture<Flight> fixture;

  private FlightNumber flightNumber;

  private Schedule schedule;

  private PassengerName passenger;

  private BookingNumber bookingNumber;

  @Before
  public void setUp() throws Exception {
    fixture = new AggregateTestFixture<>(Flight.class);
    FlightCommandHandler commandHandler = new FlightCommandHandler(fixture.getRepository(),
            fixture.getEventBus());
    fixture.registerAnnotatedCommandHandler(commandHandler);
    schedule = new Schedule(ORIGIN, DESTINATION, DEPARTURE_DATE);
    flightNumber = new FlightNumber();
    passenger = new PassengerName(FIRST_NAME, LAST_NAME);
    bookingNumber = new BookingNumber();
  }

  @Test
  public void testAddFlightToFlightSchedule() throws Exception {
    AddFlightToFlightScheduleCommand command = new AddFlightToFlightScheduleCommand(flightNumber,
            schedule, CAPACITY);
    fixture.givenNoPriorActivity()
            .when(command)
            .expectEvents(new FlightAddedToFlightScheduleEvent(flightNumber, schedule, CAPACITY));
  }

  @Test
  public void testAddPassengerToFlightCommand() throws Exception {
    AddPassengerToFlightCommand command = new AddPassengerToFlightCommand(bookingNumber,
            flightNumber, passenger);
    fixture.given(new FlightAddedToFlightScheduleEvent(flightNumber, schedule, 10))
            .when(new AddPassengerToFlightCommand(bookingNumber, flightNumber, passenger))
            .expectEvents(new PassengerAddedToFlightEvent(bookingNumber, flightNumber, passenger));

  }

}