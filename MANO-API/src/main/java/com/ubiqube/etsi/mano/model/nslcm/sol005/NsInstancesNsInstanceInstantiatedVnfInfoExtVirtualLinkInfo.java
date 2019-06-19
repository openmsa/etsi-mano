package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle = null;

  @ApiModelProperty(value = "Link ports of this VL. ")
  @Valid
 /**
   * Link ports of this VL. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoExtLinkPorts> extLinkPorts = null;
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

  public NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo id(String id) {
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

  public NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo resourceHandle(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

 /**
   * Link ports of this VL. 
   * @return extLinkPorts
  **/
  @JsonProperty("extLinkPorts")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoExtLinkPorts> getExtLinkPorts() {
    return extLinkPorts;
  }

  public void setExtLinkPorts(List<NsInstancesNsInstanceInstantiatedVnfInfoExtLinkPorts> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo extLinkPorts(List<NsInstancesNsInstanceInstantiatedVnfInfoExtLinkPorts> extLinkPorts) {
    this.extLinkPorts = extLinkPorts;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo addExtLinkPortsItem(NsInstancesNsInstanceInstantiatedVnfInfoExtLinkPorts extLinkPortsItem) {
    this.extLinkPorts.add(extLinkPortsItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoExtVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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

