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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type provides information about added, deleted, modified and temporary
 * VNFCs.
 */
@Schema(description = "This type provides information about added, deleted, modified and temporary VNFCs. ")
@Validated

public class AffectedVnfc {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("vduId")
	private String vduId = null;

	@JsonProperty("vnfdId")
	private String vnfdId = null;

	/**
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
	 * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long
	 * as the temporary resource exists.
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

	@JsonProperty("computeResource")
	private ResourceHandle computeResource = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	@JsonProperty("affectedVnfcCpIds")
	@Valid
	private List<String> affectedVnfcCpIds = null;

	@JsonProperty("addedStorageResourceIds")
	@Valid
	private List<String> addedStorageResourceIds = null;

	@JsonProperty("removedStorageResourceIds")
	@Valid
	private List<String> removedStorageResourceIds = null;

	public AffectedVnfc id(final String id) {
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

	public AffectedVnfc vduId(final String vduId) {
		this.vduId = vduId;
		return this;
	}

	/**
	 * Get vduId
	 *
	 * @return vduId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVduId() {
		return vduId;
	}

	public void setVduId(final String vduId) {
		this.vduId = vduId;
	}

	public AffectedVnfc vnfdId(final String vnfdId) {
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

	public AffectedVnfc changeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
		return this;
	}

	/**
	 * Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED *
	 * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long
	 * as the temporary resource exists.
	 *
	 * @return changeType
	 **/
	@Schema(required = true, description = "Signals the type of change. Permitted values: * ADDED * REMOVED * MODIFIED * TEMPORARY For a temporary resource, an AffectedVnfc structure exists as long as the temporary resource exists. ")
	@NotNull

	public ChangeTypeEnum getChangeType() {
		return changeType;
	}

	public void setChangeType(final ChangeTypeEnum changeType) {
		this.changeType = changeType;
	}

	public AffectedVnfc computeResource(final ResourceHandle computeResource) {
		this.computeResource = computeResource;
		return this;
	}

	/**
	 * Get computeResource
	 *
	 * @return computeResource
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public ResourceHandle getComputeResource() {
		return computeResource;
	}

	public void setComputeResource(final ResourceHandle computeResource) {
		this.computeResource = computeResource;
	}

	public AffectedVnfc metadata(final Map<String, String> metadata) {
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

	public AffectedVnfc affectedVnfcCpIds(final List<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
		return this;
	}

	public AffectedVnfc addAffectedVnfcCpIdsItem(final String affectedVnfcCpIdsItem) {
		if (this.affectedVnfcCpIds == null) {
			this.affectedVnfcCpIds = new ArrayList<>();
		}
		this.affectedVnfcCpIds.add(affectedVnfcCpIdsItem);
		return this;
	}

	/**
	 * Identifiers of CP(s) of the VNFC instance that were affected by the change.
	 * Shall be present for those affected CPs of the VNFC instance that are
	 * associated to an external CP of the VNF instance. May be present for further
	 * affected CPs of the VNFC instance.
	 *
	 * @return affectedVnfcCpIds
	 **/
	@Schema(description = "Identifiers of CP(s) of the VNFC instance that were affected by the change. Shall be present for those affected CPs of the VNFC instance that are associated to an external CP of the VNF instance. May be present for further affected CPs of the VNFC instance. ")

	public List<String> getAffectedVnfcCpIds() {
		return affectedVnfcCpIds;
	}

	public void setAffectedVnfcCpIds(final List<String> affectedVnfcCpIds) {
		this.affectedVnfcCpIds = affectedVnfcCpIds;
	}

	public AffectedVnfc addedStorageResourceIds(final List<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
		return this;
	}

	public AffectedVnfc addAddedStorageResourceIdsItem(final String addedStorageResourceIdsItem) {
		if (this.addedStorageResourceIds == null) {
			this.addedStorageResourceIds = new ArrayList<>();
		}
		this.addedStorageResourceIds.add(addedStorageResourceIdsItem);
		return this;
	}

	/**
	 * References to VirtualStorage resources that have been added. Each value
	 * refers to a VirtualStorageResourceInfo item in the VnfInstance that was added
	 * to the VNFC. It shall be provided if at least one storage resource was added
	 * to the VNFC.
	 *
	 * @return addedStorageResourceIds
	 **/
	@Schema(description = "References to VirtualStorage resources that have been added. Each value refers to a VirtualStorageResourceInfo item in the VnfInstance that was added to the VNFC. It shall be provided if at least one storage resource was added to the VNFC. ")

	public List<String> getAddedStorageResourceIds() {
		return addedStorageResourceIds;
	}

	public void setAddedStorageResourceIds(final List<String> addedStorageResourceIds) {
		this.addedStorageResourceIds = addedStorageResourceIds;
	}

	public AffectedVnfc removedStorageResourceIds(final List<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
		return this;
	}

	public AffectedVnfc addRemovedStorageResourceIdsItem(final String removedStorageResourceIdsItem) {
		if (this.removedStorageResourceIds == null) {
			this.removedStorageResourceIds = new ArrayList<>();
		}
		this.removedStorageResourceIds.add(removedStorageResourceIdsItem);
		return this;
	}

	/**
	 * References to VirtualStorage resources that have been removed. The value
	 * contains the identifier of a VirtualStorageResourceInfo item that has been
	 * removed from the VNFC, and might no longer exist in the VnfInstance. It shall
	 * be provided if at least one storage resource was removed from the VNFC.
	 *
	 * @return removedStorageResourceIds
	 **/
	@Schema(description = "References to VirtualStorage resources that have been removed. The value contains the identifier of a VirtualStorageResourceInfo item that has been removed from the VNFC, and might no longer exist in the VnfInstance. It shall be provided if at least one storage resource was removed from the VNFC. ")

	public List<String> getRemovedStorageResourceIds() {
		return removedStorageResourceIds;
	}

	public void setRemovedStorageResourceIds(final List<String> removedStorageResourceIds) {
		this.removedStorageResourceIds = removedStorageResourceIds;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final AffectedVnfc affectedVnfc = (AffectedVnfc) o;
		return Objects.equals(this.id, affectedVnfc.id) &&
				Objects.equals(this.vduId, affectedVnfc.vduId) &&
				Objects.equals(this.vnfdId, affectedVnfc.vnfdId) &&
				Objects.equals(this.changeType, affectedVnfc.changeType) &&
				Objects.equals(this.computeResource, affectedVnfc.computeResource) &&
				Objects.equals(this.metadata, affectedVnfc.metadata) &&
				Objects.equals(this.affectedVnfcCpIds, affectedVnfc.affectedVnfcCpIds) &&
				Objects.equals(this.addedStorageResourceIds, affectedVnfc.addedStorageResourceIds) &&
				Objects.equals(this.removedStorageResourceIds, affectedVnfc.removedStorageResourceIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, vduId, vnfdId, changeType, computeResource, metadata, affectedVnfcCpIds, addedStorageResourceIds, removedStorageResourceIds);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class AffectedVnfc {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    vduId: ").append(toIndentedString(vduId)).append("\n");
		sb.append("    vnfdId: ").append(toIndentedString(vnfdId)).append("\n");
		sb.append("    changeType: ").append(toIndentedString(changeType)).append("\n");
		sb.append("    computeResource: ").append(toIndentedString(computeResource)).append("\n");
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
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
