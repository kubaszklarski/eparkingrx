package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * information about topping up the account
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class RegisterPayment   {
  
  private String accountId = null;
  private BigDecimal amount = null;

  public RegisterPayment () {

  }

  public RegisterPayment (String accountId, BigDecimal amount) {
    this.accountId = accountId;
    this.amount = amount;
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
    RegisterPayment registerPayment = (RegisterPayment) o;
    return Objects.equals(accountId, registerPayment.accountId) &&
        Objects.equals(amount, registerPayment.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterPayment {\n");
    
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
