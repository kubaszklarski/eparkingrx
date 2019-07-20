package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * information about topping up the account
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class Payment   {
  
  private String paymentId = null;
  private String accountId = null;
  private BigDecimal amount = null;

  public Payment () {

  }

  public Payment (String paymentId, String accountId, BigDecimal amount) {
    this.paymentId = paymentId;
    this.accountId = accountId;
    this.amount = amount;
  }

    
  @JsonProperty("PaymentId")
  public String getPaymentId() {
    return paymentId;
  }
  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

    
  @JsonProperty("AccountId")
  public String getAccountId() {
    return accountId;
  }
  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

    
  @JsonProperty("Amount")
  public BigDecimal getAmount() {
    return amount;
  }
  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Payment payment = (Payment) o;
    return Objects.equals(paymentId, payment.paymentId) &&
        Objects.equals(accountId, payment.accountId) &&
        Objects.equals(amount, payment.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(paymentId, accountId, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Payment {\n");
    
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
