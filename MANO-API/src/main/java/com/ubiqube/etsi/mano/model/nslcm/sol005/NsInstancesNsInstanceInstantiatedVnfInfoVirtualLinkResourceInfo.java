package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. 
 **/
@ApiModel(description="This type represents the information that allows addressing a virtualised resource that is used by an internal VL instance in a VNF instance. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String vnfVirtualLinkDescId = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle networkResource = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String reservationId = null;

  @ApiModelProperty(value = "Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise. ")
  @Valid
 /**
   * Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object metadata = null;
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo id(String id) {
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
    return this;
  }

 /**
   * Get networkResource
   * @return networkResource
  **/
  @JsonProperty("networkResource")
  @NotNull
  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle getNetworkResource() {
    return networkResource;
  }

  public void setNetworkResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle networkResource) {
    this.networkResource = networkResource;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo networkResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle networkResource) {
    this.networkResource = networkResource;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return reservationId
  **/
  @JsonProperty("reservationId")
  public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo reservationId(String reservationId) {
    this.reservationId = reservationId;
    return this;
  }

 /**
   * Links ports of this VL. Shall be present when the linkPort is used for external connectivity by the VNF (refer to VnfLinkPortInfo). May be present otherwise. 
   * @return vnfLinkPorts
  **/
  @JsonProperty("vnfLinkPorts")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> getVnfLinkPorts() {
    return vnfLinkPorts;
  }

  public void setVnfLinkPorts(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo vnfLinkPorts(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts> vnfLinkPorts) {
    this.vnfLinkPorts = vnfLinkPorts;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo addVnfLinkPortsItem(NsInstancesNsInstanceInstantiatedVnfInfoVnfLinkPorts vnfLinkPortsItem) {
    this.vnfLinkPorts.add(vnfLinkPortsItem);
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return metadata
  **/
  @JsonProperty("metadata")
  public Object getMetadata() {
    return metadata;
  }

  public void setMetadata(Object metadata) {
    this.metadata = metadata;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoVirtualLinkResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    vnfLinkPorts: ").append(toIndentedString(vnfLinkPorts)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

