package io.astefanich.airline.web.controller;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.query.booking.BookingRecord;
import io.astefanich.airline.query.flight.FlightSchedule;
import io.astefanich.airline.query.queries.QueryBookingsByBookingNumber;
import io.astefanich.airline.query.queries.QueryBookingsByFlightNumber;
import io.astefanich.airline.query.queries.QueryFlightScheduleForFlightCandidates;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController("/search")
@Slf4j
public class QueryController {

  @Autowired
  private QueryGateway gateway;


  @PostMapping("/flights")
  public CompletableFuture<FlightSchedule> searchForFlights(@RequestBody QueryFlightScheduleForFlightCandidates query) {
    return gateway.send(query, FlightSchedule.class);
  }

  @GetMapping("/bookingrecords/bookingnumber{bookingNumber}")
  public CompletableFuture<BookingRecord> searchBookingRecordsByBookingNumber(@PathVariable String bookingNumber) {
    QueryBookingsByBookingNumber query = new QueryBookingsByBookingNumber(new BookingNumber(bookingNumber));
    return gateway.send(query, BookingRecord.class);
  }

  @GetMapping("/bookingrecords/flightnumber{flightNumber}")
  public CompletableFuture<?> searchBookingRecordsByFlightNumber(@PathVariable String flightNumber) {
    QueryBookingsByFlightNumber query = new QueryBookingsByFlightNumber(new FlightNumber(flightNumber));
    return gateway.send(query, List.class);
  }

}
