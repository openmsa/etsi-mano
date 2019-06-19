package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement  {
  
  @ApiModelProperty(required = true, value = "Describe the content type of caValue. The value of caType shall comply with Section 3.4 of IETF RFC 4776 [13]. ")
 /**
   * Describe the content type of caValue. The value of caType shall comply with Section 3.4 of IETF RFC 4776 [13]. 
  **/
  private Integer caType = null;

  @ApiModelProperty(required = true, value = "Content of civic address element corresponding to the caType. The format caValue shall comply with Section 3.4 of IETF RFC 4776 [13]. ")
 /**
   * Content of civic address element corresponding to the caType. The format caValue shall comply with Section 3.4 of IETF RFC 4776 [13]. 
  **/
  private String caValue = null;
 /**
   * Describe the content type of caValue. The value of caType shall comply with Section 3.4 of IETF RFC 4776 [13]. 
   * @return caType
  **/
  @JsonProperty("caType")
  @NotNull
  public Integer getCaType() {
    return caType;
  }

  public void setCaType(Integer caType) {
    this.caType = caType;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement caType(Integer caType) {
    this.caType = caType;
    return this;
  }

 /**
   * Content of civic address element corresponding to the caType. The format caValue shall comply with Section 3.4 of IETF RFC 4776 [13]. 
   * @return caValue
  **/
  @JsonProperty("caValue")
  @NotNull
  public String getCaValue() {
    return caValue;
  }

  public void setCaValue(String caValue) {
    this.caValue = caValue;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement caValue(String caValue) {
    this.caValue = caValue;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraintsCivicAddressElement {\n");
    
    sb.append("    caType: ").append(toIndentedString(caType)).append("\n");
    sb.append("    caValue: ").append(toIndentedString(caValue)).append("\n");
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

