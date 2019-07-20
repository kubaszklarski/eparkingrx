package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * information about parking rate per minute
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Rate   {
  
  private BigDecimal rateAmount = null;

  public Rate () {

  }

  public Rate (BigDecimal rateAmount) {
    this.rateAmount = rateAmount;
  }

    
  @JsonProperty("RateAmount")
  public BigDecimal getRateAmount() {
    return rateAmount;
  }
  public void setRateAmount(BigDecimal rateAmount) {
    this.rateAmount = rateAmount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rate rate = (Rate) o;
    return Objects.equals(rateAmount, rate.rateAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rateAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Rate {\n");
    
    sb.append("    rateAmount: ").append(toIndentedString(rateAmount)).append("\n");
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
