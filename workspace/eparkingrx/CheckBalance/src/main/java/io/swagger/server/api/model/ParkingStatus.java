package io.swagger.server.api.model;

import java.util.Objects;

/**
 * ACTIVE is current parking, COMPLETED is after parking ends, ENDED is before parking ends
 **/
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * ACTIVE is current parking, COMPLETED is after parking ends, ENDED is before parking ends
 */
public enum ParkingStatus {
  
  ACTIVE("ACTIVE"),
  
  COMPLETED("COMPLETED"),
  
  ENDED("ENDED");

  private String value;

  ParkingStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static ParkingStatus fromValue(String text) {
    for (ParkingStatus b : ParkingStatus.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}