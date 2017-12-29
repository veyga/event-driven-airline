package io.astefanich.airline.config;

import io.astefanich.airline.command.aggregate.booking.Booking;
import io.astefanich.airline.command.aggregate.booking.BookingCommandHandler;
import io.astefanich.airline.command.aggregate.customer.Customer;
import io.astefanich.airline.command.aggregate.customer.CustomerCommandHandler;
import io.astefanich.airline.command.aggregate.flight.Flight;
import io.astefanich.airline.command.aggregate.flight.FlightCommandHandler;
import io.astefanich.airline.command.saga.BookingManagerSaga;
import org.axonframework.config.SagaConfiguration;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

  @Autowired
  private AxonConfiguration axonConfiguration;

  @Autowired
  private EventBus eventBus;

  @Bean
  public FlightCommandHandler flightCommandHandlerCommandHandler() {
    return new FlightCommandHandler(axonConfiguration.repository(Flight.class), eventBus);
  }

  @Bean
  public CustomerCommandHandler customerCommandHandlerCommandHandler() {
    return new CustomerCommandHandler(axonConfiguration.repository(Customer.class), eventBus);
  }

  @Bean
  public BookingCommandHandler bookingCommandHandlerCommandHandler() {
    return new BookingCommandHandler(axonConfiguration.repository(Booking.class), eventBus);
  }

  @Bean
  public SagaConfiguration bookingManagerSagaConfiguration() {
    return SagaConfiguration.subscribingSagaManager(BookingManagerSaga.class);
  }
}
