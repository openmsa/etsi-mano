package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
  * The PortRange data type provides the lower and upper bounds of a range of Internet ports.  It shall comply with the provisions defined in Table 6.5.3.42-1. 
 **/
@ApiModel(description="The PortRange data type provides the lower and upper bounds of a range of Internet ports.  It shall comply with the provisions defined in Table 6.5.3.42-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange  {
  
  @ApiModelProperty(required = true, value = "Identifies the lower bound of the port range. upperPort Integer ")
 /**
   * Identifies the lower bound of the port range. upperPort Integer 
  **/
  private Integer lowerPort = null;

  @ApiModelProperty(required = true, value = "Identifies the upper bound of the port range. ")
 /**
   * Identifies the upper bound of the port range. 
  **/
  private Integer upperPort = null;
 /**
   * Identifies the lower bound of the port range. upperPort Integer 
   * minimum: 0
   * @return lowerPort
  **/
  @JsonProperty("lowerPort")
  @NotNull
 @Min(0)  public Integer getLowerPort() {
    return lowerPort;
  }

  public void setLowerPort(Integer lowerPort) {
    this.lowerPort = lowerPort;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange lowerPort(Integer lowerPort) {
    this.lowerPort = lowerPort;
    return this;
  }

 /**
   * Identifies the upper bound of the port range. 
   * minimum: 0
   * @return upperPort
  **/
  @JsonProperty("upperPort")
  @NotNull
 @Min(0)  public Integer getUpperPort() {
    return upperPort;
  }

  public void setUpperPort(Integer upperPort) {
    this.upperPort = upperPort;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange upperPort(Integer upperPort) {
    this.upperPort = upperPort;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleSourcePortRange {\n");
    
    sb.append("    lowerPort: ").append(toIndentedString(lowerPort)).append("\n");
    sb.append("    upperPort: ").append(toIndentedString(upperPort)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

