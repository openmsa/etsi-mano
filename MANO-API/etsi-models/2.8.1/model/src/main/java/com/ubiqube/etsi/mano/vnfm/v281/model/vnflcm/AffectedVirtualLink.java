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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type provides information about added, deleted, modified and temporary VLs, and added or removed VNF link ports. It shall comply with the provisions in table 5.5.3.14-1.
 */
@ApiModel(description = "This type provides information about added, deleted, modified and temporary VLs, and added or removed VNF link ports.  It shall comply with the provisions in table 5.5.3.14-1. ")
@Validated

public class AffectedVirtualLink {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vnfVirtualLinkDescId")
	private UUID vnfVirtualLinkDescId = null;

	/**
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual link instance, not the link port instance. The resource handles
	 * of the affected VNF link ports can be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute.
	 */
	public enum ChangeTypeEnum {
		ADDED("ADDED"),

		REMOVED("REMOVED"),

		MODIFIED("MODIFIED"),

		TEMPORARY("TEMPORARY"),

		LINK_PORT_ADDED("LINK_PORT_ADDED"),

		LINK_PORT_REMOVED("LINK_PORT_REMOVED");

		private final String value;

		ChangeTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ChangeTypeEnum fromValue(final String text) {
			for (final ChangeTypeEnum b : ChangeTypeEnum.values()) {
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
	private Map<String, String> metadata = null;

	public AffectedVirtualLink id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the virtual link instance, identifying the applicable \"vnfVirtualLinkResourceInfo\" entry in the \"VnfInstance\" data type.
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the virtual link instance, identifying the applicable \"vnfVirtualLinkResourceInfo\" entry in the \"VnfInstance\" data type. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public AffectedVirtualLink vnfVirtualLinkDescId(final UUID vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
		return this;
	}

	/**
	 * Identifier of the related VLD in the VNFD.
	 *
	 * @return vnfVirtualLinkDescId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of the related VLD in the VNFD. ")
	@NotNull

	public UUID getVnfVirtualLinkDescId() {
		return vnfVirtualLinkDescId;
	}

	public void setVnfVirtualLinkDescId(final UUID vnfVirtualLinkDescId) {
		this.vnfVirtualLinkDescId = vnfVirtualLinkDescId;
	}

	public AffectedVirtualLink changeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	/**
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual link instance, not the link port instance. The resource handles
	 * of the affected VNF link ports can be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute.
	 *
	 * @return changeType
	 **/
	@ApiModelProperty(required = true, value = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY * LINK_PORT_ADDED * LINK_PORT_REMOVED For a temporary resource, an AffectedVirtualLink structure exists as long as the temporary resource exists. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link  ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual  link instance, not the link port instance. The resource handles of the affected VNF link ports can  be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute. ")
	@NotNull

	public ChangeTypeEnum getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
	}

	public AffectedVirtualLink networkResource(final ResourceHandle networkResource) {
		this.networkResource = networkResource;
		return this;
	}

	/**
	 * Reference to the VirtualNetwork resource. Detailed information is (for new and modified resources) or has been (for removed resources) available from the VIM. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual link instance, not the link port instance. The resource handles of the affected VNF link ports can be found by dereferencing the
	 * identifiers in the \"vnfLinkPortIds\" attribute.
	 *
	 * @return networkResource
	 **/
	@ApiModelProperty(required = true, value = "Reference to the VirtualNetwork resource. Detailed information is (for new and modified resources) or has been (for removed resources) available from the VIM. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link  ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual  link instance, not the link port instance. The resource handles of the affected VNF link ports can  be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute. ")
	@NotNull

	@Valid

	public ResourceHandle getNetworkResource() {
		return networkResource;
	}

	public void setNetworkResource(final ResourceHandle networkResource) {
		this.networkResource = networkResource;
	}

	public AffectedVirtualLink vnfLinkPortIds(final List<String> vnfLinkPortIds) {
		this.vnfLinkPortIds = vnfLinkPortIds;
		return this;
	}

	public AffectedVirtualLink addVnfLinkPortIdsItem(final String vnfLinkPortIdsItem) {
		if (this.vnfLinkPortIds == null) {
			this.vnfLinkPortIds = new ArrayList<>();
		}
		this.vnfLinkPortIds.add(vnfLinkPortIdsItem);
		return this;
	}

	/**
	 * Identifiers of the link ports of the affected VL related to the change. Each identifier references a \"VnfLinkPortInfo\" structure. Shall be set when changeType is equal to \"LINK_PORT_ADDED\" or \"LINK_PORT_REMOVED\", and the related \"VnfLinkPortInfo\" structures are present (case \"added\") or have been present (case \"removed\") in the \"VnfVirtualLinkResourceInfo\" or \"ExtManagedVirtualLinkInfo\" structures that are represented by the \"vnfVirtualLinkResource¬Info\" or
	 * \"extManagedVirtualLinkInfo\" attribute in the \"VnfInstance\" structure. See note 1. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual link instance, not the link port instance. The resource handles of the affected VNF link ports can be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute.
	 *
	 * @return vnfLinkPortIds
	 **/
	@ApiModelProperty(value = "Identifiers of the link ports of the affected VL related to the change.  Each identifier references a \"VnfLinkPortInfo\" structure. Shall be set when changeType is equal to \"LINK_PORT_ADDED\" or \"LINK_PORT_REMOVED\",  and the related \"VnfLinkPortInfo\" structures are present (case \"added\") or have  been present (case \"removed\") in the \"VnfVirtualLinkResourceInfo\" or \"ExtManagedVirtualLinkInfo\"  structures that are represented by the \"vnfVirtualLinkResource¬Info\" or \"extManagedVirtualLinkInfo\"  attribute in the \"VnfInstance\" structure. See note 1. NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link  ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual  link instance, not the link port instance. The resource handles of the affected VNF link ports can  be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute. ")

	public List<String> getVnfLinkPortIds() {
		return vnfLinkPortIds;
	}

	public void setVnfLinkPortIds(final List<String> vnfLinkPortIds) {
		this.vnfLinkPortIds = vnfLinkPortIds;
	}

	public AffectedVirtualLink resourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
		return this;
	}

