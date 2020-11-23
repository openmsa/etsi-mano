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

package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the association of location constraints to a VNF
 * instance to be created according to a specific VNF profile. It shall comply
 * with the provisions defined in Table 6.5.3.20-1.
 */
@ApiModel(description = "This type represents the association of location constraints to a VNF instance to be created according to a specific VNF profile. It shall comply with the provisions defined in Table 6.5.3.20-1. ")
@Validated


public class VnfLocationConstraint {
	@JsonProperty("vnfProfileId")
	private String vnfProfileId = null;

	@JsonProperty("locationConstraints")
	private LocationConstraints locationConstraints = null;

	public VnfLocationConstraint vnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	/**
	 * Identifier of (reference to) a vnfProfile to which the additional parameters
	 * apply.
	 * 
	 * @return vnfProfileId
	 **/
	@ApiModelProperty(required = true, value = "Identifier of (reference to) a vnfProfile to which the additional parameters apply. ")
	@NotNull

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	public VnfLocationConstraint locationConstraints(final LocationConstraints locationConstraints) {
		this.locationConstraints = locationConstraints;
		return this;
	}

	/**
	 * Defines the location constraints for the VNF instance to be created based on
	 * the VNF profile.
	 * 
	 * @return locationConstraints
	 **/
	@ApiModelProperty(value = "Defines the location constraints for the VNF instance to be created based on the VNF profile. ")

	@Valid

	public LocationConstraints getLocationConstraints() {
		return locationConstraints;
	}

	public void setLocationConstraints(final LocationConstraints locationConstraints) {
		this.locationConstraints = locationConstraints;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfLocationConstraint vnfLocationConstraint = (VnfLocationConstraint) o;
		return Objects.equals(this.vnfProfileId, vnfLocationConstraint.vnfProfileId) &&
				Objects.equals(this.locationConstraints, vnfLocationConstraint.locationConstraints);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfProfileId, locationConstraints);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfLocationConstraint {\n");

		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
		sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
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
