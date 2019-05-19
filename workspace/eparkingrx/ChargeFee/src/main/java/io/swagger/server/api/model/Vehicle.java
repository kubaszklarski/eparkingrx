package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * details of clients vehicle
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Vehicle   {
  
  private String registrationNumber = null;
  private String vinNumber = null;
  private String brand = null;
  private String model = null;
  private LocalDate productionDate = null;

  public Vehicle () {

  }

  public Vehicle (String registrationNumber, String vinNumber, String brand, String model, LocalDate productionDate) {
    this.registrationNumber = registrationNumber;
    this.vinNumber = vinNumber;
    this.brand = brand;
    this.model = model;
    this.productionDate = productionDate;
  }

    
  @JsonProperty("RegistrationNumber")
  public String getRegistrationNumber() {
    return registrationNumber;
  }
  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

    
  @JsonProperty("VinNumber")
  public String getVinNumber() {
    return vinNumber;
  }
  public void setVinNumber(String vinNumber) {
    this.vinNumber = vinNumber;
  }

    
  @JsonProperty("Brand")
  public String getBrand() {
    return brand;
  }
  public void setBrand(String brand) {
    this.brand = brand;
  }

    
  @JsonProperty("Model")
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }

    
  @JsonProperty("ProductionDate")
  public LocalDate getProductionDate() {
    return productionDate;
  }
  public void setProductionDate(LocalDate productionDate) {
    this.productionDate = productionDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vehicle vehicle = (Vehicle) o;
    return Objects.equals(registrationNumber, vehicle.registrationNumber) &&
        Objects.equals(vinNumber, vehicle.vinNumber) &&
        Objects.equals(brand, vehicle.brand) &&
        Objects.equals(model, vehicle.model) &&
        Objects.equals(productionDate, vehicle.productionDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationNumber, vinNumber, brand, model, productionDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Vehicle {\n");
    
    sb.append("    registrationNumber: ").append(toIndentedString(registrationNumber)).append("\n");
    sb.append("    vinNumber: ").append(toIndentedString(vinNumber)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    productionDate: ").append(toIndentedString(productionDate)).append("\n");
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
