package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * details of clients identification information
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Client   {
  
  private String firstName = null;
  private String lastName = null;
  private String pesel = null;

  public Client () {

  }

  public Client (String firstName, String lastName, String pesel) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.pesel = pesel;
  }

    
  @JsonProperty("FirstName")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

    
  @JsonProperty("LastName")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

    
  @JsonProperty("Pesel")
  public String getPesel() {
    return pesel;
  }
  public void setPesel(String pesel) {
    this.pesel = pesel;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Client client = (Client) o;
    return Objects.equals(firstName, client.firstName) &&
        Objects.equals(lastName, client.lastName) &&
        Objects.equals(pesel, client.pesel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, pesel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Client {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    pesel: ").append(toIndentedString(pesel)).append("\n");
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
