package io.astefanich.airline.command.aggregate.flight;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.domain.Schedule;
import io.astefanich.airline.common.events.FlightAddedToFlightScheduleEvent;
import io.astefanich.airline.common.events.PassengerAddedToFlightEvent;
import io.astefanich.airline.common.events.PassengerFlightAssignmentFailedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
public class Flight {

  @AggregateIdentifier
  private FlightNumber flightNumber;

  private Schedule schedule;

  private int capacity;

  private List<PassengerName> manifest;

  public Flight(FlightNumber flightNumber, Schedule schedule, int capacity) {
    apply(new FlightAddedToFlightScheduleEvent(flightNumber, schedule, capacity));
  }

  void addPassengerToManifest(BookingNumber bookingNumber, PassengerName passenger) {
    if (capacity > 0) {
      apply(new PassengerAddedToFlightEvent(bookingNumber, flightNumber, passenger));
    } else {
      apply(new PassengerFlightAssignmentFailedEvent(bookingNumber, flightNumber, passenger));
    }
  }

  @EventSourcingHandler
  public void on(FlightAddedToFlightScheduleEvent event) {
    this.flightNumber = event.getFlightNumber();
    this.schedule = event.getSchedule();
    this.capacity = event.getNumberOfSeats();
    this.manifest = new ArrayList<>(capacity);
  }

  @EventSourcingHandler
  public void on(PassengerAddedToFlightEvent event) {
    manifest.add(event.getPassengerName());
    capacity--;
  }

}
