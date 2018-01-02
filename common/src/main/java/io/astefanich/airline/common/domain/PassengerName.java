package io.astefanich.airline.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class PassengerName implements Serializable {

  private String firstName;

  private String lastName;

  public PassengerName(String firstName, String lastName) {
    this.firstName = firstName.toUpperCase();
    this.lastName = lastName.toUpperCase();
  }

  public String getFullName() {
    return String.format("%s,%s", lastName, firstName);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return getFullName();
  }
}
