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
package com.ubiqube.etsi.mano.em.v351.model.vnffm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.ResourceHandle;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents the faulty virtual resources that have a negative impact
 * on a VNF.
 */
@Schema(description = "This type represents the faulty virtual resources that have a negative impact on a VNF. ")
@Validated

public class FaultyResourceInfo {
	@JsonProperty("faultyResource")
	private ResourceHandle faultyResource = null;

	@JsonProperty("faultyResourceType")
	private FaultyResourceType faultyResourceType = null;

	public FaultyResourceInfo faultyResource(final ResourceHandle faultyResource) {
		this.faultyResource = faultyResource;
		return this;
	}

	/**
	 * Get faultyResource
	 *
	 * @return faultyResource
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public ResourceHandle getFaultyResource() {
		return faultyResource;
	}

	public void setFaultyResource(final ResourceHandle faultyResource) {
		this.faultyResource = faultyResource;
	}

	public FaultyResourceInfo faultyResourceType(final FaultyResourceType faultyResourceType) {
		this.faultyResourceType = faultyResourceType;
		return this;
	}

	/**
	 * Get faultyResourceType
	 *
	 * @return faultyResourceType
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public FaultyResourceType getFaultyResourceType() {
		return faultyResourceType;
	}

	public void setFaultyResourceType(final FaultyResourceType faultyResourceType) {
		this.faultyResourceType = faultyResourceType;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final FaultyResourceInfo faultyResourceInfo = (FaultyResourceInfo) o;
		return Objects.equals(this.faultyResource, faultyResourceInfo.faultyResource) &&
				Objects.equals(this.faultyResourceType, faultyResourceInfo.faultyResourceType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(faultyResource, faultyResourceType);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class FaultyResourceInfo {\n");

		sb.append("    faultyResource: ").append(toIndentedString(faultyResource)).append("\n");
		sb.append("    faultyResourceType: ").append(toIndentedString(faultyResourceType)).append("\n");
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
