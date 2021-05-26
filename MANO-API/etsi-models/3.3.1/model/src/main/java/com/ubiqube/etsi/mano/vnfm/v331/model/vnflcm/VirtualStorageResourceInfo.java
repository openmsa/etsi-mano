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
package com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. 
 */
@Schema(description = "This type represents the information that allows addressing a virtualised resource that is used by a VNF instance. ")
@Validated


public class VirtualStorageResourceInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("virtualStorageDescId")
  private String virtualStorageDescId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  @JsonProperty("storageResource")
  private ResourceHandle storageResource = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  @JsonProperty("reservationId")
  private String reservationId = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public VirtualStorageResourceInfo id(String id) {
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

  public VirtualStorageResourceInfo virtualStorageDescId(String virtualStorageDescId) {
    this.virtualStorageDescId = virtualStorageDescId;
    return this;
  }

  /**
   * Get virtualStorageDescId
   * @return virtualStorageDescId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVirtualStorageDescId() {
    return virtualStorageDescId;
  }

  public void setVirtualStorageDescId(String virtualStorageDescId) {
    this.virtualStorageDescId = virtualStorageDescId;
  }

  public VirtualStorageResourceInfo vnfdId(String vnfdId) {
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

  public VirtualStorageResourceInfo storageResource(ResourceHandle storageResource) {
    this.storageResource = storageResource;
    return this;
  }

  /**
   * Get storageResource
   * @return storageResource
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getStorageResource() {
    return storageResource;
  }

  public void setStorageResource(ResourceHandle storageResource) {
    this.storageResource = storageResource;
  }

  public VirtualStorageResourceInfo zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * Get zoneId
   * @return zoneId
   **/
  @Schema(description = "")
  
    public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }

  public VirtualStorageResourceInfo reservationId(String reservationId) {
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

  public VirtualStorageResourceInfo metadata(KeyValuePairs metadata) {
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
    VirtualStorageResourceInfo virtualStorageResourceInfo = (VirtualStorageResourceInfo) o;
    return Objects.equals(this.id, virtualStorageResourceInfo.id) &&
        Objects.equals(this.virtualStorageDescId, virtualStorageResourceInfo.virtualStorageDescId) &&
        Objects.equals(this.vnfdId, virtualStorageResourceInfo.vnfdId) &&
        Objects.equals(this.storageResource, virtualStorageResourceInfo.storageResource) &&
        Objects.equals(this.zoneId, virtualStorageResourceInfo.zoneId) &&
        Objects.equals(this.reservationId, virtualStorageResourceInfo.reservationId) &&
        Objects.equals(this.metadata, virtualStorageResourceInfo.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, virtualStorageDescId, vnfdId, storageResource, zoneId, reservationId, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VirtualStorageResourceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    virtualStorageDescId: ").append(toIndentedString(virtualStorageDescId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    storageResource: ").append(toIndentedString(storageResource)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
    sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
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
