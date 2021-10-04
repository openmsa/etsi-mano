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

package com.ubiqube.etsi.mano.nfvo.v261.model.vnf;

import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * \&quot;The payload body contains the address information based on which the NFVO can obtain the content of the VNF package\&quot;
 */
@Schema(description = "\"The payload body contains the address information based on which the NFVO can obtain the content of the VNF package\" ")
@Validated
@Setter
@Getter
public class UploadVnfPkgFromUriRequest {
	private String addressInformation;

	private String userName;

	private String password;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class UploadVnfPkgFromUriRequest {\n");

		sb.append("    addressInformation: ").append(toIndentedString(addressInformation)).append("\n");
		sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
		sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
