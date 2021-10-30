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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted, modified and temporary virtual storage resources. 
 */
@Schema(description = "This type provides information about added, deleted, modified and temporary virtual storage resources. ")
@Validated


public class AffectedVirtualStorage   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("virtualStorageDescId")
  private String virtualStorageDescId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  /**
   * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure exists as long as the temporary resource exists. 
   */
  public enum ChangeTypeEnum {
    ADDED("ADDED"),
    
    REMOVED("REMOVED"),
    
    MODIFIED("MODIFIED"),
    
    TEMPORARY("TEMPORARY");

    private String value;

    ChangeTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ChangeTypeEnum fromValue(String text) {
      for (ChangeTypeEnum b : ChangeTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("changeType")
  private ChangeTypeEnum changeType = null;

  @JsonProperty("storageResource")
  private ResourceHandle storageResource = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public AffectedVirtualStorage id(String id) {
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

  public AffectedVirtualStorage virtualStorageDescId(String virtualStorageDescId) {
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

  public AffectedVirtualStorage vnfdId(String vnfdId) {
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

  public AffectedVirtualStorage changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure exists as long as the temporary resource exists. 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure exists as long as the temporary resource exists. ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVirtualStorage storageResource(ResourceHandle storageResource) {
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

  public AffectedVirtualStorage metadata(KeyValuePairs metadata) {
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
    AffectedVirtualStorage affectedVirtualStorage = (AffectedVirtualStorage) o;
    return Objects.equals(this.id, affectedVirtualStorage.id) &&
        Objects.equals(this.virtualStorageDescId, affectedVirtualStorage.virtualStorageDescId) &&
        Objects.equals(this.vnfdId, affectedVirtualStorage.vnfdId) &&
        Objects.equals(this.changeType, affectedVirtualStorage.changeType) &&
        Objects.equals(this.storageResource, affectedVirtualStorage.storageResource) &&
        Objects.equals(this.metadata, affectedVirtualStorage.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, virtualStorageDescId, vnfdId, changeType, storageResource, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVirtualStorage {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    virtualStorageDescId: ").append(toIndentedString(virtualStorageDescId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    storageResource: ").append(toIndentedString(storageResource)).append("\n");
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
