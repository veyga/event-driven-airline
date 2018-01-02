package io.astefanich.airline.query.flight;

import io.astefanich.airline.common.domain.Schedule;
import io.astefanich.airline.common.events.BookingConfirmedEvent;
import io.astefanich.airline.common.events.FlightAddedToFlightScheduleEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FlightEntryEventListener {

  private FlightEntryRepository repository;

  @Autowired
  public FlightEntryEventListener(FlightEntryRepository repository) {
    this.repository = repository;
  }

  @EventHandler
  public void handle(FlightAddedToFlightScheduleEvent event) {
    FlightEntry entry = new FlightEntry();
    entry.setFlightNumber(event.getFlightNumber().getIdentifier());
    Schedule schedule = event.getSchedule();
    entry.setOrigin(schedule.getOrigin());
    entry.setDestination(schedule.getDestination());
    entry.setFlightDate(schedule.getDepartureDate());
    entry.setAvailableCapacity(Integer.valueOf(event.getNumberOfSeats()).toString());
    repository.save(entry);
  }

  @EventHandler
  public void on(BookingConfirmedEvent event) {
    String flightNumber = event.getFlightNumber().getIdentifier();
    FlightEntry entry = repository.findByFlightNumber(event.getFlightNumber().getIdentifier());
    int currentAvailability = Integer.parseInt(entry.getAvailableCapacity());
    entry.setAvailableCapacity(Integer.valueOf(--currentAvailability).toString());
    repository.save(entry);
  }
}
