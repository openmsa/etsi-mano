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

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type specifies an existing VNF instance to be used in the NS instance
 * and if needed, the VNF Profile to use for this VNF instance. It shall comply
 * with the provisions defined in Table 6.5.3.19-1.
 */
@Schema(description = "This type specifies an existing VNF instance to be used in the NS instance and if needed, the VNF Profile to use for this VNF instance. It shall comply with the provisions defined in Table 6.5.3.19-1. ")
@Validated


public class VnfInstanceData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfProfileId")
	private String vnfProfileId = null;

	public VnfInstanceData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Identifier of the existing VNF instance to be used in the NS.
	 * 
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "Identifier of the existing VNF instance to be used in the NS. ")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public VnfInstanceData vnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
		return this;
	}

	/**
	 * Identifier of (Reference to) a vnfProfile defined in the NSD which the
	 * existing VNF instance shall be matched with. If not present, the NFVO will
	 * select the VnfProfile matching the information in the VNF instance.
	 * 
	 * @return vnfProfileId
	 **/
	@Schema(required = true, description = "Identifier of (Reference to) a vnfProfile defined in the NSD which the existing VNF instance shall be matched with. If not present, the NFVO will select the VnfProfile matching the information in the VNF instance. ")
	@NotNull

	public String getVnfProfileId() {
		return vnfProfileId;
	}

	public void setVnfProfileId(final String vnfProfileId) {
		this.vnfProfileId = vnfProfileId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfInstanceData vnfInstanceData = (VnfInstanceData) o;
		return Objects.equals(this.vnfInstanceId, vnfInstanceData.vnfInstanceId) &&
				Objects.equals(this.vnfProfileId, vnfInstanceData.vnfProfileId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfProfileId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfInstanceData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfProfileId: ").append(toIndentedString(vnfProfileId)).append("\n");
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
