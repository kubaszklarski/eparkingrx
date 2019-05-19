package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.Payment;

/**
 * information about clients account
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class RegisterPaymentResponse   {
  
  private Payment payment = null;

  public RegisterPaymentResponse () {

  }

  public RegisterPaymentResponse (Payment payment) {
    this.payment = payment;
  }

    
  @JsonProperty("Payment")
  public Payment getPayment() {
    return payment;
  }
  public void setPayment(Payment payment) {
    this.payment = payment;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterPaymentResponse registerPaymentResponse = (RegisterPaymentResponse) o;
    return Objects.equals(payment, registerPaymentResponse.payment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payment);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterPaymentResponse {\n");
    
    sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
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
