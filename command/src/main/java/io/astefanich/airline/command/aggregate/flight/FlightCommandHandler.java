package io.astefanich.airline.command.aggregate.flight;

import io.astefanich.airline.common.commands.AddFlightToFlightScheduleCommand;
import io.astefanich.airline.common.commands.AddPassengerToFlightCommand;
import io.astefanich.airline.common.events.PassengerFlightAssignmentFailedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

@Slf4j
@NoArgsConstructor
public class FlightCommandHandler {

  private Repository<Flight> flightRepository;

  private EventBus eventBus;

  public FlightCommandHandler(Repository<Flight> flightRepository, EventBus eventBus) {
    this.flightRepository = flightRepository;
    this.eventBus = eventBus;
  }

  @CommandHandler
  public void handle(AddFlightToFlightScheduleCommand command) throws Exception {
    flightRepository.newInstance(() -> new Flight(command.getFlightNumber(), command.getSchedule(),
            command.getNumberOfSeats()));
  }

  @CommandHandler
  public void handle(AddPassengerToFlightCommand command) {
    try {
      Aggregate<Flight> flight = flightRepository.load(command.getFlightNumber().getIdentifier());
      flight.execute(aggregateRoot -> {
        aggregateRoot.addPassengerToManifest(command.getBookingNumber(), command.getPassenger());
      });
    } catch (AggregateNotFoundException e) {
      eventBus.publish(asEventMessage(new PassengerFlightAssignmentFailedEvent(command
              .getBookingNumber(), command.getFlightNumber(), command.getPassenger())));
    }
  }

}