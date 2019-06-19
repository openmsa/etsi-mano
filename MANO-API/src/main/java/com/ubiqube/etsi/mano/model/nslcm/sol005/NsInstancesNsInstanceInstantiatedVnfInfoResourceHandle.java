package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. Information about the resource is available from the VIM. 
 **/
@ApiModel(description="This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. Information about the resource is available from the VIM. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vimConnectionId = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String resourceProviderId = null;

  @ApiModelProperty(required = true, value = "An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. ")
 /**
   * An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. 
  **/
  private String resourceId = null;

  @ApiModelProperty(value = "Type of the resource in the scope of the VIM or the resource provider. ")
 /**
   * Type of the resource in the scope of the VIM or the resource provider. 
  **/
  private String vimLevelResourceType = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return vimConnectionId
  **/
  @JsonProperty("vimConnectionId")
  @NotNull
  public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return resourceProviderId
  **/
  @JsonProperty("resourceProviderId")
  public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

 /**
   * An identifier maintained by the VIM or other resource provider. It is expected to be unique within the VIM instance. 
   * @return resourceId
  **/
  @JsonProperty("resourceId")
  @NotNull
  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

 /**
   * Type of the resource in the scope of the VIM or the resource provider. 
   * @return vimLevelResourceType
  **/
  @JsonProperty("vimLevelResourceType")
  public String getVimLevelResourceType() {
    return vimLevelResourceType;
  }

  public void setVimLevelResourceType(String vimLevelResourceType) {
    this.vimLevelResourceType = vimLevelResourceType;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle vimLevelResourceType(String vimLevelResourceType) {
    this.vimLevelResourceType = vimLevelResourceType;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle {\n");
    
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    vimLevelResourceType: ").append(toIndentedString(vimLevelResourceType)).append("\n");
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

