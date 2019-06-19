package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type specifies the information about an NS VL instance.  It shall comply with the provisions defined in Table 6.5.3.53-1 
 **/
@ApiModel(description="This type specifies the information about an NS VL instance.  It shall comply with the provisions defined in Table 6.5.3.53-1 ")
public class NsInstancesNsInstanceVirtualLinkInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique with respect to a NS.  Representation: string of variable length. ")
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "An identifier that is unique within a NS descriptor. Representation: string of variable length. ")
 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
  **/
  private String nsVirtualLinkDescId = null;

  @ApiModelProperty(value = "Identifier(s) of the virtualised network resource(s) realizing the VL instance. See note. ")
  @Valid
 /**
   * Identifier(s) of the virtualised network resource(s) realizing the VL instance. See note. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle> resourceHandle = null;

  @ApiModelProperty(value = "Link ports of the VL instance. Cardinality of zero indicates that no port has yet been created for the VL instance. ")
  @Valid
 /**
   * Link ports of the VL instance. Cardinality of zero indicates that no port has yet been created for the VL instance. 
  **/
  private List<NsInstancesNsInstanceLinkPort> linkPort = null;
 /**
   * An identifier that is unique with respect to a NS.  Representation: string of variable length. 
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

  public NsInstancesNsInstanceVirtualLinkInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * An identifier that is unique within a NS descriptor. Representation: string of variable length. 
   * @return nsVirtualLinkDescId
  **/
  @JsonProperty("nsVirtualLinkDescId")
  @NotNull
  public String getNsVirtualLinkDescId() {
    return nsVirtualLinkDescId;
  }

  public void setNsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
  }

  public NsInstancesNsInstanceVirtualLinkInfo nsVirtualLinkDescId(String nsVirtualLinkDescId) {
    this.nsVirtualLinkDescId = nsVirtualLinkDescId;
    return this;
  }

 /**
   * Identifier(s) of the virtualised network resource(s) realizing the VL instance. See note. 
   * @return resourceHandle
  **/
  @JsonProperty("resourceHandle")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle> getResourceHandle() {
    return resourceHandle;
  }

  public void setResourceHandle(List<NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle> resourceHandle) {
    this.resourceHandle = resourceHandle;
  }

  public NsInstancesNsInstanceVirtualLinkInfo resourceHandle(List<NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle> resourceHandle) {
    this.resourceHandle = resourceHandle;
    return this;
  }

  public NsInstancesNsInstanceVirtualLinkInfo addResourceHandleItem(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle resourceHandleItem) {
    this.resourceHandle.add(resourceHandleItem);
    return this;
  }

 /**
   * Link ports of the VL instance. Cardinality of zero indicates that no port has yet been created for the VL instance. 
   * @return linkPort
  **/
  @JsonProperty("linkPort")
  public List<NsInstancesNsInstanceLinkPort> getLinkPort() {
    return linkPort;
  }

  public void setLinkPort(List<NsInstancesNsInstanceLinkPort> linkPort) {
    this.linkPort = linkPort;
  }

  public NsInstancesNsInstanceVirtualLinkInfo linkPort(List<NsInstancesNsInstanceLinkPort> linkPort) {
    this.linkPort = linkPort;
    return this;
  }

  public NsInstancesNsInstanceVirtualLinkInfo addLinkPortItem(NsInstancesNsInstanceLinkPort linkPortItem) {
    this.linkPort.add(linkPortItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceVirtualLinkInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nsVirtualLinkDescId: ").append(toIndentedString(nsVirtualLinkDescId)).append("\n");
    sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
    sb.append("    linkPort: ").append(toIndentedString(linkPort)).append("\n");
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

