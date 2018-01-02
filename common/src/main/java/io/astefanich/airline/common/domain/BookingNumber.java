package io.astefanich.airline.common.domain;

import lombok.EqualsAndHashCode;
import org.axonframework.common.IdentifierFactory;

import java.io.Serializable;

@EqualsAndHashCode
public class BookingNumber implements Serializable {

  private String identifier;

  public BookingNumber() {
    this.identifier = IdentifierFactory.getInstance().generateIdentifier().toUpperCase();
  }

  public BookingNumber(String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }

  @Override
  public String toString() {
    return identifier;
  }

}