package io.astefanich.airline.common.events;

import io.astefanich.airline.common.domain.BookingNumber;

import java.io.Serializable;

public abstract class AbstractBookingEvent {

  private BookingNumber bookingNumber;

  public AbstractBookingEvent(BookingNumber bookingNumber){
    this.bookingNumber = bookingNumber;
  }

  public BookingNumber getBookingNumber() {
    return bookingNumber;
  }
}
