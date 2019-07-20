package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.server.api.model.ValidationResult;

/**
 * account validation result
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ValidateAccountResponse   {
  
  private ValidationResult validationResult = null;

  public ValidateAccountResponse () {

  }

  public ValidateAccountResponse (ValidationResult validationResult) {
    this.validationResult = validationResult;
  }

    
  @JsonProperty("ValidationResult")
  public ValidationResult getValidationResult() {
    return validationResult;
  }
  public void setValidationResult(ValidationResult validationResult) {
    this.validationResult = validationResult;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidateAccountResponse validateAccountResponse = (ValidateAccountResponse) o;
    return Objects.equals(validationResult, validateAccountResponse.validationResult);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationResult);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidateAccountResponse {\n");
    
    sb.append("    validationResult: ").append(toIndentedString(validationResult)).append("\n");
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
