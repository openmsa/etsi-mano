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

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type provides information about added, deleted, modified and temporary virtual storage resources.
 */
@Schema(description = "This type provides information about added, deleted, modified and temporary virtual storage resources. ")
@Validated

public class AffectedVirtualStorage {
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

	@JsonProperty("storageResource")
	private ResourceHandle storageResource = null;

	@JsonProperty("resourceDefinitionId")
	private String resourceDefinitionId = null;

	@JsonProperty("zoneId")
	private String zoneId = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	public AffectedVirtualStorage id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public AffectedVirtualStorage virtualStorageDescId(final String virtualStorageDescId) {
		this.virtualStorageDescId = virtualStorageDescId;
		return this;
	}

	/**
	 * Get virtualStorageDescId
	 *
	 * @return virtualStorageDescId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVirtualStorageDescId() {
		return virtualStorageDescId;
	}

	public void setVirtualStorageDescId(final String virtualStorageDescId) {
		this.virtualStorageDescId = virtualStorageDescId;
	}

	public AffectedVirtualStorage vnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
		return this;
	}

	/**
	 * Get vnfdId
	 *
	 * @return vnfdId
	 **/
	@Schema(description = "")

	public String getVnfdId() {
		return vnfdId;
	}

	public void setVnfdId(final String vnfdId) {
		this.vnfdId = vnfdId;
	}

	public AffectedVirtualStorage changeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	/**
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure exists as long as the temporary resource exists.
	 *
	 * @return changeType
	 **/
	@Schema(required = true, description = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVirtualStorage structure exists as long as the temporary resource exists. ")
	@NotNull

	public ChangeTypeEnum getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
	}

	public AffectedVirtualStorage storageResource(final ResourceHandle storageResource) {
		this.storageResource = storageResource;
		return this;
	}

	/**
	 * Get storageResource
	 *
	 * @return storageResource
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public ResourceHandle getStorageResource() {
		return storageResource;
	}

	public void setStorageResource(final ResourceHandle storageResource) {
		this.storageResource = storageResource;
	}

	public AffectedVirtualStorage resourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
		return this;
	}

	/**
	 * Get resourceDefinitionId
	 *
	 * @return resourceDefinitionId
	 **/
	@Schema(description = "")

	public String getResourceDefinitionId() {
		return resourceDefinitionId;
	}

	public void setResourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
	}

	public AffectedVirtualStorage zoneId(final String zoneId) {
		this.zoneId = zoneId;
		return this;
	}

	/**
	 * Get zoneId
	 *
	 * @return zoneId
	 **/
	@Schema(description = "")

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public AffectedVirtualStorage metadata(final Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Get metadata
	 *
	 * @return metadata
	 **/
	@Schema(description = "")

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
		final AffectedVirtualStorage affectedVirtualStorage = (AffectedVirtualStorage) o;
		return Objects.equals(this.id, affectedVirtualStorage.id) &&
				Objects.equals(this.virtualStorageDescId, affectedVirtualStorage.virtualStorageDescId) &&
				Objects.equals(this.vnfdId, affectedVirtualStorage.vnfdId) &&
				Objects.equals(this.changeType, affectedVirtualStorage.changeType) &&
				Objects.equals(this.storageResource, affectedVirtualStorage.storageResource) &&
				Objects.equals(this.resourceDefinitionId, affectedVirtualStorage.resourceDefinitionId) &&
				Objects.equals(this.zoneId, affectedVirtualStorage.zoneId) &&
				Objects.equals(this.metadata, affectedVirtualStorage.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, virtualStorageDescId, vnfdId, changeType, storageResource, resourceDefinitionId, zoneId, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffectedVirtualStorage {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    virtualStorageDescId: ").append(toIndentedString(virtualStorageDescId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    storageResource: ").append(toIndentedString(storageResource)).append("\n");
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
