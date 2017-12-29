package io.astefanich.airline.common.commands;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.PassengerName;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Value
public class RegisterCustomerCommand {

  private BookingNumber bookingNumber;

  @TargetAggregateIdentifier
  private PassengerName passengerName;
}
