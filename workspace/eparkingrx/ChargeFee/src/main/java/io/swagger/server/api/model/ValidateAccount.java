package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.Client;
import io.swagger.server.api.model.Vehicle;
import java.math.BigDecimal;

/**
 * information required to perform account validation
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ValidateAccount   {
  
  private BigDecimal amount = null;
  private Client client = null;
  private Vehicle vehicle = null;

  public ValidateAccount () {

  }

  public ValidateAccount (BigDecimal amount, Client client, Vehicle vehicle) {
    this.amount = amount;
    this.client = client;
    this.vehicle = vehicle;
  }

    
  @JsonProperty("Amount")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

    
  @JsonProperty("Client")
  public Client getClient() {
    return client;
  }
  public void setClient(Client client) {
    this.client = client;
  }

    
  @JsonProperty("Vehicle")
  public Vehicle getVehicle() {
    return vehicle;
  }
  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidateAccount validateAccount = (ValidateAccount) o;
    return Objects.equals(amount, validateAccount.amount) &&
        Objects.equals(client, validateAccount.client) &&
        Objects.equals(vehicle, validateAccount.vehicle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, client, vehicle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidateAccount {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    client: ").append(toIndentedString(client)).append("\n");
    sb.append("    vehicle: ").append(toIndentedString(vehicle)).append("\n");
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
