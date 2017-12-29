package io.astefanich.airline.web.controller;

import io.astefanich.airline.query.booking.BookingRecord;
import io.astefanich.airline.query.flight.FlightSchedule;
import io.astefanich.airline.query.queries.QueryBookingsByBookingNumber;
import io.astefanich.airline.query.queries.QueryFlightScheduleForFlightCandidates;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController("/search")
public class QueryController {

  @Autowired
  private QueryGateway gateway;


  @GetMapping("/flights")
  public CompletableFuture<FlightSchedule> searchForFlights(@RequestBody QueryFlightScheduleForFlightCandidates query) {
//    QueryFlightScheduleForFlightCandidates query = new QueryFlightScheduleForFlightCandidates
//            (searchCriteria.getOrigin(), searchCriteria.getDestination(), searchCriteria.getDestination());
    return gateway.send(query, FlightSchedule.class);
  }

  @GetMapping("/bookingrecords/{bookingNumber}")
  public CompletableFuture<BookingRecord> searchForBookingRecord(@PathVariable String bookingNumber) {
    QueryBookingsByBookingNumber query = new QueryBookingsByBookingNumber(bookingNumber);
    return gateway.send(query, BookingRecord.class);
  }
}
