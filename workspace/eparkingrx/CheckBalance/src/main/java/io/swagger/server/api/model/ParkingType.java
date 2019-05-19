package io.swagger.server.api.model;

import java.util.Objects;

/**
 * DEADLINE is parking until specific hour, PERIOD is parking for specific time
 **/
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * DEADLINE is parking until specific hour, PERIOD is parking for specific time
 */
public enum ParkingType {
  
  DEADLINE("DEADLINE"),
  
  PERIOD("PERIOD");

  private String value;

  ParkingType(String value) {
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

  public static ParkingType fromValue(String text) {
    for (ParkingType b : ParkingType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}