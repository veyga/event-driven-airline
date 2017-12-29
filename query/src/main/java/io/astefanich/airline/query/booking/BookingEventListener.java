package io.astefanich.airline.query.booking;

import io.astefanich.airline.common.domain.BookingStatus;
import io.astefanich.airline.common.events.BookingConfirmedEvent;
import io.astefanich.airline.common.events.BookingCreatedEvent;
import io.astefanich.airline.common.events.BookingRejectedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class BookingEventListener {

  private BookingRecordRepository repository;

  @Autowired
  public BookingEventListener(BookingRecordRepository repository) {
    this.repository = repository;
  }

  @EventHandler
  public void on(BookingCreatedEvent event) {
    BookingRecord record = new BookingRecord();
    record.setBookingNumber(event.getBookingNumber().identifier());
    record.setFlightNumber(event.getFlightNumber().identifier());
    record.setPassengerFullName(event.getPassengerName().fullname());
    record.setBookingDate(LocalDate.now().toString());
    record.setBookingStatus(BookingStatus.RECEIVED.toString());
    repository.save(record);
  }

  @EventHandler
  public void on(BookingConfirmedEvent event) {
    BookingRecord record = repository.findByBookingNumber(event.getBookingNumber().identifier());
    record.setBookingStatus(BookingStatus.CONFIRMED.toString());
    repository.save(record);
  }

  @EventHandler
  public void on(BookingRejectedEvent event) {
    BookingRecord record = repository.findByBookingNumber(event.getBookingNumber().identifier());
    record.setBookingStatus(BookingStatus.REJECTED.toString());
    repository.save(record);
  }


}
