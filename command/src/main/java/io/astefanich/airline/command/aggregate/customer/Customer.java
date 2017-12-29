package io.astefanich.airline.command.aggregate.customer;

import io.astefanich.airline.common.commands.RegisterCustomerCommand;
import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.BookingAddedToCustomerHistoryEvent;
import io.astefanich.airline.common.events.CustomerRegisteredEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@Slf4j
@NoArgsConstructor
public class Customer {

  @AggregateIdentifier
  private PassengerName passengerName;

  private List<BookingNumber> purchaseHistory;

  public Customer(RegisterCustomerCommand command) {
    apply(new CustomerRegisteredEvent(command.getBookingNumber(), command.getPassengerName()));
  }

  void addBookingToHistory(BookingNumber bookingNumber) {
    apply(new BookingAddedToCustomerHistoryEvent(bookingNumber, passengerName));
  }

  @EventSourcingHandler
  public void on(CustomerRegisteredEvent event) {
    this.passengerName = event.getPassengerName();
    this.purchaseHistory = new ArrayList<>();
  }

  @EventSourcingHandler
  public void on(BookingAddedToCustomerHistoryEvent event) {
    purchaseHistory.add(event.getBookingNumber());
  }


}
