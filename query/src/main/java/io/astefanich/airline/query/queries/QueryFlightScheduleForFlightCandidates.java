package io.astefanich.airline.query.queries;

import lombok.Data;

@Data
public class QueryFlightScheduleForFlightCandidates {

  private String origin;

  private String destination;

}
