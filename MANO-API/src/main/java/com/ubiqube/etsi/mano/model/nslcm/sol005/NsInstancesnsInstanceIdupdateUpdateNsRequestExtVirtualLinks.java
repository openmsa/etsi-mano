package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
  * This type represents an external VL. It shall comply with the provisions defined in Table 6.5.3.26-1. 
 **/
@ApiModel(description="This type represents an external VL. It shall comply with the provisions defined in Table 6.5.3.26-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks  {
  
  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String extVirtualLinkId = null;

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

  @ApiModelProperty(required = true, value = "External CPs of the VNF to be connected to this external VL. ")
  @Valid
 /**
   * External CPs of the VNF to be connected to this external VL. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps> extCps = new ArrayList<NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps>();

  @ApiModelProperty(value = "Externally provided link ports to be used to connect external connection points to this external VL. ")
  @Valid
 /**
   * Externally provided link ports to be used to connect external connection points to this external VL. 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts> extLinkPorts = null;
 /**
   * An identifier with the intention of being globally unique. 
   * @return extVirtualLinkId
  **/
  @JsonProperty("extVirtualLinkId")
  public String getExtVirtualLinkId() {
    return extVirtualLinkId;
  }

  public void setExtVirtualLinkId(String extVirtualLinkId) {
    this.extVirtualLinkId = extVirtualLinkId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks extVirtualLinkId(String extVirtualLinkId) {
    this.extVirtualLinkId = extVirtualLinkId;
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks vimId(String vimId) {
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks resourceProviderId(String resourceProviderId) {
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

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

 /**
   * External CPs of the VNF to be connected to this external VL. 
   * @return extCps
  **/
  @JsonProperty("extCps")
  @NotNull
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps> getExtCps() {
    return extCps;
  }

  public void setExtCps(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps> extCps) {
    this.extCps = extCps;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks extCps(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps> extCps) {
    this.extCps = extCps;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks addExtCpsItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtCps extCpsItem) {
    this.extCps.add(extCpsItem);
    return this;
  }

 /**
   * Externally provided link ports to be used to connect external connection points to this external VL. 
   * @return extLinkPorts
  **/
  @JsonProperty("extLinkPorts")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts> getExtLinkPorts() {
    return extLinkPorts;
  }

  public void setExtLinkPorts(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks extLinkPorts(List<NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks addExtLinkPortsItem(NsInstancesnsInstanceIdupdateUpdateNsRequestExtLinkPorts extLinkPortsItem) {
    this.extLinkPorts.add(extLinkPortsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestExtVirtualLinks {\n");
    
    sb.append("    extVirtualLinkId: ").append(toIndentedString(extVirtualLinkId)).append("\n");
    sb.append("    vimId: ").append(toIndentedString(vimId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    extCps: ").append(toIndentedString(extCps)).append("\n");
    sb.append("    extLinkPorts: ").append(toIndentedString(extLinkPorts)).append("\n");
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

