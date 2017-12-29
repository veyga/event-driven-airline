package io.astefanich.airline.query.queries;

import io.astefanich.airline.common.domain.PassengerName;

public class QueryBookingsByPassengerName {

  private PassengerName passengerName;

  public QueryBookingsByPassengerName(PassengerName passengerName){
    this.passengerName = passengerName;
  }

  public PassengerName name(){
    return passengerName;
  }
}
