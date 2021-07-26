/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.ResourceHandle;
import com.ubiqube.etsi.mano.vnfm.v281.model.vnflcm.VnfcResourceInfoVnfcCpInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. 
 */
@ApiModel(description = "This type represents the information on virtualised compute and storage resources used by a VNFC in a VNF instance. ")
@Validated

public class VnfcResourceInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("computeResource")
  private ResourceHandle computeResource = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

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
   * Identifier of this VnfcResourceInfo instance. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of this VnfcResourceInfo instance. ")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfcResourceInfo vduId(String vduId) {
    this.vduId = vduId;
    return this;
  }

  /**
   * Reference to the applicable VDU in the VNFD. 
   * @return vduId
  **/
  @ApiModelProperty(required = true, value = "Reference to the applicable VDU in the VNFD. ")
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
   * Reference to the VirtualCompute resource. 
   * @return computeResource
  **/
  @ApiModelProperty(required = true, value = "Reference to the VirtualCompute resource. ")
  @NotNull

  @Valid

  public ResourceHandle getComputeResource() {
    return computeResource;
  }

  public void setComputeResource(ResourceHandle computeResource) {
    this.computeResource = computeResource;
  }

  public VnfcResourceInfo zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * The identifier of the resource zone, as managed by the resource management layer (typically, the VIM), where the referenced VirtualCompute resource is placed. Shall be provided if this information is available from the VIM. 
   * @return zoneId
  **/
  @ApiModelProperty(value = "The identifier of the resource zone, as managed by the resource management layer (typically, the VIM), where the referenced VirtualCompute resource is placed. Shall be provided if this information is available from the VIM. ")


  public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
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
  @ApiModelProperty(value = "References to the VirtualStorage resources. The value refers to a VirtualStorageResourceInfo item in the VnfInstance. ")


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
   * The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists. 
   * @return reservationId
  **/
  @ApiModelProperty(value = "The reservation identifier applicable to the resource. It shall be present when an applicable reservation exists. ")


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
   * CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is exposed as  an external CP of the VNF instance or is connected to an external CP of the  VNF instance. See note 2. May be present otherwise. NOTE 2: A VNFC CP is \"connected to\" an external CP if the VNFC CP is connected  to an internal VL that exposes an external CP. A VNFC CP is \"exposed as\" an external  CP if it is connected directly to an external VL. 
   * @return vnfcCpInfo
  **/
  @ApiModelProperty(value = "CPs of the VNFC instance. Shall be present when that particular CP of the VNFC instance is exposed as  an external CP of the VNF instance or is connected to an external CP of the  VNF instance. See note 2. May be present otherwise. NOTE 2: A VNFC CP is \"connected to\" an external CP if the VNFC CP is connected  to an internal VL that exposes an external CP. A VNFC CP is \"exposed as\" an external  CP if it is connected directly to an external VL. ")

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
   * Metadata about this resource. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Metadata about this resource. ")

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
        Objects.equals(this.vduId, vnfcResourceInfo.vduId) &&
        Objects.equals(this.computeResource, vnfcResourceInfo.computeResource) &&
        Objects.equals(this.zoneId, vnfcResourceInfo.zoneId) &&
        Objects.equals(this.storageResourceIds, vnfcResourceInfo.storageResourceIds) &&
        Objects.equals(this.reservationId, vnfcResourceInfo.reservationId) &&
        Objects.equals(this.vnfcCpInfo, vnfcResourceInfo.vnfcCpInfo) &&
        Objects.equals(this.metadata, vnfcResourceInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vduId, computeResource, zoneId, storageResourceIds, reservationId, vnfcCpInfo, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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

