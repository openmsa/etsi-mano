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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm.ResourceHandle;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted, modified and temporary VNFCs.  It shall comply with the provisions in table 5.5.3.13-1. NOTE: The \&quot;resourceDefinitionId\&quot; attribute provides information to the API consumer        (i.e. the NFVO) to assist in correlating the resource changes performed during        the LCM operation with the granted resources in a specific Grant exchange, which        is identified by the \&quot;grantId\&quot; available in the \&quot;Individual VNF lifecycle management        operation occurrence\&quot; and the \&quot;id\&quot; in the \&quot;Individual Grant\&quot;. 
 */
@Schema(description = "This type provides information about added, deleted, modified and temporary VNFCs.  It shall comply with the provisions in table 5.5.3.13-1. NOTE: The \"resourceDefinitionId\" attribute provides information to the API consumer        (i.e. the NFVO) to assist in correlating the resource changes performed during        the LCM operation with the granted resources in a specific Grant exchange, which        is identified by the \"grantId\" available in the \"Individual VNF lifecycle management        operation occurrence\" and the \"id\" in the \"Individual Grant\". ")
@Validated


public class AffectedVnfc   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vduId")
  private String vduId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  /**
   * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long as the temporary resource exists. 
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

  @JsonProperty("computeResource")
  private ResourceHandle computeResource = null;

  @JsonProperty("resourceDefinitionId")
  private String resourceDefinitionId = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  @JsonProperty("affectedVnfcCpIds")
  @Valid
  private List<String> affectedVnfcCpIds = null;

  @JsonProperty("addedStorageResourceIds")
  @Valid
  private List<String> addedStorageResourceIds = null;

  @JsonProperty("removedStorageResourceIds")
  @Valid
  private List<String> removedStorageResourceIds = null;

  public AffectedVnfc id(String id) {
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

  public AffectedVnfc vduId(String vduId) {
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

  public AffectedVnfc vnfdId(String vnfdId) {
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

  public AffectedVnfc changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long as the temporary resource exists. 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long as the temporary resource exists. ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVnfc computeResource(ResourceHandle computeResource) {
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

  public AffectedVnfc resourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
    return this;
  }

  /**
   * Get resourceDefinitionId
   * @return resourceDefinitionId
   **/
  @Schema(description = "")
  
    public String getResourceDefinitionId() {
    return resourceDefinitionId;
  }

  public void setResourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
  }

  public AffectedVnfc zoneId(String zoneId) {
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

  public AffectedVnfc metadata(KeyValuePairs metadata) {
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

  public AffectedVnfc affectedVnfcCpIds(List<String> affectedVnfcCpIds) {
    this.affectedVnfcCpIds = affectedVnfcCpIds;
    return this;
  }

  public AffectedVnfc addAffectedVnfcCpIdsItem(String affectedVnfcCpIdsItem) {
    if (this.affectedVnfcCpIds == null) {
      this.affectedVnfcCpIds = new ArrayList<>();
    }
    this.affectedVnfcCpIds.add(affectedVnfcCpIdsItem);
    return this;
  }

  /**
   * Identifiers of CP(s) of the VNFC instance that were affected by the change. Shall be present for those affected CPs of the VNFC instance that are associated to an external CP of the VNF instance. May be present for further affected CPs of the VNFC instance. 
   * @return affectedVnfcCpIds
   **/
  @Schema(description = "Identifiers of CP(s) of the VNFC instance that were affected by the change. Shall be present for those affected CPs of the VNFC instance that are associated to an external CP of the VNF instance. May be present for further affected CPs of the VNFC instance. ")
  
    public List<String> getAffectedVnfcCpIds() {
    return affectedVnfcCpIds;
  }

  public void setAffectedVnfcCpIds(List<String> affectedVnfcCpIds) {
    this.affectedVnfcCpIds = affectedVnfcCpIds;
  }

  public AffectedVnfc addedStorageResourceIds(List<String> addedStorageResourceIds) {
    this.addedStorageResourceIds = addedStorageResourceIds;
    return this;
  }

  public AffectedVnfc addAddedStorageResourceIdsItem(String addedStorageResourceIdsItem) {
    if (this.addedStorageResourceIds == null) {
      this.addedStorageResourceIds = new ArrayList<>();
    }
    this.addedStorageResourceIds.add(addedStorageResourceIdsItem);
    return this;
  }

  /**
   * References to VirtualStorage resources that have been added. Each value refers to a VirtualStorageResourceInfo item in the VnfInstance that was added to the VNFC. It shall be provided if at least one storage resource was added to the VNFC. 
   * @return addedStorageResourceIds
   **/
  @Schema(description = "References to VirtualStorage resources that have been added. Each value refers to a VirtualStorageResourceInfo item in the VnfInstance that was added to the VNFC. It shall be provided if at least one storage resource was added to the VNFC. ")
  
    public List<String> getAddedStorageResourceIds() {
    return addedStorageResourceIds;
  }

  public void setAddedStorageResourceIds(List<String> addedStorageResourceIds) {
    this.addedStorageResourceIds = addedStorageResourceIds;
  }

  public AffectedVnfc removedStorageResourceIds(List<String> removedStorageResourceIds) {
    this.removedStorageResourceIds = removedStorageResourceIds;
    return this;
  }

  public AffectedVnfc addRemovedStorageResourceIdsItem(String removedStorageResourceIdsItem) {
    if (this.removedStorageResourceIds == null) {
      this.removedStorageResourceIds = new ArrayList<>();
    }
    this.removedStorageResourceIds.add(removedStorageResourceIdsItem);
    return this;
  }

  /**
   * References to VirtualStorage resources that have been removed. The value contains the identifier of a VirtualStorageResourceInfo item that has been removed from the VNFC, and might no longer exist in the VnfInstance. It shall be provided if at least one storage resource was removed from the VNFC. 
   * @return removedStorageResourceIds
   **/
  @Schema(description = "References to VirtualStorage resources that have been removed. The value contains the identifier of a VirtualStorageResourceInfo item that has been removed from the VNFC, and might no longer exist in the VnfInstance. It shall be provided if at least one storage resource was removed from the VNFC. ")
  
    public List<String> getRemovedStorageResourceIds() {
    return removedStorageResourceIds;
  }

  public void setRemovedStorageResourceIds(List<String> removedStorageResourceIds) {
    this.removedStorageResourceIds = removedStorageResourceIds;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AffectedVnfc affectedVnfc = (AffectedVnfc) o;
    return Objects.equals(this.id, affectedVnfc.id) &&
        Objects.equals(this.vduId, affectedVnfc.vduId) &&
        Objects.equals(this.vnfdId, affectedVnfc.vnfdId) &&
        Objects.equals(this.changeType, affectedVnfc.changeType) &&
        Objects.equals(this.computeResource, affectedVnfc.computeResource) &&
        Objects.equals(this.resourceDefinitionId, affectedVnfc.resourceDefinitionId) &&
        Objects.equals(this.zoneId, affectedVnfc.zoneId) &&
        Objects.equals(this.metadata, affectedVnfc.metadata) &&
        Objects.equals(this.affectedVnfcCpIds, affectedVnfc.affectedVnfcCpIds) &&
        Objects.equals(this.addedStorageResourceIds, affectedVnfc.addedStorageResourceIds) &&
        Objects.equals(this.removedStorageResourceIds, affectedVnfc.removedStorageResourceIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vduId, vnfdId, changeType, computeResource, resourceDefinitionId, zoneId, metadata, affectedVnfcCpIds, addedStorageResourceIds, removedStorageResourceIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVnfc {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
    sb.append("    resourceDefinitionId: ").append(toIndentedString(resourceDefinitionId)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    affectedVnfcCpIds: ").append(toIndentedString(affectedVnfcCpIds)).append("\n");
    sb.append("    addedStorageResourceIds: ").append(toIndentedString(addedStorageResourceIds)).append("\n");
    sb.append("    removedStorageResourceIds: ").append(toIndentedString(removedStorageResourceIds)).append("\n");
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
