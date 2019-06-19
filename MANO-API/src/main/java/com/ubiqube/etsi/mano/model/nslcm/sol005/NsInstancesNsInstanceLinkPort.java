package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents information about a link port of a VL instance.  It shall comply with the provisions defined in Table 6.5.3.55-1. 
 **/
@ApiModel(description="This type represents information about a link port of a VL instance.  It shall comply with the provisions defined in Table 6.5.3.55-1. ")
public class NsInstancesNsInstanceLinkPort  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle = null;

  @ApiModelProperty(value = "Identifier of the CP/SAP instance to be connected to this link port. The value refers to a vnfExtCpInfo item in the VnfInstance, or a pnfExtCpInfo item in the PnfInfo, or a sapInfo item in the NS instance. There shall be at most one link port associated with any connection point instance. ")
  @Valid
 /**
   * Identifier of the CP/SAP instance to be connected to this link port. The value refers to a vnfExtCpInfo item in the VnfInstance, or a pnfExtCpInfo item in the PnfInfo, or a sapInfo item in the NS instance. There shall be at most one link port associated with any connection point instance. 
  **/
  private List<NsInstancesNsInstanceNsCpHandle> nsCpHandle = null;
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

  public NsInstancesNsInstanceLinkPort id(String id) {
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

  public NsInstancesNsInstanceLinkPort resourceHandle(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

 /**
   * Identifier of the CP/SAP instance to be connected to this link port. The value refers to a vnfExtCpInfo item in the VnfInstance, or a pnfExtCpInfo item in the PnfInfo, or a sapInfo item in the NS instance. There shall be at most one link port associated with any connection point instance. 
   * @return nsCpHandle
  **/
  @JsonProperty("nsCpHandle")
  public List<NsInstancesNsInstanceNsCpHandle> getNsCpHandle() {
    return nsCpHandle;
  }

  public void setNsCpHandle(List<NsInstancesNsInstanceNsCpHandle> nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
  }

  public NsInstancesNsInstanceLinkPort nsCpHandle(List<NsInstancesNsInstanceNsCpHandle> nsCpHandle) {
    this.nsCpHandle = nsCpHandle;
    return this;
  }

  public NsInstancesNsInstanceLinkPort addNsCpHandleItem(NsInstancesNsInstanceNsCpHandle nsCpHandleItem) {
    this.nsCpHandle.add(nsCpHandleItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceLinkPort {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    nsCpHandle: ").append(toIndentedString(nsCpHandle)).append("\n");
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

