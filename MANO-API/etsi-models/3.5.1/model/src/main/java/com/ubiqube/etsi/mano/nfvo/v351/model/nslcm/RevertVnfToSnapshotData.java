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
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type specifies the identifier of an existing VNF instance of the NS
 * instance to be reverted and the identifier of an existing VNF Snapshot to be
 * reverted to. It shall comply with the provisions defined in Table 6.5.3.75-1.
 */
@Schema(description = "This type specifies the identifier of an existing VNF instance of the NS instance to be reverted and the identifier of an existing VNF Snapshot to be reverted to. It shall comply with the provisions defined in Table 6.5.3.75-1. ")
@Validated

public class RevertVnfToSnapshotData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("vnfSnapshotInfoId")
	private String vnfSnapshotInfoId = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	public RevertVnfToSnapshotData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public RevertVnfToSnapshotData vnfSnapshotInfoId(final String vnfSnapshotInfoId) {
		this.vnfSnapshotInfoId = vnfSnapshotInfoId;
		return this;
	}

	/**
	 * Get vnfSnapshotInfoId
	 *
	 * @return vnfSnapshotInfoId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfSnapshotInfoId() {
		return vnfSnapshotInfoId;
	}

	public void setVnfSnapshotInfoId(final String vnfSnapshotInfoId) {
		this.vnfSnapshotInfoId = vnfSnapshotInfoId;
	}

	public RevertVnfToSnapshotData additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final RevertVnfToSnapshotData revertVnfToSnapshotData = (RevertVnfToSnapshotData) o;
		return Objects.equals(this.vnfInstanceId, revertVnfToSnapshotData.vnfInstanceId) &&
				Objects.equals(this.vnfSnapshotInfoId, revertVnfToSnapshotData.vnfSnapshotInfoId) &&
				Objects.equals(this.additionalParams, revertVnfToSnapshotData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, vnfSnapshotInfoId, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class RevertVnfToSnapshotData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    vnfSnapshotInfoId: ").append(toIndentedString(vnfSnapshotInfoId)).append("\n");
		sb.append("    additionalParams: ").append(toIndentedString(additionalParams)).append("\n");
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
