package io.astefanich.airline.query.booking;

import io.astefanich.airline.common.domain.PassengerName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {

  BookingRecord findByBookingNumber(String bookingNumber);

  List<BookingRecord> findByFlightNumberAndPassengerFullName(String flightNumber, String passengerFullName);

  List<BookingRecord> findByFlightNumber(String flightNumber);

  List<BookingRecord> findByPassengerFullName(String passengerFullName);
}
