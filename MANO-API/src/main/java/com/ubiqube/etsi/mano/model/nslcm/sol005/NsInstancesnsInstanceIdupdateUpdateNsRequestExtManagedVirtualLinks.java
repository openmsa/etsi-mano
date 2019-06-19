package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
  * This type represents an externally-managed internal VL.  It shall comply with the provisions defined in Table 6.5.3.27-1.      
 **/
@ApiModel(description="This type represents an externally-managed internal VL.  It shall comply with the provisions defined in Table 6.5.3.27-1.      ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks  {
  
  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String extManagedVirtualLinkId = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String virtualLinkDescId = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String vimId = null;

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
 /**
   * An identifier with the intention of being globally unique. 
   * @return extManagedVirtualLinkId
  **/
  @JsonProperty("extManagedVirtualLinkId")
  public String getExtManagedVirtualLinkId() {
    return extManagedVirtualLinkId;
  }

  public void setExtManagedVirtualLinkId(String extManagedVirtualLinkId) {
    this.extManagedVirtualLinkId = extManagedVirtualLinkId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks extManagedVirtualLinkId(String extManagedVirtualLinkId) {
    this.extManagedVirtualLinkId = extManagedVirtualLinkId;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return virtualLinkDescId
  **/
  @JsonProperty("virtualLinkDescId")
  @NotNull
  public String getVirtualLinkDescId() {
    return virtualLinkDescId;
  }

  public void setVirtualLinkDescId(String virtualLinkDescId) {
    this.virtualLinkDescId = virtualLinkDescId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks virtualLinkDescId(String virtualLinkDescId) {
    this.virtualLinkDescId = virtualLinkDescId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return vimId
  **/
  @JsonProperty("vimId")
  public String getVimId() {
    return vimId;
  }

  public void setVimId(String vimId) {
    this.vimId = vimId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks vimId(String vimId) {
    this.vimId = vimId;
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks resourceProviderId(String resourceProviderId) {
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestExtManagedVirtualLinks {\n");
    
    sb.append("    extManagedVirtualLinkId: ").append(toIndentedString(extManagedVirtualLinkId)).append("\n");
    sb.append("    virtualLinkDescId: ").append(toIndentedString(virtualLinkDescId)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
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

