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
package com.ubiqube.etsi.mano.em.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.KeyValuePairs;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.ResourceHandle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type provides information about added, deleted, modified and temporary VLs, and added or removed VNF link ports. It shall comply with the provisions in table 5.5.3.14-1. 
 */
@ApiModel(description = "This type provides information about added, deleted, modified and temporary VLs, and added or removed VNF link ports. It shall comply with the provisions in table 5.5.3.14-1. ")
@Validated

public class AffectedVirtualLink   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("vnfVirtualLinkDescId")
  private String vnfVirtualLinkDescId = null;

  /**
   * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. 
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
   * Identifier of the virtual link instance, identifying the applicable \"vnfVirtualLinkResourceInfo\" entry in the \"VnfInstance\" data type. 
   * @return id
  **/
  @ApiModelProperty(required = true, value = "Identifier of the virtual link instance, identifying the applicable \"vnfVirtualLinkResourceInfo\" entry in the \"VnfInstance\" data type. ")
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
   * Identifier of the related VLD in the VNFD. 
   * @return vnfVirtualLinkDescId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the related VLD in the VNFD. ")
  @NotNull


  public String getVnfVirtualLinkDescId() {
    return vnfVirtualLinkDescId;
  }

  public void setVnfVirtualLinkDescId(String vnfVirtualLinkDescId) {
    this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
  }

  public AffectedVirtualLink changeType(ChangeTypeEnum changeType) {
    this.changeType = changeType;
    return this;
  }

  /**
   * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. 
   * @return changeType
  **/
  @ApiModelProperty(required = true, value = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. ")
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
   * Reference to the VirtualNetwork resource. Detailed information is (for new and modified resources) or has been (for removed resources) available from the VIM. 
   * @return networkResource
  **/
  @ApiModelProperty(required = true, value = "Reference to the VirtualNetwork resource. Detailed information is (for new and modified resources) or has been (for removed resources) available from the VIM. ")
  @NotNull

  @Valid

  public ResourceHandle getNetworkResource() {
    return networkResource;
  }

  public void setNetworkResource(ResourceHandle networkResource) {
    this.networkResource = networkResource;
  }

  public AffectedVirtualLink resourceDefinitionId(String resourceDefinitionId) {
    this.resourceDefinitionId = resourceDefinitionId;
    return this;
  }

  /**
   * The identifier of the \"ResourceDefinition\" in the granting exchange related to the LCM operation occurrence. It shall be present when an applicable GrantInfo for the granted resource exists. The \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO) to assist in correlating the resource changes performed during the LCM operation with the granted resources in a specific Grant exchange, which is identified by the \"grantId\" available in the \"Individual VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\". 
   * @return resourceDefinitionId
  **/
  @ApiModelProperty(value = "The identifier of the \"ResourceDefinition\" in the granting exchange related to the LCM operation occurrence. It shall be present when an applicable GrantInfo for the granted resource exists. The \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO) to assist in correlating the resource changes performed during the LCM operation with the granted resources in a specific Grant exchange, which is identified by the \"grantId\" available in the \"Individual VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\". ")


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
   * The identifier of the resource zone, as managed by the resource management layer (typically, the VIM), where the referenced VirtualNetwork resource is placed. Shall be provided if this information is available from the VIM. 
   * @return zoneId
  **/
  @ApiModelProperty(value = "The identifier of the resource zone, as managed by the resource management layer (typically, the VIM), where the referenced VirtualNetwork resource is placed. Shall be provided if this information is available from the VIM. ")


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
   * Metadata about this resource. The content of this attribute shall be a copy of the content of the \"metadata\" attribute of the VnfVirtualLinkResourceInfo structure. 
   * @return metadata
  **/
  @ApiModelProperty(value = "Metadata about this resource. The content of this attribute shall be a copy of the content of the \"metadata\" attribute of the VnfVirtualLinkResourceInfo structure. ")

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
        Objects.equals(this.changeType, affectedVirtualLink.changeType) &&
        Objects.equals(this.networkResource, affectedVirtualLink.networkResource) &&
        Objects.equals(this.resourceDefinitionId, affectedVirtualLink.resourceDefinitionId) &&
        Objects.equals(this.zoneId, affectedVirtualLink.zoneId) &&
        Objects.equals(this.metadata, affectedVirtualLink.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, vnfVirtualLinkDescId, changeType, networkResource, resourceDefinitionId, zoneId, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AffectedVirtualLink {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
    sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
    sb.append("    networkResource: ").append(toIndentedString(networkResource)).append("\n");
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

