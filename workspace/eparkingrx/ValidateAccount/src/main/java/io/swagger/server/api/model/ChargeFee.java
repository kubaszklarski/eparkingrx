package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.Parking;

/**
 * information about amount charged for parking
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ChargeFee   {
  
  private Parking parking = null;

  public ChargeFee () {

  }

  public ChargeFee (Parking parking) {
    this.parking = parking;
  }

    
  @JsonProperty("Parking")
  public Parking getParking() {
    return parking;
  }
  public void setParking(Parking parking) {
    this.parking = parking;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChargeFee chargeFee = (ChargeFee) o;
    return Objects.equals(parking, chargeFee.parking);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parking);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChargeFee {\n");
    
    sb.append("    parking: ").append(toIndentedString(parking)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
