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
 * The Mask data type identifies the value to be matched for a sequence of bits at a particular location in a frame. It shall comply with the provisions defined in Table 6.5.3.41-1. 
 */
@ApiModel(description = "The Mask data type identifies the value to be matched for a sequence of bits at a particular location in a frame. It shall comply with the provisions defined in Table 6.5.3.41-1. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class Mask   {
  @JsonProperty("startingPoint")
  private Integer startingPoint = null;

  @JsonProperty("length")
  private Integer length = null;

  @JsonProperty("value")
  private String value = null;

  public Mask startingPoint(Integer startingPoint) {
    this.startingPoint = startingPoint;
    return this;
  }

  /**
   * Indicates the offset between the last bit of the source mac address and the first bit of the sequence of bits to be matched. 
   * @return startingPoint
  **/
  @ApiModelProperty(required = true, value = "Indicates the offset between the last bit of the source mac address and the first bit of the sequence of bits to be matched. ")
  @NotNull


  public Integer getStartingPoint() {
    return startingPoint;
  }

  public void setStartingPoint(Integer startingPoint) {
    this.startingPoint = startingPoint;
  }

  public Mask length(Integer length) {
    this.length = length;
    return this;
  }

  /**
   * Indicates the number of bits to be matched. 
   * @return length
  **/
  @ApiModelProperty(required = true, value = "Indicates the number of bits to be matched. ")
  @NotNull


  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Mask value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Provide the sequence of bit values to be matched. 
   * @return value
  **/
  @ApiModelProperty(required = true, value = "Provide the sequence of bit values to be matched. ")
  @NotNull


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Mask mask = (Mask) o;
    return Objects.equals(this.startingPoint, mask.startingPoint) &&
        Objects.equals(this.length, mask.length) &&
        Objects.equals(this.value, mask.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(startingPoint, length, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Mask {\n");
    
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

