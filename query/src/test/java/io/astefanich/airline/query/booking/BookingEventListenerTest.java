package io.astefanich.airline.query.booking;

import io.astefanich.airline.common.domain.BookingNumber;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.PassengerName;
import io.astefanich.airline.common.events.BookingConfirmedEvent;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;

public class BookingEventListenerTest {

  private static final String BOOKING_NUMBER = "1234567";

  private static final String FLIGHT_NUMBER = "SW8491";

  private static final String FIRST_NAME = "John";

  private static final String LAST_NAME = "Doe";

  private PassengerName passenger;

  private BookingNumber bookingNumber;

  private FlightNumber flightNumber;

  private BookingRecordRepository repository;

  private BookingEventListener listener;

  private BookingRecord bookingRecord;
//
//  @Before
//  public void setUp() throws Exception {
//    repository = mock(BookingRecordRepository.class);
//    listener = new BookingEventListener(repository);
//
//    bookingNumber = new BookingNumber(BOOKING_NUMBER);
//    flightNumber = new FlightNumber(FLIGHT_NUMBER);
//    passenger = new PassengerName(FIRST_NAME, LAST_NAME);
//    bookingRecord = createRecord();
//
////    Mockito.when(repository.findByBookingNumber(BOOKING_NUMBER)).thenReturn(bookingRecord);
////    Mockito.when(repository.findByFlightNumber(FLIGHT_NUMBER)).thenReturn(bookingRecord);
//  }
//
//  @Test
//  public void testBookingConfirmedEvent() throws Exception {
//    BookingConfirmedEvent event = new BookingConfirmedEvent(bookingNumber, flightNumber,
//            passenger, LocalDate.now());
//    listener.on(event);
//
////    Mockito.verify(repository).save(Matchers.argThat())
//  }
//
//  private BookingRecord createRecord() {
//    BookingRecord record = new BookingRecord();
//    record.setBookingNumber(BOOKING_NUMBER);
//    record.setFlightNumber(FLIGHT_NUMBER);
//    record.setPassengerName(passenger);
//    record.setBookingDate(LocalDate.of(12, 10, 2017));
//    return record;
//  }
}