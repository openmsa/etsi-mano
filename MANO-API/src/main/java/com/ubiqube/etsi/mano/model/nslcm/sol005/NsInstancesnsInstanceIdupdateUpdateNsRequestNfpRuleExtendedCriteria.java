package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * The Mask data type identifies the value to be matched for a sequence of  bits at a particular location in a frame. It shall comply with the provisions defined in Table 6.5.3.41-1. 
 **/
@ApiModel(description="The Mask data type identifies the value to be matched for a sequence of  bits at a particular location in a frame. It shall comply with the provisions defined in Table 6.5.3.41-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria  {
  
  @ApiModelProperty(required = true, value = "Indicates the offset between the last bit of the source mac address and the first bit of the sequence of bits to be matched. ")
 /**
   * Indicates the offset between the last bit of the source mac address and the first bit of the sequence of bits to be matched. 
  **/
  private Integer startingPoint = null;

  @ApiModelProperty(required = true, value = "Indicates the number of bits to be matched. ")
 /**
   * Indicates the number of bits to be matched. 
  **/
  private Integer length = null;

  @ApiModelProperty(required = true, value = "Provide the sequence of bit values to be matched.  ")
 /**
   * Provide the sequence of bit values to be matched.  
  **/
  private String value = null;
 /**
   * Indicates the offset between the last bit of the source mac address and the first bit of the sequence of bits to be matched. 
   * @return startingPoint
  **/
  @JsonProperty("startingPoint")
  @NotNull
  public Integer getStartingPoint() {
    return startingPoint;
  }

  public void setStartingPoint(Integer startingPoint) {
    this.startingPoint = startingPoint;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria startingPoint(Integer startingPoint) {
    this.startingPoint = startingPoint;
    return this;
  }

 /**
   * Indicates the number of bits to be matched. 
   * @return length
  **/
  @JsonProperty("length")
  @NotNull
  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria length(Integer length) {
    this.length = length;
    return this;
  }

 /**
   * Provide the sequence of bit values to be matched.  
   * @return value
  **/
  @JsonProperty("value")
  @NotNull
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria value(String value) {
    this.value = value;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestNfpRuleExtendedCriteria {\n");
    
    sb.append("    startingPoint: ").append(toIndentedString(startingPoint)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

