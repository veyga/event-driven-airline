package io.astefanich.airline.query.flight;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
public class FlightEntry {

  @Id
  @GeneratedValue
  private Long id;

  private String flightNumber;

  private String origin;

  private String destination;

  private Date flightDate;

  private String availableCapacity;

}
