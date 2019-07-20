package io.swagger.server.api.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.List;

/**
 * generic validation result
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) 
public class ValidationResult   {
  


  public enum ResultEnum {
    VALID("VALID"),
    INVALID("INVALID");

    private String value;

    ResultEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private ResultEnum result = null;
  private List<String> messages = new ArrayList<>();

  public ValidationResult () {

  }

  public ValidationResult (ResultEnum result, List<String> messages) {
    this.result = result;
    this.messages = messages;
  }

    
  @JsonProperty("Result")
  public ResultEnum getResult() {
    return result;
  }
  public void setResult(ResultEnum result) {
    this.result = result;
  }

    
  @JsonProperty("Messages")
  public List<String> getMessages() {
    return messages;
  }
  public void setMessages(List<String> messages) {
    this.messages = messages;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationResult validationResult = (ValidationResult) o;
    return Objects.equals(result, validationResult.result) &&
        Objects.equals(messages, validationResult.messages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, messages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationResult {\n");
    
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    messages: ").append(toIndentedString(messages)).append("\n");
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
