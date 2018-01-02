package io.astefanich.airline.query.queries;

import io.astefanich.airline.common.domain.BookingNumber;

public class QueryBookingsByBookingNumber {

  private BookingNumber bookingNumber;

  public QueryBookingsByBookingNumber(BookingNumber bookingNumber) {
    this.bookingNumber = bookingNumber;
  }

  public BookingNumber getBookingNumber() {
    return bookingNumber;
  }
}
