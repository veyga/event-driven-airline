package io.astefanich.airline.query.flight;

import io.astefanich.airline.query.queries.QueryFlightScheduleForFlightCandidates;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FlightQueryHandler {

  private Predicate<FlightEntry> inventoryCheck = (f) -> Integer.parseInt(f.getAvailableCapacity()) > 0;

  @Autowired
  private FlightEntryRepository repository;

  @QueryHandler
  public FlightSchedule handle(QueryFlightScheduleForFlightCandidates query) {
    List<FlightEntry> flightEntries = repository.findByOriginAndDestination(query.getOrigin(), query.getDestination());
    return new FlightSchedule(flightEntries.stream().filter(inventoryCheck).collect(Collectors.toList()));
  }

}
