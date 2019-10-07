package com.ubiqube.etsi.mano.model.nslcm.sol005;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * The PortRange data type provides the lower and upper bounds of a range of Internet ports. It shall comply with the provisions defined in Table 6.5.3.42-1. 
 */
@ApiModel(description = "The PortRange data type provides the lower and upper bounds of a range of Internet ports. It shall comply with the provisions defined in Table 6.5.3.42-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class PortRange   {
  @JsonProperty("lowerPort")
  private Integer lowerPort = null;

  @JsonProperty("upperPort")
  private Integer upperPort = null;

  public PortRange lowerPort(Integer lowerPort) {
    this.lowerPort = lowerPort;
    return this;
  }

  /**
   * Identifies the lower bound of the port range. upperPort Integer 
   * minimum: 0
   * @return lowerPort
  **/
  @ApiModelProperty(required = true, value = "Identifies the lower bound of the port range. upperPort Integer ")
  @NotNull

@Min(0)
  public Integer getLowerPort() {
    return lowerPort;
  }

  public void setLowerPort(Integer lowerPort) {
    this.lowerPort = lowerPort;
  }

  public PortRange upperPort(Integer upperPort) {
    this.upperPort = upperPort;
    return this;
  }

  /**
   * Identifies the upper bound of the port range. 
   * minimum: 0
   * @return upperPort
  **/
  @ApiModelProperty(required = true, value = "Identifies the upper bound of the port range. ")
  @NotNull

@Min(0)
  public Integer getUpperPort() {
    return upperPort;
  }

  public void setUpperPort(Integer upperPort) {
    this.upperPort = upperPort;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PortRange portRange = (PortRange) o;
    return Objects.equals(this.lowerPort, portRange.lowerPort) &&
        Objects.equals(this.upperPort, portRange.upperPort);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lowerPort, upperPort);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PortRange {\n");
    
    sb.append("    lowerPort: ").append(toIndentedString(lowerPort)).append("\n");
    sb.append("    upperPort: ").append(toIndentedString(upperPort)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

