package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.ParkingStatus;
import io.swagger.server.api.model.ParkingType;
import java.math.BigDecimal;

/**
 * information about parking activity
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Parking   {
  
  private String parkingId = null;
  private String accountId = null;
  private ParkingType parkingType = null;
  private ParkingStatus parkingStatus = null;
  private String parkingStartTime = null;
  private String parkingEndTime = null;
  private BigDecimal parkingPeriod = null;

  public Parking () {

  }

  public Parking (String parkingId, String accountId, ParkingType parkingType, ParkingStatus parkingStatus, String parkingStartTime, String parkingEndTime, BigDecimal parkingPeriod) {
    this.parkingId = parkingId;
    this.accountId = accountId;
    this.parkingType = parkingType;
    this.parkingStatus = parkingStatus;
    this.parkingStartTime = parkingStartTime;
    this.parkingEndTime = parkingEndTime;
    this.parkingPeriod = parkingPeriod;
  }

    
  @JsonProperty("ParkingId")
  public String getParkingId() {
    return parkingId;
  }
  public void setParkingId(String parkingId) {
    this.parkingId = parkingId;
  }

    
  @JsonProperty("AccountId")
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

    
  @JsonProperty("ParkingType")
  public ParkingType getParkingType() {
    return parkingType;
  }
  public void setParkingType(ParkingType parkingType) {
    this.parkingType = parkingType;
  }

    
  @JsonProperty("ParkingStatus")
  public ParkingStatus getParkingStatus() {
    return parkingStatus;
  }
  public void setParkingStatus(ParkingStatus parkingStatus) {
    this.parkingStatus = parkingStatus;
  }

    
  @JsonProperty("ParkingStartTime")
  public String getParkingStartTime() {
    return parkingStartTime;
  }
  public void setParkingStartTime(String parkingStartTime) {
    this.parkingStartTime = parkingStartTime;
  }

    
  @JsonProperty("ParkingEndTime")
  public String getParkingEndTime() {
    return parkingEndTime;
  }
  public void setParkingEndTime(String parkingEndTime) {
    this.parkingEndTime = parkingEndTime;
  }

    
  @JsonProperty("ParkingPeriod")
  public BigDecimal getParkingPeriod() {
    return parkingPeriod;
  }
  public void setParkingPeriod(BigDecimal parkingPeriod) {
    this.parkingPeriod = parkingPeriod;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Parking parking = (Parking) o;
    return Objects.equals(parkingId, parking.parkingId) &&
        Objects.equals(accountId, parking.accountId) &&
        Objects.equals(parkingType, parking.parkingType) &&
        Objects.equals(parkingStatus, parking.parkingStatus) &&
        Objects.equals(parkingStartTime, parking.parkingStartTime) &&
        Objects.equals(parkingEndTime, parking.parkingEndTime) &&
        Objects.equals(parkingPeriod, parking.parkingPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parkingId, accountId, parkingType, parkingStatus, parkingStartTime, parkingEndTime, parkingPeriod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Parking {\n");
    
    sb.append("    parkingId: ").append(toIndentedString(parkingId)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    parkingType: ").append(toIndentedString(parkingType)).append("\n");
    sb.append("    parkingStatus: ").append(toIndentedString(parkingStatus)).append("\n");
    sb.append("    parkingStartTime: ").append(toIndentedString(parkingStartTime)).append("\n");
    sb.append("    parkingEndTime: ").append(toIndentedString(parkingEndTime)).append("\n");
    sb.append("    parkingPeriod: ").append(toIndentedString(parkingPeriod)).append("\n");
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
