package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String vnfVirtualLinkDescId = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle networkResource = null;

  @ApiModelProperty(value = "Link ports of this VL. ")
  @Valid
 /**
   * Link ports of this VL. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts = null;
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

  public NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return vnfVirtualLinkDescId
  **/
  @JsonProperty("vnfVirtualLinkDescId")
  @NotNull
  public String getVnfVirtualLinkDescId() {
    return vnfVirtualLinkDescId;
  }

  public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
    return this;
  }

 /**
   * Get networkResource
   * @return networkResource
  **/
  @JsonProperty("networkResource")
  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle getNetworkResource() {
    return networkResource;
  }

  public void setNetworkResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle networkResource) {
    this.networkResource = networkResource;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo networkResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle networkResource) {
    this.networkResource = networkResource;
    return this;
  }

 /**
   * Link ports of this VL. 
   * @return vnfLinkPorts
  **/
  @JsonProperty("vnfLinkPorts")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> getVnfLinkPorts() {
    return vnfLinkPorts;
  }

  public void setVnfLinkPorts(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo vnfLinkPorts(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo addVnfLinkPortsItem(NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts vnfLinkPortsItem) {
    this.vnfLinkPorts.add(vnfLinkPortsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoExtManagedVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
    sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
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

