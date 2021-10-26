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
 * This type provides information about added, deleted, modified and temporary VLs, and added or removed VNF link ports. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports,          the \&quot;networkResource\&quot; and \&quot;resourceDefinitionId\&quot; attributes refer to the affected virtual link          instance, not the link port instance. The resource handles of the affected VNF link ports can be          found by dereferencing the identifiers in the \&quot;vnfLinkPortIds\&quot; attribute. NOTE 2: The \&quot;resourceDefinitionId\&quot; attribute provides information to the API consumer (i.e. the NFVO) to          assist in correlating the resource changes performed during the LCM operation with the granted          resources in a specific Grant exchange, which is identified by the \&quot;grantId\&quot; available in the          \&quot;Individual VNF lifecycle management operation occurrence\&quot; and the \&quot;id\&quot; in the \&quot;Individual Grant\&quot;. 
 */
@Schema(description = "This type provides information about added, deleted, modified and temporary VLs, and added or removed VNF link ports. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports,          the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual link          instance, not the link port instance. The resource handles of the affected VNF link ports can be          found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute. NOTE 2: The \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO) to          assist in correlating the resource changes performed during the LCM operation with the granted          resources in a specific Grant exchange, which is identified by the \"grantId\" available in the          \"Individual VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\". ")
@Validated


public class AffectedVirtualLink   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  @JsonProperty("vnfdId")
  private String vnfdId = null;

  /**
   * Signals the type of change. Permitted values: - ADDED - REMOVED - MODIFIED - TEMPORARY - LINK_PORT_ADDED - LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists.  See note 1. 
   */
  public enum ChangeTypeEnum {
    ADDED("ADDED"),
    
    REMOVED("REMOVED"),
    
    MODIFIED("MODIFIED"),
    
    TEMPORARY("TEMPORARY"),
    
    LINK_PORT_ADDED("LINK_PORT_ADDED"),
    
    LINK_PORT_REMOVED("LINK_PORT_REMOVED");

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

  @JsonProperty("networkResource")
  private ResourceHandle networkResource = null;

  @JsonProperty("vnfLinkPortIds")
  @Valid
  private List<String> vnfLinkPortIds = null;

  @JsonProperty("resourceDefinitionId")
  private String resourceDefinitionId = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  @JsonProperty("metadata")
  private KeyValuePairs metadata = null;

  public AffectedVirtualLink id(String id) {
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

  public AffectedVirtualLink vnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
    return this;
  }

  /**
   * Get vnfVirtualLinkDescId
   * @return vnfVirtualLinkDescId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfVirtualLinkDescId() {
    return vnfVirtualLinkDescId;
  }

  public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
  }

  public AffectedVirtualLink vnfdId(String vnfdId) {
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

  public AffectedVirtualLink changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: - ADDED - REMOVED - MODIFIED - TEMPORARY - LINK_PORT_ADDED - LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists.  See note 1. 
   * @return changeType
   **/
  @Schema(required = true, description = "Signals the type of change. Permitted values: - ADDED - REMOVED - MODIFIED - TEMPORARY - LINK_PORT_ADDED - LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists.  See note 1. ")
      @NotNull

    public ChangeTypeEnum getChangeType() {
    return changeType;
  }

  public void setChangeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
  }

  public AffectedVirtualLink networkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
    return this;
  }

  /**
   * Get networkResource
   * @return networkResource
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public ResourceHandle getNetworkResource() {
    return networkResource;
  }

  public void setNetworkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
  }

  public AffectedVirtualLink vnfLinkPortIds(List<String> vnfLinkPortIds) {
    this.vnfLinkPortIds = vnfLinkPortIds;
    return this;
  }

  public AffectedVirtualLink addVnfLinkPortIdsItem(String vnfLinkPortIdsItem) {
    if (this.vnfLinkPortIds == null) {
      this.vnfLinkPortIds = new ArrayList<>();
    }
    this.vnfLinkPortIds.add(vnfLinkPortIdsItem);
    return this;
  }

  /**
   * Identifiers of the link ports of the affected VL related to the change. Each identifier references a \"VnfLinkPortInfo\"  structure. Shall be set when changeType is equal to \"LINK_PORT_ADDED\" or \"LINK_PORT_REMOVED\", and the related \"VnfLinkPortInfo\"  structures are present (case \"added\") or have been present (case \"removed\") in the \"VnfVirtualLinkResourceInfo\" or  \"ExtManagedVirtualLinkInfo\" structures that are represented by the \"vnfVirtualLinkResource¬Info\" or \"extManagedVirtualLinkInfo\"  attribute in the \"VnfInstance\" structure. See note 1. 
   * @return vnfLinkPortIds
   **/
  @Schema(description = "Identifiers of the link ports of the affected VL related to the change. Each identifier references a \"VnfLinkPortInfo\"  structure. Shall be set when changeType is equal to \"LINK_PORT_ADDED\" or \"LINK_PORT_REMOVED\", and the related \"VnfLinkPortInfo\"  structures are present (case \"added\") or have been present (case \"removed\") in the \"VnfVirtualLinkResourceInfo\" or  \"ExtManagedVirtualLinkInfo\" structures that are represented by the \"vnfVirtualLinkResource¬Info\" or \"extManagedVirtualLinkInfo\"  attribute in the \"VnfInstance\" structure. See note 1. ")
  
    public List<String> getVnfLinkPortIds() {
    return vnfLinkPortIds;
  }

  public void setVnfLinkPortIds(List<String> vnfLinkPortIds) {
    this.vnfLinkPortIds = vnfLinkPortIds;
  }

  public AffectedVirtualLink resourceDefinitionId(String resourceDefinitionId) {
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

  public AffectedVirtualLink zoneId(String zoneId) {
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

  public AffectedVirtualLink metadata(KeyValuePairs metadata) {
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
    AffectedVirtualLink affectedVirtualLink = (AffectedVirtualLink) o;
    return Objects.equals(this.id, affectedVirtualLink.id) &&
        Objects.equals(this.vnfVirtualLinkDescId, affectedVirtualLink.vnfVirtualLinkDescId) &&
        Objects.equals(this.vnfdId, affectedVirtualLink.vnfdId) &&
        Objects.equals(this.changeType, affectedVirtualLink.changeType) &&
        Objects.equals(this.networkResource, affectedVirtualLink.networkResource) &&
        Objects.equals(this.vnfLinkPortIds, affectedVirtualLink.vnfLinkPortIds) &&
        Objects.equals(this.resourceDefinitionId, affectedVirtualLink.resourceDefinitionId) &&
        Objects.equals(this.zoneId, affectedVirtualLink.zoneId) &&
        Objects.equals(this.metadata, affectedVirtualLink.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfVirtualLinkDescId, vnfdId, changeType, networkResource, vnfLinkPortIds, resourceDefinitionId, zoneId, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVirtualLink {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
    sb.append("    vnfLinkPortIds: ").append(toIndentedString(vnfLinkPortIds)).append("\n");
    sb.append("    resourceDefinitionId: ").append(toIndentedString(resourceDefinitionId)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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
