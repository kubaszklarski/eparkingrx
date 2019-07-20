package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.Rate;

/**
 * information about clients account
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class SetRateResponse   {
  
  private Rate rate = null;

  public SetRateResponse () {

  }

  public SetRateResponse (Rate rate) {
    this.rate = rate;
  }

    
  @JsonProperty("Rate")
  public Rate getRate() {
    return rate;
  }
  public void setRate(Rate rate) {
    this.rate = rate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SetRateResponse setRateResponse = (SetRateResponse) o;
    return Objects.equals(rate, setRateResponse.rate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SetRateResponse {\n");
    
    sb.append("    rate: ").append(toIndentedString(rate)).append("\n");
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
