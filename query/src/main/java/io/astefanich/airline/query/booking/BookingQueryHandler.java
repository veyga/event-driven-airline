package io.astefanich.airline.query.booking;

import io.astefanich.airline.query.queries.QueryBookingsByBookingNumber;
import io.astefanich.airline.query.queries.QueryBookingsByFlightNumber;
import io.astefanich.airline.query.queries.QueryBookingsByPassengerName;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingQueryHandler {

  private BookingRecordRepository repository;

  @Autowired
  public BookingQueryHandler(BookingRecordRepository repository) {
    this.repository = repository;
  }

  @QueryHandler
  public BookingRecord handle(QueryBookingsByBookingNumber query) {
    return repository.findByBookingNumber(query.getBookingNumber().getIdentifier());
  }

  @QueryHandler
  public List<BookingRecord> handle(QueryBookingsByFlightNumber query) {
    return repository.findByFlightNumber(query.getFlightNumber().getIdentifier());
  }

  @QueryHandler
  public List<BookingRecord> handle(QueryBookingsByPassengerName query) {
    return repository.findByPassengerFullName(query.getPassengerName().getFullName());
  }
}
