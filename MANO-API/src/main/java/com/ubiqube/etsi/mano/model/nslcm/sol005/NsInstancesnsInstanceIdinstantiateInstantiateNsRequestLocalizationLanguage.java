package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents the association of location constraints to a VNF instance  to be created according to a specific VNF profile.  It shall comply with the provisions defined in Table 6.5.3.20-1. 
 **/
@ApiModel(description="This type represents the association of location constraints to a VNF instance  to be created according to a specific VNF profile.  It shall comply with the provisions defined in Table 6.5.3.20-1. ")
public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String vnfProfileId = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints locationConstraints = null;
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return vnfProfileId
  **/
  @JsonProperty("vnfProfileId")
  @NotNull
  public String getVnfProfileId() {
    return vnfProfileId;
  }

  public void setVnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage vnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }

 /**
   * Get locationConstraints
   * @return locationConstraints
  **/
  @JsonProperty("locationConstraints")
  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage locationConstraints(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocationConstraints locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestLocalizationLanguage {\n");
    
    sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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

