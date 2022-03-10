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
package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.ResourceHandle;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents resource definition information related to a snapshot
 * resource.
 */
@Schema(description = "This type represents resource definition information related to a snapshot resource. ")
@Validated

public class SnapshotResourceDefinition {
	@JsonProperty("vnfSnapshotId")
	private String vnfSnapshotId = null;

	@JsonProperty("vnfcSnapshotId")
	private String vnfcSnapshotId = null;

	@JsonProperty("storageSnapshotId")
	private String storageSnapshotId = null;

	@JsonProperty("snapshotResource")
	private ResourceHandle snapshotResource = null;

	public SnapshotResourceDefinition vnfSnapshotId(final String vnfSnapshotId) {
		this.vnfSnapshotId = vnfSnapshotId;
		return this;
	}

	/**
	 * Get vnfSnapshotId
	 *
	 * @return vnfSnapshotId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfSnapshotId() {
		return vnfSnapshotId;
	}

	public void setVnfSnapshotId(final String vnfSnapshotId) {
		this.vnfSnapshotId = vnfSnapshotId;
	}

	public SnapshotResourceDefinition vnfcSnapshotId(final String vnfcSnapshotId) {
		this.vnfcSnapshotId = vnfcSnapshotId;
		return this;
	}

	/**
	 * Get vnfcSnapshotId
	 *
	 * @return vnfcSnapshotId
	 **/
	@Schema(description = "")

	public String getVnfcSnapshotId() {
		return vnfcSnapshotId;
	}

	public void setVnfcSnapshotId(final String vnfcSnapshotId) {
		this.vnfcSnapshotId = vnfcSnapshotId;
	}

	public SnapshotResourceDefinition storageSnapshotId(final String storageSnapshotId) {
		this.storageSnapshotId = storageSnapshotId;
		return this;
	}

	/**
	 * Get storageSnapshotId
	 *
	 * @return storageSnapshotId
	 **/
	@Schema(description = "")

	public String getStorageSnapshotId() {
		return storageSnapshotId;
	}

	public void setStorageSnapshotId(final String storageSnapshotId) {
		this.storageSnapshotId = storageSnapshotId;
	}

	public SnapshotResourceDefinition snapshotResource(final ResourceHandle snapshotResource) {
		this.snapshotResource = snapshotResource;
		return this;
	}

	/**
	 * Get snapshotResource
	 *
	 * @return snapshotResource
	 **/
	@Schema(description = "")

	@Valid
	public ResourceHandle getSnapshotResource() {
		return snapshotResource;
	}

	public void setSnapshotResource(final ResourceHandle snapshotResource) {
		this.snapshotResource = snapshotResource;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final SnapshotResourceDefinition snapshotResourceDefinition = (SnapshotResourceDefinition) o;
		return Objects.equals(this.vnfSnapshotId, snapshotResourceDefinition.vnfSnapshotId) &&
				Objects.equals(this.vnfcSnapshotId, snapshotResourceDefinition.vnfcSnapshotId) &&
				Objects.equals(this.storageSnapshotId, snapshotResourceDefinition.storageSnapshotId) &&
				Objects.equals(this.snapshotResource, snapshotResourceDefinition.snapshotResource);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfSnapshotId, vnfcSnapshotId, storageSnapshotId, snapshotResource);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SnapshotResourceDefinition {\n");

		sb.append("    vnfSnapshotId: ").append(toIndentedString(vnfSnapshotId)).append("\n");
		sb.append("    vnfcSnapshotId: ").append(toIndentedString(vnfcSnapshotId)).append("\n");
		sb.append("    storageSnapshotId: ").append(toIndentedString(storageSnapshotId)).append("\n");
		sb.append("    snapshotResource: ").append(toIndentedString(snapshotResource)).append("\n");
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
