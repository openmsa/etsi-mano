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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents an externally provided link port to be used to connect a
 * VNFC connection point to an externally-managed VL. It shall comply with the
 * provisions defined in table 6.5.3.78-1.
 */
@Schema(description = "This type represents an externally provided link port to be used to connect a VNFC connection point to an externally-managed VL. It shall comply with the provisions defined in table 6.5.3.78-1. ")
@Validated

public class VnfLinkPortData {
	@JsonProperty("vnfLinkPortId")
	private String vnfLinkPortId = null;

	@JsonProperty("resourceHandle")
	private ResourceHandle resourceHandle = null;

	public VnfLinkPortData vnfLinkPortId(final String vnfLinkPortId) {
		this.vnfLinkPortId = vnfLinkPortId;
		return this;
	}

	/**
	 * Get vnfLinkPortId
	 *
	 * @return vnfLinkPortId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfLinkPortId() {
		return vnfLinkPortId;
	}

	public void setVnfLinkPortId(final String vnfLinkPortId) {
		this.vnfLinkPortId = vnfLinkPortId;
	}

	public VnfLinkPortData resourceHandle(final ResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
		return this;
	}

	/**
	 * Get resourceHandle
	 *
	 * @return resourceHandle
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public ResourceHandle getResourceHandle() {
		return resourceHandle;
	}

	public void setResourceHandle(final ResourceHandle resourceHandle) {
		this.resourceHandle = resourceHandle;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfLinkPortData vnfLinkPortData = (VnfLinkPortData) o;
		return Objects.equals(this.vnfLinkPortId, vnfLinkPortData.vnfLinkPortId) &&
				Objects.equals(this.resourceHandle, vnfLinkPortData.resourceHandle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfLinkPortId, resourceHandle);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfLinkPortData {\n");

		sb.append("    vnfLinkPortId: ").append(toIndentedString(vnfLinkPortId)).append("\n");
		sb.append("    resourceHandle: ").append(toIndentedString(resourceHandle)).append("\n");
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
