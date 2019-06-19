package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
  * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. 
 **/
@ApiModel(description="This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. ")
public class NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo  {
  
  @ApiModelProperty(required = true, value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. ")
 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
  **/
  private String virtualStorageDescId = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle storageResource = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String reservationId = null;

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

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Identifier of the VNF Virtual Link Descriptor (VLD) in the VNFD. 
   * @return virtualStorageDescId
  **/
  @JsonProperty("virtualStorageDescId")
  @NotNull
  public String getVirtualStorageDescId() {
    return virtualStorageDescId;
  }

  public void setVirtualStorageDescId(String virtualStorageDescId) {
    this.virtualStorageDescId = virtualStorageDescId;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo virtualStorageDescId(String virtualStorageDescId) {
    this.virtualStorageDescId = virtualStorageDescId;
    return this;
  }

 /**
   * Get storageResource
   * @return storageResource
  **/
  @JsonProperty("storageResource")
  @NotNull
  public NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle getStorageResource() {
    return storageResource;
  }

  public void setStorageResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle storageResource) {
    this.storageResource = storageResource;
  }

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo storageResource(NsInstancesNsInstanceInstantiatedVnfInfoResourceHandle storageResource) {
    this.storageResource = storageResource;
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo reservationId(String reservationId) {
    this.reservationId = reservationId;
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

  public NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo metadata(Object metadata) {
    this.metadata = metadata;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesNsInstanceInstantiatedVnfInfoVirtualStorageResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    virtualStorageDescId: ").append(toIndentedString(virtualStorageDescId)).append("\n");
    sb.append("    storageResource: ").append(toIndentedString(storageResource)).append("\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
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

