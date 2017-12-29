package io.astefanich.airline.common.events;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.PassengerName;
import lombok.Getter;

@Getter
public class BookingAddedToCustomerHistoryEvent extends AbstractBookingEvent {

  private PassengerName passengerName;

  public BookingAddedToCustomerHistoryEvent(BookingNumber bookingNumber, PassengerName passengerName) {
    super(bookingNumber);
    this.passengerName = passengerName;
  }


}
