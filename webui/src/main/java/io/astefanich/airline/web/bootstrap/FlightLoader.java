package io.astefanich.airline.web.bootstrap;

import io.astefanich.airline.common.commands.AddFlightToFlightScheduleCommand;
import io.astefanich.airline.common.domain.FlightNumber;
import io.astefanich.airline.common.domain.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
@Slf4j
@Profile("default")
public class FlightLoader implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private CommandGateway gateway;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    final String[] flights = {
            "AS1684,SEATTLE,DALLAS,2018-01-01,100",
            "AS9135,SEATTLE,DALLAS,2018-01-02,150",
            "AS8741,SEATTLE,DALLAS,2018-01-03,80",
            "AS3521,SEATTLE,DALLAS,2018-01-04,100",
            "AS9684,SEATTLE,DALLAS,2018-01-05,75",
            "AS8421,SEATTLE,DALLAS,2018-01-06,110",
            "AS9952,SEATTLE,DALLAS,2018-01-07,100",
            "AS3265,DALLAS,SEATTLE,2018-01-01,100",
            "AS7849,DALLAS,SEATTLE,2018-01-02,150",
            "AS2154,DALLAS,SEATTLE,2018-01-03,80",
            "AS1235,DALLAS,SEATTLE,2018-01-04,100",
            "AS9458,DALLAS,SEATTLE,2018-01-05,75",
            "AS7784,DALLAS,SEATTLE,2018-01-06,110",
            "AS3325,DALLAS,SEATTLE,2018-01-07,100",
            "AS0000,DALLAS,SEATTLE,2018-01-01,0",
    };
    final int flightNumI = 0;
    final int originI = 1;
    final int destinationI = 2;
    final int dateI = 3;
    final int capacityI = 4;
    for (int i = 0; i < flights.length; i++) {
      final String[] tokens = flights[i].split(",");
      FlightNumber flightNumber = new FlightNumber(tokens[flightNumI]);
      final String[] dateTokens = tokens[dateI].split("-");
      Date flightDate = java.sql.Date.valueOf(LocalDate.of(Integer.parseInt(dateTokens[0]), Integer
              .parseInt(dateTokens[1]), Integer.parseInt(dateTokens[2])));
      Schedule schedule = new Schedule(tokens[originI], tokens[destinationI], flightDate);
      gateway.send(new AddFlightToFlightScheduleCommand(flightNumber, schedule, Integer.parseInt(tokens[capacityI])));
    }
  }
}

//            "AS1684,SEATTLE,DALLAS,01-JAN-18,100",
//            "AS9135,SEATTLE,DALLAS,02-JAN-18,150",
//            "AS8741,SEATTLE,DALLAS,03-JAN-18,80",
//            "AS3521,SEATTLE,DALLAS,04-JAN-18,100",
//            "AS9684,SEATTLE,DALLAS,05-JAN-18,75",
//            "AS8421,SEATTLE,DALLAS,06-JAN-18,110",
//            "AS9952,SEATTLE,DALLAS,07-JAN-18,100",
//            "AS3265,DALLAS,SEATTLE,01-JAN-18,100",
//            "AS7849,DALLAS,SEATTLE,02-JAN-18,150",
//            "AS2154,DALLAS,SEATTLE,03-JAN-18,80",
//            "AS1235,DALLAS,SEATTLE,04-JAN-18,100",
//            "AS9458,DALLAS,SEATTLE,05-JAN-18,75",
//            "AS7784,DALLAS,SEATTLE,06-JAN-18,110",
//            "AS3325,DALLAS,SEATTLE,07-JAN-18,100",
//            "AS0000,DALLAS,SEATTLE,08-JAN-18,0",
