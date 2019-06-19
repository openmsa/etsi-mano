package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents an externally provided link port to be used to connect an external connection point to an external VL. 
 **/
@ApiModel(description="This type represents an externally provided link port to be used to connect an external connection point to an external VL. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Get resourceHandle
   * @return resourceHandle
  **/
  @JsonProperty("resourceHandle")
  @NotNull
  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts resourceHandle(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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

