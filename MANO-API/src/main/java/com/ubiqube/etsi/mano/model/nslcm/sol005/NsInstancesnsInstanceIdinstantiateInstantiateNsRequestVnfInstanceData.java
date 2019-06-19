package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type specifies an existing VNF instance to be used in the NS instance and  if needed, the VNF Profile to use for this VNF instance.  It shall comply with the provisions defined in Table 6.5.3.19-1. 
 **/
@ApiModel(description="This type specifies an existing VNF instance to be used in the NS instance and  if needed, the VNF Profile to use for this VNF instance.  It shall comply with the provisions defined in Table 6.5.3.19-1. ")
public class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vnfInstanceId = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String vnfProfileId = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vnfInstanceId
  **/
  @JsonProperty("vnfInstanceId")
  @NotNull
  public String getVnfInstanceId() {
    return vnfInstanceId;
  }

  public void setVnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
  }

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData vnfInstanceId(String vnfInstanceId) {
    this.vnfInstanceId = vnfInstanceId;
    return this;
  }

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

  public NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData vnfProfileId(String vnfProfileId) {
    this.vnfProfileId = vnfProfileId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData {\n");
    
    sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
    sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
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

