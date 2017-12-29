package io.astefanich.airline.web.request;

import lombok.Value;

@Value
public class BookTicketRequest {

  private String flightNumber;

  private String passengerFirstName;

  private String passengerLastName;


}