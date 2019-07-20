package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.Client;
import io.swagger.server.api.model.Vehicle;
import java.math.BigDecimal;

/**
 * information about clients account
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class RegisterAccount   {
  
  private BigDecimal amount = null;
  private Client client = null;
  private Vehicle vehicle = null;

  public RegisterAccount () {

  }

  public RegisterAccount (BigDecimal amount, Client client, Vehicle vehicle) {
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
    RegisterAccount registerAccount = (RegisterAccount) o;
    return Objects.equals(amount, registerAccount.amount) &&
        Objects.equals(client, registerAccount.client) &&
        Objects.equals(vehicle, registerAccount.vehicle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, client, vehicle);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterAccount {\n");
    
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
