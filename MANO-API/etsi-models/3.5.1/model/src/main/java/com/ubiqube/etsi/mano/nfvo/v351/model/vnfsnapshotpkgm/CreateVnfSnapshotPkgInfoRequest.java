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
package com.ubiqube.etsi.mano.nfvo.v351.model.vnfsnapshotpkgm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents the request parameters for creating a new
 * \&quot;Individual VNF snapshot package\&quot; resource. It shall comply with
 * the provisions defined in table 11.5.2.2-1.
 */
@Schema(description = "This type represents the request parameters for creating a new \"Individual VNF snapshot package\" resource.  It shall comply with the provisions defined in table 11.5.2.2-1. ")
@Validated

public class CreateVnfSnapshotPkgInfoRequest {
	@JsonProperty("name")
	private String name = null;

	@JsonProperty("userDefinedData")
	private Map<String, String> userDefinedData = null;

	public CreateVnfSnapshotPkgInfoRequest name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Human-readable name of the VNF snapshot package.
	 *
	 * @return name
	 **/
	@Schema(required = true, description = "Human-readable name of the VNF snapshot package. ")
	@NotNull

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public CreateVnfSnapshotPkgInfoRequest userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Get userDefinedData
	 *
	 * @return userDefinedData
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final CreateVnfSnapshotPkgInfoRequest createVnfSnapshotPkgInfoRequest = (CreateVnfSnapshotPkgInfoRequest) o;
		return Objects.equals(this.name, createVnfSnapshotPkgInfoRequest.name) &&
				Objects.equals(this.userDefinedData, createVnfSnapshotPkgInfoRequest.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CreateVnfSnapshotPkgInfoRequest {\n");

		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
