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
package com.ubiqube.etsi.mano.vnfm.v261.model.vnfconfig;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * Network address that has been configured on the CP. See NOTE 1.
 */
@Schema(description = "Network address that has been configured on the CP. See NOTE 1. ")
@Validated
public class CpAddressAddress {
	@JsonProperty("macAddress")
	private String macAddress = null;

	@JsonProperty("ipAddress")
	private String ipAddress = null;

	public CpAddressAddress macAddress(final String macAddress) {
		this.macAddress = macAddress;
		return this;
	}

	/**
	 * Mac address. See NOTE 2.
	 *
	 * @return macAddress
	 **/
	@Schema(description = "Mac address. See NOTE 2. ")

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(final String macAddress) {
		this.macAddress = macAddress;
	}

	public CpAddressAddress ipAddress(final String ipAddress) {
		this.ipAddress = ipAddress;
		return this;
	}

	/**
	 * IP address. See NOTE 2.
	 *
	 * @return ipAddress
	 **/
	@Schema(description = "IP address. See NOTE 2. ")

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(final String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final CpAddressAddress cpAddressAddress = (CpAddressAddress) o;
		return Objects.equals(this.macAddress, cpAddressAddress.macAddress) &&
				Objects.equals(this.ipAddress, cpAddressAddress.ipAddress);
	}

	@Override
	public int hashCode() {
		return Objects.hash(macAddress, ipAddress);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class CpAddressAddress {\n");

		sb.append("    macAddress: ").append(toIndentedString(macAddress)).append("\n");
		sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
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
