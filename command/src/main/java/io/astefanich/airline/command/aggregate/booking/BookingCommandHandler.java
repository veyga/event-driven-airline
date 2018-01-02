package io.astefanich.airline.command.aggregate.booking;

import io.astefanich.airline.common.commands.CreateBookingCommand;
import io.astefanich.airline.common.commands.MarkBookingConfirmedCommand;
import io.astefanich.airline.common.commands.MarkBookingRejectedCommand;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;


@Slf4j
@NoArgsConstructor
public class BookingCommandHandler {

  private Repository<Booking> bookingRepository;

  private EventBus eventBus;


  public BookingCommandHandler(Repository<Booking> bookingRepository, EventBus eventBus) {
    this.bookingRepository = bookingRepository;
    this.eventBus = eventBus;
  }


  @CommandHandler
  public void handle(CreateBookingCommand command) throws Exception {
    bookingRepository.newInstance(() -> new Booking(command.getBookingNumber(), command.getFlightNumber
            (), command.getPassengerName()));
  }

  @CommandHandler
  public void handle(MarkBookingConfirmedCommand command) throws Exception {
    Aggregate<Booking> booking = bookingRepository.load(command.getBookingNumber().getIdentifier());
    booking.execute(aggregate -> {
      aggregate.markBookingConfirmed();
    });
  }

  @CommandHandler
  public void handle(MarkBookingRejectedCommand command) throws Exception {
    Aggregate<Booking> booking = bookingRepository.load(command.getBookingNumber().getIdentifier());
    booking.execute(aggregate -> {
      aggregate.markBookingRejected();
    });
  }


}