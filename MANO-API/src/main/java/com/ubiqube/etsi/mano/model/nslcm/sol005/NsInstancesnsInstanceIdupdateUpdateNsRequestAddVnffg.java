package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type specifies the parameters used for the creation of a new VNFFG instance.  It shall comply with the provisions defined in Table 6.5.3.36-1. 
 **/
@ApiModel(description="This type specifies the parameters used for the creation of a new VNFFG instance.  It shall comply with the provisions defined in Table 6.5.3.36-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg  {
  
  @ApiModelProperty(value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String targetNsInstanceId = null;

  @ApiModelProperty(required = true, value = "Human readable name for the VNFFG. ")
 /**
   * Human readable name for the VNFFG. 
  **/
  private String vnffgName = null;

  @ApiModelProperty(required = true, value = "Human readable description for the VNFFG. ")
 /**
   * Human readable description for the VNFFG. 
  **/
  private String description = null;
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return targetNsInstanceId
  **/
  @JsonProperty("targetNsInstanceId")
  public String getTargetNsInstanceId() {
    return targetNsInstanceId;
  }

  public void setTargetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg targetNsInstanceId(String targetNsInstanceId) {
    this.targetNsInstanceId = targetNsInstanceId;
    return this;
  }

 /**
   * Human readable name for the VNFFG. 
   * @return vnffgName
  **/
  @JsonProperty("vnffgName")
  @NotNull
  public String getVnffgName() {
    return vnffgName;
  }

  public void setVnffgName(String vnffgName) {
    this.vnffgName = vnffgName;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg vnffgName(String vnffgName) {
    this.vnffgName = vnffgName;
    return this;
  }

 /**
   * Human readable description for the VNFFG. 
   * @return description
  **/
  @JsonProperty("description")
  @NotNull
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg description(String description) {
    this.description = description;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg {\n");
    
    sb.append("    targetNsInstanceId: ").append(toIndentedString(targetNsInstanceId)).append("\n");
    sb.append("    vnffgName: ").append(toIndentedString(vnffgName)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

