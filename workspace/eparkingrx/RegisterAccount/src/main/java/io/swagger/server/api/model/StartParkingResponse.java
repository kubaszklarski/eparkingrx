package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * information about amount charged for parking
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class StartParkingResponse   {
  
  private String parkingId = null;

  public StartParkingResponse () {

  }

  public StartParkingResponse (String parkingId) {
    this.parkingId = parkingId;
  }

    
  @JsonProperty("ParkingId")
  public String getParkingId() {
    return parkingId;
  }
  public void setParkingId(String parkingId) {
    this.parkingId = parkingId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StartParkingResponse startParkingResponse = (StartParkingResponse) o;
    return Objects.equals(parkingId, startParkingResponse.parkingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parkingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartParkingResponse {\n");
    
    sb.append("    parkingId: ").append(toIndentedString(parkingId)).append("\n");
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
