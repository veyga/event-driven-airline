package io.astefanich.airline.command.aggregate.customer;

import io.astefanich.airline.common.commands.AddBookingToCustomerHistoryCommand;
import io.astefanich.airline.common.commands.RegisterCustomerCommand;
import io.astefanich.airline.common.events.CustomerNotRegisteredEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;

import static org.axonframework.eventhandling.GenericEventMessage.asEventMessage;

@NoArgsConstructor
@Slf4j
public class CustomerCommandHandler {

  private Repository<Customer> customerRepository;

  private EventBus eventBus;

  public CustomerCommandHandler(Repository<Customer> customerRepository, EventBus eventBus) {
    this.customerRepository = customerRepository;
    this.eventBus = eventBus;
  }


  @CommandHandler
  public void handle(RegisterCustomerCommand command) throws Exception {
    customerRepository.newInstance(() -> new Customer(command));
  }


  @CommandHandler
  public void handle(AddBookingToCustomerHistoryCommand command) {
    try {
      Aggregate<Customer> passenger = customerRepository.load(command.getPassengerName().fullname());
      passenger.execute(aggregateRoot -> {
        aggregateRoot.addBookingToHistory(command.getBookingNumber());
      });
    } catch (AggregateNotFoundException e) {
      eventBus.publish(asEventMessage(new CustomerNotRegisteredEvent(command.getBookingNumber(), command.getPassengerName())));
    }
  }

}
