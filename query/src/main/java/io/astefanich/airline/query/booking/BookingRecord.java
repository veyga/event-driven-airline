package io.astefanich.airline.query.booking;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Data
public class BookingRecord {

  @Id
  @GeneratedValue
  private Long id;

  private String bookingNumber;

  private String flightNumber;

  private String passengerFullName;

  private String bookingDate;

  private String bookingStatus;

}
