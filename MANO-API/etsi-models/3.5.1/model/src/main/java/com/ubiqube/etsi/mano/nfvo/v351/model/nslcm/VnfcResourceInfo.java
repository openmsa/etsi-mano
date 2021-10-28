package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ResourceHandle;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfcResourceInfoVnfcCpInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. NOTE 1: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. NOTE 2: A VNFC CP is \&quot;connected to\&quot; an external CP if the VNFC CP is connected to an internal VL that exposes an external CP. A VNFC CP is \&quot;exposed as\&quot; an external CP if it is connected directly to an external VL. NOTE 3: The information can be omitted because it is already available as part of the external CP information.  
 */
@Schema(description = "This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. NOTE 1: ETSI GS NFV-SOL 001 specifies the structure and format of the VNFD based on TOSCA specifications. NOTE 2: A VNFC CP is \"connected to\" an external CP if the VNFC CP is connected to an internal VL that exposes an external CP. A VNFC CP is \"exposed as\" an external CP if it is connected directly to an external VL. NOTE 3: The information can be omitted because it is already available as part of the external CP information.  ")
@Validated


public class VnfcResourceInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("computeResource")
  private ResourceHandle computeResource = null;

  @JsonProperty("storageResourceIds")
  @Valid
  private List<String> storageResourceIds = null;

  @JsonProperty("reservationId")
  private String reservationId = null;

  @JsonProperty("vnfcCpInfo")
  @Valid
  private List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public VnfcResourceInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfcResourceInfo vnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
    return this;
  }

  /**
   * Get vnfdId
   * @return vnfdId
   **/
  @Schema(description = "")
  
    public String getVnfdId() {
    return vnfdId;
  }

  public void setVnfdId(String vnfdId) {
    this.vnfdId = vnfdId;
  }

  public VnfcResourceInfo vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Get vduId
   * @return vduId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVduId() {
    return vduId;
  }

  public void setVduId(String vduId) {
    this.vduId = vduId;
  }

  public VnfcResourceInfo computeResource(ResourceHandle computeResource) {
    this.computeResource = computeResource;
    return this;
  }

  /**
   * Get computeResource
   * @return computeResource
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getComputeResource() {
    return computeResource;
  }

  public void setComputeResource(ResourceHandle computeResource) {
    this.computeResource = computeResource;
  }

  public VnfcResourceInfo storageResourceIds(List<String> storageResourceIds) {
    this.storageResourceIds = storageResourceIds;
    return this;
  }

  public VnfcResourceInfo addStorageResourceIdsItem(String storageResourceIdsItem) {
    if (this.storageResourceIds == null) {
      this.storageResourceIds = new ArrayList<>();
    }
    this.storageResourceIds.add(storageResourceIdsItem);
    return this;
  }

  /**
   * References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. 
   * @return storageResourceIds
   **/
  @Schema(description = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. ")
  
    public List<String> getStorageResourceIds() {
    return storageResourceIds;
  }

  public void setStorageResourceIds(List<String> storageResourceIds) {
    this.storageResourceIds = storageResourceIds;
  }

  public VnfcResourceInfo reservationId(String reservationId) {
    this.reservationId = reservationId;
    return this;
  }

  /**
   * Get reservationId
   * @return reservationId
   **/
  @Schema(description = "")
  
    public String getReservationId() {
    return reservationId;
  }

  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }

  public VnfcResourceInfo vnfcCpInfo(List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo) {
    this.vnfcCpInfo = vnfcCpInfo;
    return this;
  }

  public VnfcResourceInfo addVnfcCpInfoItem(VnfcResourceInfoVnfcCpInfo vnfcCpInfoItem) {
    if (this.vnfcCpInfo == null) {
      this.vnfcCpInfo = new ArrayList<>();
    }
    this.vnfcCpInfo.add(vnfcCpInfoItem);
    return this;
  }

  /**
   * CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is exposed as an external CP of the VNF instance or is connected to an external CP of the VNF instance.See note 2. May be present otherwise. 
   * @return vnfcCpInfo
   **/
  @Schema(description = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is exposed as an external CP of the VNF instance or is connected to an external CP of the VNF instance.See note 2. May be present otherwise. ")
      @Valid
    public List<VnfcResourceInfoVnfcCpInfo> getVnfcCpInfo() {
    return vnfcCpInfo;
  }

  public void setVnfcCpInfo(List<VnfcResourceInfoVnfcCpInfo> vnfcCpInfo) {
    this.vnfcCpInfo = vnfcCpInfo;
  }

  public VnfcResourceInfo metadata(KeyValuePairs metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getMetadata() {
    return metadata;
  }

  public void setMetadata(KeyValuePairs metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfcResourceInfo vnfcResourceInfo = (VnfcResourceInfo) o;
    return Objects.equals(this.id, vnfcResourceInfo.id) &&
        Objects.equals(this.vnfdId, vnfcResourceInfo.vnfdId) &&
        Objects.equals(this.vduId, vnfcResourceInfo.vduId) &&
        Objects.equals(this.computeResource, vnfcResourceInfo.computeResource) &&
        Objects.equals(this.storageResourceIds, vnfcResourceInfo.storageResourceIds) &&
        Objects.equals(this.reservationId, vnfcResourceInfo.reservationId) &&
        Objects.equals(this.vnfcCpInfo, vnfcResourceInfo.vnfcCpInfo) &&
        Objects.equals(this.metadata, vnfcResourceInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfdId, vduId, computeResource, storageResourceIds, reservationId, vnfcCpInfo, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