	/**
	 * The identifier of the \"ResourceDefinition\" in the granting exchange related to the LCM operation occurrence. It shall be present when an applicable GrantInfo for the granted resource exists. The \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO) to assist in correlating the resource changes performed during the LCM operation with the granted resources in a specific Grant exchange, which is identified by the \"grantId\" available in the \"Individual
	 * VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\". NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual link instance, not the link port instance. The resource handles of the affected VNF link ports can be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute. NOTE 2: The
	 * \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO) to assist in correlating the resource changes performed during the LCM operation with the granted resources in a specific Grant exchange, which is identified by the \"grantId\" available in the \"Individual VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\".
	 *
	 * @return resourceDefinitionId
	 **/
	@ApiModelProperty(value = "The identifier of the \"ResourceDefinition\" in the granting exchange related to the LCM operation occurrence. It shall be present when an applicable GrantInfo for the granted resource exists. The \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO) to assist in correlating the resource changes performed during the LCM operation with the granted resources in a specific Grant exchange, which is identified by the \"grantId\" available in the \"Individual VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\". NOTE 1: When signalling the addition (LINK_PORT_ADDED) or removal (LINK_PORT_REMOVED) of VNF link  ports, the \"networkResource\" and \"resourceDefinitionId\" attributes refer to the affected virtual  link instance, not the link port instance. The resource handles of the affected VNF link ports can  be found by dereferencing the identifiers in the \"vnfLinkPortIds\" attribute. NOTE 2: The \"resourceDefinitionId\" attribute provides information to the API consumer (i.e. the NFVO)  to assist in correlating the resource changes performed during the LCM operation with the granted  resources in a specific Grant exchange, which is identified by the \"grantId\" available in the \"Individual  VNF lifecycle management operation occurrence\" and the \"id\" in the \"Individual Grant\". ")

	public String getResourceDefinitionId() {
		return resourceDefinitionId;
	}

	public void setResourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
	}

	public AffectedVirtualLink zoneId(final String zoneId) {
		this.zoneId = zoneId;
		return this;
	}

	/**
	 * The identifier of the resource zone, as managed by the resource management layer (typically, the VIM), where the referenced VirtualNetwork resource is placed. Shall be provided if this information is available from the VIM.
	 *
	 * @return zoneId
	 **/
	@ApiModelProperty(value = "The identifier of the resource zone, as managed by the resource management layer (typically, the VIM), where the referenced VirtualNetwork resource is placed. Shall be provided if this information is available from the VIM. ")

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public AffectedVirtualLink metadata(final Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Metadata about this resource. The content of this attribute shall be a copy of the content of the \"metadata\" attribute of the VnfVirtualLinkResourceInfo structure.
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "Metadata about this resource. The content of this attribute shall be a copy of the content of the \"metadata\" attribute of the VnfVirtualLinkResourceInfo structure. ")

	@Valid

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final AffectedVirtualLink affectedVirtualLink = (AffectedVirtualLink) o;
		return Objects.equals(this.id, affectedVirtualLink.id) &&
				Objects.equals(this.vnfVirtualLinkDescId, affectedVirtualLink.vnfVirtualLinkDescId) &&
				Objects.equals(this.changeType, affectedVirtualLink.changeType) &&
				Objects.equals(this.networkResource, affectedVirtualLink.networkResource) &&
				Objects.equals(this.vnfLinkPortIds, affectedVirtualLink.vnfLinkPortIds) &&
				Objects.equals(this.resourceDefinitionId, affectedVirtualLink.resourceDefinitionId) &&
				Objects.equals(this.zoneId, affectedVirtualLink.zoneId) &&
				Objects.equals(this.metadata, affectedVirtualLink.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vnfVirtualLinkDescId, changeType, networkResource, vnfLinkPortIds, resourceDefinitionId, zoneId, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffectedVirtualLink {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vnfVirtualLinkDescId: ").append(toIndentedString(vnfVirtualLinkDescId)).append("\n");
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
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
