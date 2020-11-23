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

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * LocationConstraintsCivicAddressElement
 */
@Validated


public class LocationConstraintsCivicAddressElement {
	@JsonProperty("caType")
	private Integer caType = null;

	@JsonProperty("caValue")
	private String caValue = null;

	public LocationConstraintsCivicAddressElement caType(final Integer caType) {
		this.caType = caType;
		return this;
	}

	/**
	 * Describe the content type of caValue. The value of caType shall comply with
	 * Section 3.4 of IETF RFC 4776 [13].
	 * 
	 * @return caType
	 **/
	@ApiModelProperty(required = true, value = "Describe the content type of caValue. The value of caType shall comply with Section 3.4 of IETF RFC 4776 [13]. ")
	@NotNull

	public Integer getCaType() {
		return caType;
	}

	public void setCaType(final Integer caType) {
		this.caType = caType;
	}

	public LocationConstraintsCivicAddressElement caValue(final String caValue) {
		this.caValue = caValue;
		return this;
	}

	/**
	 * Content of civic address element corresponding to the caType. The format
	 * caValue shall comply with Section 3.4 of IETF RFC 4776 [13].
	 * 
	 * @return caValue
	 **/
	@ApiModelProperty(required = true, value = "Content of civic address element corresponding to the caType. The format caValue shall comply with Section 3.4 of IETF RFC 4776 [13]. ")
	@NotNull

	public String getCaValue() {
		return caValue;
	}

	public void setCaValue(final String caValue) {
		this.caValue = caValue;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final LocationConstraintsCivicAddressElement locationConstraintsCivicAddressElement = (LocationConstraintsCivicAddressElement) o;
		return Objects.equals(this.caType, locationConstraintsCivicAddressElement.caType) &&
				Objects.equals(this.caValue, locationConstraintsCivicAddressElement.caValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(caType, caValue);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class LocationConstraintsCivicAddressElement {\n");

		sb.append("    caType: ").append(toIndentedString(caType)).append("\n");
		sb.append("    caValue: ").append(toIndentedString(caValue)).append("\n");
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
