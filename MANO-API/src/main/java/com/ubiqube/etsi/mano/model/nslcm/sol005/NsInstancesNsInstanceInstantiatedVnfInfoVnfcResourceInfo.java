package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
  * This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. 
 **/
@ApiModel(description="This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String vduId = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle computeResource = null;

  @ApiModelProperty(value = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. ")
 /**
   * References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. 
  **/
  private List<String> storageResourceIds = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String reservationId = null;

  @ApiModelProperty(value = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise. ")
  @Valid
 /**
   * CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise. 
  **/
  private List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo> vnfcCpInfo = null;

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

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return vduId
  **/
  @JsonProperty("vduId")
  @NotNull
  public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

 /**
   * Get computeResource
   * @return computeResource
  **/
  @JsonProperty("computeResource")
  @NotNull
  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle getComputeResource() {
    return computeResource;
  }

  public void setComputeResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle computeResource) {
    this.computeResource = computeResource;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo computeResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle computeResource) {
    this.computeResource = computeResource;
    return this;
  }

 /**
   * References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. 
   * @return storageResourceIds
  **/
  @JsonProperty("storageResourceIds")
  public List<String> getStorageResourceIds() {
    return storageResourceIds;
  }

  public void setStorageResourceIds(List<String> storageResourceIds) {
    this.storageResourceIds = storageResourceIds;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo storageResourceIds(List<String> storageResourceIds) {
    this.storageResourceIds = storageResourceIds;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo addStorageResourceIdsItem(String storageResourceIdsItem) {
    this.storageResourceIds.add(storageResourceIdsItem);
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo reservationId(String reservationId) {
    this.reservationId = reservationId;
    return this;
  }

 /**
   * CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is associated to an external CP of the VNF instance. May be present otherwise. 
   * @return vnfcCpInfo
  **/
  @JsonProperty("vnfcCpInfo")
  public List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo> getVnfcCpInfo() {
    return vnfcCpInfo;
  }

  public void setVnfcCpInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo> vnfcCpInfo) {
    this.vnfcCpInfo = vnfcCpInfo;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo vnfcCpInfo(List<NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo> vnfcCpInfo) {
    this.vnfcCpInfo = vnfcCpInfo;
    return this;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo addVnfcCpInfoItem(NsInstancesNsInstanceInstantiatedVnfInfoVnfcCpInfo vnfcCpInfoItem) {
    this.vnfcCpInfo.add(vnfcCpInfoItem);
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoVnfcResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
    sb.append("    storageResourceIds: ").append(toIndentedString(storageResourceIds)).append("\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
    sb.append("    vnfcCpInfo: ").append(toIndentedString(vnfcCpInfo)).append("\n");
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

