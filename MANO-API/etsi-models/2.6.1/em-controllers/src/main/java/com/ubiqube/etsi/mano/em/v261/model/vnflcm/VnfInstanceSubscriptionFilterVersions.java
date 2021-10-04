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
package com.ubiqube.etsi.mano.em.v261.model.vnflcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * VnfInstanceSubscriptionFilterVersions
 */
@Validated

public class VnfInstanceSubscriptionFilterVersions {
	@JsonProperty("vnfSoftwareVersion")
	private String vnfSoftwareVersion = null;

	@JsonProperty("vnfdVersions")
	@Valid
	private List<String> vnfdVersions = null;

	public VnfInstanceSubscriptionFilterVersions vnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
		return this;
	}

	/**
	 * Software version to match.
	 *
	 * @return vnfSoftwareVersion
	 **/
	@Schema(required = true, description = "Software version to match. ")
	@NotNull

	public String getVnfSoftwareVersion() {
		return vnfSoftwareVersion;
	}

	public void setVnfSoftwareVersion(final String vnfSoftwareVersion) {
		this.vnfSoftwareVersion = vnfSoftwareVersion;
	}

	public VnfInstanceSubscriptionFilterVersions vnfdVersions(final List<String> vnfdVersions) {
		this.vnfdVersions = vnfdVersions;
		return this;
	}

	public VnfInstanceSubscriptionFilterVersions addVnfdVersionsItem(final String vnfdVersionsItem) {
		if (this.vnfdVersions == null) {
			this.vnfdVersions = new ArrayList<>();
		}
		this.vnfdVersions.add(vnfdVersionsItem);
		return this;
	}

	/**
	 * If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider.
	 *
	 * @return vnfdVersions
	 **/
	@Schema(description = "If present, match VNF instances that belong to VNF products with certain VNFD versions, a certain software version and a certain product name, from one particular provider. ")

	public List<String> getVnfdVersions() {
		return vnfdVersions;
	}

	public void setVnfdVersions(final List<String> vnfdVersions) {
		this.vnfdVersions = vnfdVersions;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstanceSubscriptionFilterVersions vnfInstanceSubscriptionFilterVersions = (VnfInstanceSubscriptionFilterVersions) o;
		return Objects.equals(this.vnfSoftwareVersion, vnfInstanceSubscriptionFilterVersions.vnfSoftwareVersion) &&
				Objects.equals(this.vnfdVersions, vnfInstanceSubscriptionFilterVersions.vnfdVersions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfSoftwareVersion, vnfdVersions);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceSubscriptionFilterVersions {\n");

		sb.append("    vnfSoftwareVersion: ").append(toIndentedString(vnfSoftwareVersion)).append("\n");
		sb.append("    vnfdVersions: ").append(toIndentedString(vnfdVersions)).append("\n");
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
