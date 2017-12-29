package io.astefanich.airline.query.booking;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.query.queries.QueryBookingsByBookingNumber;
import io.astefanich.airline.query.queries.QueryBookingsByFlightNumber;
import io.astefanich.airline.query.queries.QueryBookingsByPassengerName;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingQueryHandler {

  private BookingRecordRepository repository;

  @Autowired
  public BookingQueryHandler(BookingRecordRepository repository) {
    this.repository = repository;
  }

  @QueryHandler
  public BookingNumber handle(QueryBookingsByBookingNumber query) {
    return null;
  }

  @QueryHandler
  public List<BookingNumber> handle(QueryBookingsByFlightNumber query) {
    return null;

  }

  @QueryHandler
  public List<BookingRecord> handle(QueryBookingsByPassengerName query) {
    return null;
  }
}
