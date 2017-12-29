package io.astefanich.airline.common.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
public class Schedule {

  private String origin;

  private String destination;

  private Date departureDate;


  public Schedule(String origin, String destination, Date departureDate) {
    this.origin = origin.toUpperCase();
    this.destination = destination.toUpperCase();
    this.departureDate = departureDate;
  }

}