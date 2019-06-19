package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class NsInstancesCreateNsRequest  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String nsdId = null;

  @ApiModelProperty(required = true, value = "Human-readable name of the NS instance to be created. ")
 /**
   * Human-readable name of the NS instance to be created. 
  **/
  private String nsName = null;

  @ApiModelProperty(required = true, value = "Human-readable description of the NS instance to be created. ")
 /**
   * Human-readable description of the NS instance to be created. 
  **/
  private String nsDescription = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return nsdId
  **/
  @JsonProperty("nsdId")
  @NotNull
  public String getNsdId() {
    return nsdId;
  }

  public void setNsdId(String nsdId) {
    this.nsdId = nsdId;
  }

  public NsInstancesCreateNsRequest nsdId(String nsdId) {
    this.nsdId = nsdId;
    return this;
  }

 /**
   * Human-readable name of the NS instance to be created. 
   * @return nsName
  **/
  @JsonProperty("nsName")
  @NotNull
  public String getNsName() {
    return nsName;
  }

  public void setNsName(String nsName) {
    this.nsName = nsName;
  }

  public NsInstancesCreateNsRequest nsName(String nsName) {
    this.nsName = nsName;
    return this;
  }

 /**
   * Human-readable description of the NS instance to be created. 
   * @return nsDescription
  **/
  @JsonProperty("nsDescription")
  @NotNull
  public String getNsDescription() {
    return nsDescription;
  }

  public void setNsDescription(String nsDescription) {
    this.nsDescription = nsDescription;
  }

  public NsInstancesCreateNsRequest nsDescription(String nsDescription) {
    this.nsDescription = nsDescription;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesCreateNsRequest {\n");
    
    sb.append("    nsdId: ").append(toIndentedString(nsdId)).append("\n");
    sb.append("    nsName: ").append(toIndentedString(nsName)).append("\n");
    sb.append("    nsDescription: ").append(toIndentedString(nsDescription)).append("\n");
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

