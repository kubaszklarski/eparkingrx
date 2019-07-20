package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.ParkingStatus;
import io.swagger.server.api.model.ParkingType;
import java.math.BigDecimal;

/**
 * information about amount charged for parking
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class StartParking   {
  
  private String accountId = null;
  private ParkingType parkingType = null;
  private ParkingStatus parkingStatus = null;
  private String parkingStartTime = null;
  private String parkingEndTime = null;
  private BigDecimal parkingPeriod = null;

  public StartParking () {

  }

  public StartParking (String accountId, ParkingType parkingType, ParkingStatus parkingStatus, String parkingStartTime, String parkingEndTime, BigDecimal parkingPeriod) {
    this.accountId = accountId;
    this.parkingType = parkingType;
    this.parkingStatus = parkingStatus;
    this.parkingStartTime = parkingStartTime;
    this.parkingEndTime = parkingEndTime;
    this.parkingPeriod = parkingPeriod;
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
    StartParking startParking = (StartParking) o;
    return Objects.equals(accountId, startParking.accountId) &&
        Objects.equals(parkingType, startParking.parkingType) &&
        Objects.equals(parkingStatus, startParking.parkingStatus) &&
        Objects.equals(parkingStartTime, startParking.parkingStartTime) &&
        Objects.equals(parkingEndTime, startParking.parkingEndTime) &&
        Objects.equals(parkingPeriod, startParking.parkingPeriod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, parkingType, parkingStatus, parkingStartTime, parkingEndTime, parkingPeriod);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StartParking {\n");
    
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
