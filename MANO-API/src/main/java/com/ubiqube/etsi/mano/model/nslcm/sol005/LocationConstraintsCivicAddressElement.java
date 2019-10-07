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
 * LocationConstraintsCivicAddressElement
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-10-07T10:02:43.347+02:00")

public class LocationConstraintsCivicAddressElement   {
  @JsonProperty("caType")
  private Integer caType = null;

  @JsonProperty("caValue")
  private String caValue = null;

  public LocationConstraintsCivicAddressElement caType(Integer caType) {
    this.caType = caType;
    return this;
  }

  /**
   * Describe the content type of caValue. The value of caType shall comply with Section 3.4 of IETF RFC 4776 [13]. 
   * @return caType
  **/
  @ApiModelProperty(required = true, value = "Describe the content type of caValue. The value of caType shall comply with Section 3.4 of IETF RFC 4776 [13]. ")
  @NotNull


  public Integer getCaType() {
    return caType;
  }

  public void setCaType(Integer caType) {
    this.caType = caType;
  }

  public LocationConstraintsCivicAddressElement caValue(String caValue) {
    this.caValue = caValue;
    return this;
  }

  /**
   * Content of civic address element corresponding to the caType. The format caValue shall comply with Section 3.4 of IETF RFC 4776 [13]. 
   * @return caValue
  **/
  @ApiModelProperty(required = true, value = "Content of civic address element corresponding to the caType. The format caValue shall comply with Section 3.4 of IETF RFC 4776 [13]. ")
  @NotNull


  public String getCaValue() {
    return caValue;
  }

  public void setCaValue(String caValue) {
    this.caValue = caValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationConstraintsCivicAddressElement locationConstraintsCivicAddressElement = (LocationConstraintsCivicAddressElement) o;
    return Objects.equals(this.caType, locationConstraintsCivicAddressElement.caType) &&
        Objects.equals(this.caValue, locationConstraintsCivicAddressElement.caValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(caType, caValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationConstraintsCivicAddressElement {\n");
    
    sb.append("    caType: ").append(toIndentedString(caType)).append("\n");
    sb.append("    caValue: ").append(toIndentedString(caValue)).append("\n");
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

