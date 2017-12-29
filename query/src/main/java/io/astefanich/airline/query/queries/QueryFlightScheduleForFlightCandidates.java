package io.astefanich.airline.query.queries;

import lombok.Value;

@Value
public class QueryFlightScheduleForFlightCandidates {

  private String origin;

  private String destination;

  private String flightDate;


}
