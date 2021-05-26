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

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * An IP address range to be used, e.g. in case of egress connections. In case
 * this attribute is present, IP addresses from the range will be used.
 */
@ApiModel(description = "An IP address range to be used, e.g. in case of egress connections. In case this attribute is present, IP addresses from the range will be used. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-11T18:14:17.202+01:00")

public class IpOverEthernetAddressDataAddressRange {
	@JsonProperty("minAddress")
	private String minAddress = null;

	@JsonProperty("maxAddress")
	private String maxAddress = null;

	public IpOverEthernetAddressDataAddressRange minAddress(final String minAddress) {
		this.minAddress = minAddress;
		return this;
	}

	/**
	 * Lowest IP address belonging to the range.
	 *
	 * @return minAddress
	 **/
	@ApiModelProperty(required = true, value = "Lowest IP address belonging to the range. ")
	@NotNull

	public String getMinAddress() {
		return minAddress;
	}

	public void setMinAddress(final String minAddress) {
		this.minAddress = minAddress;
	}

	public IpOverEthernetAddressDataAddressRange maxAddress(final String maxAddress) {
		this.maxAddress = maxAddress;
		return this;
	}

	/**
	 * Highest IP address belonging to the range.
	 *
	 * @return maxAddress
	 **/
	@ApiModelProperty(required = true, value = "Highest IP address belonging to the range. ")
	@NotNull

	public String getMaxAddress() {
		return maxAddress;
	}

	public void setMaxAddress(final String maxAddress) {
		this.maxAddress = maxAddress;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final IpOverEthernetAddressDataAddressRange ipOverEthernetAddressDataAddressRange = (IpOverEthernetAddressDataAddressRange) o;
		return Objects.equals(this.minAddress, ipOverEthernetAddressDataAddressRange.minAddress) &&
				Objects.equals(this.maxAddress, ipOverEthernetAddressDataAddressRange.maxAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(minAddress, maxAddress);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class IpOverEthernetAddressDataAddressRange {\n");

		sb.append("    minAddress: ").append(toIndentedString(minAddress)).append("\n");
		sb.append("    maxAddress: ").append(toIndentedString(maxAddress)).append("\n");
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
