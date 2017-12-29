package io.astefanich.airline.query.queries;

public class QueryBookingsByBookingNumber {

  private String bookingNumber;

  public QueryBookingsByBookingNumber(String bookingNumber) {
    this.bookingNumber = bookingNumber;
  }

  public String number() {
    return bookingNumber;
  }
}
