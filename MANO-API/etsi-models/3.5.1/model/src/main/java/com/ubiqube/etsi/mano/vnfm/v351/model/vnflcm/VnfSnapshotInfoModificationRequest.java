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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnflcm;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.VnfSnapshot;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents attribute modifications for an \&quot;Individual VNF
 * snapshot\&quot; resource, i.e. modifications to a resource representation
 * based on the \&quot;VnfSnapshotInfo\&quot; data type. The attributes of
 * \&quot;VnfSnapshotInfo\&quot; that can be modified according to the
 * provisions in clause 5.5.2.22 are included in the
 * \&quot;VnfSnapshotInfoModificationRequest\&quot; data type. The
 * \&quot;VnfSnapshotInfoModificationRequest\&quot; data type shall comply with
 * the provisions defined in table 5.5.2.24-1.
 */
@Schema(description = "This type represents attribute modifications for an \"Individual VNF snapshot\" resource, i.e. modifications  to a resource representation based on the \"VnfSnapshotInfo\" data type. The attributes of \"VnfSnapshotInfo\"  that can be modified according to the provisions in clause 5.5.2.22 are included in the  \"VnfSnapshotInfoModificationRequest\" data type. The \"VnfSnapshotInfoModificationRequest\" data type shall  comply with the provisions defined in table 5.5.2.24-1. ")
@Validated

public class VnfSnapshotInfoModificationRequest {
	@JsonProperty("vnfSnapshotPkgId")
	private String vnfSnapshotPkgId = null;

	@JsonProperty("vnfSnapshot")
	private VnfSnapshot vnfSnapshot = null;

	public VnfSnapshotInfoModificationRequest vnfSnapshotPkgId(final String vnfSnapshotPkgId) {
		this.vnfSnapshotPkgId = vnfSnapshotPkgId;
		return this;
	}

	/**
	 * Get vnfSnapshotPkgId
	 *
	 * @return vnfSnapshotPkgId
	 **/
	@Schema(description = "")

	public String getVnfSnapshotPkgId() {
		return vnfSnapshotPkgId;
	}

	public void setVnfSnapshotPkgId(final String vnfSnapshotPkgId) {
		this.vnfSnapshotPkgId = vnfSnapshotPkgId;
	}

	public VnfSnapshotInfoModificationRequest vnfSnapshot(final VnfSnapshot vnfSnapshot) {
		this.vnfSnapshot = vnfSnapshot;
		return this;
	}

	/**
	 * Get vnfSnapshot
	 *
	 * @return vnfSnapshot
	 **/
	@Schema(description = "")

	@Valid
	public VnfSnapshot getVnfSnapshot() {
		return vnfSnapshot;
	}

	public void setVnfSnapshot(final VnfSnapshot vnfSnapshot) {
		this.vnfSnapshot = vnfSnapshot;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final VnfSnapshotInfoModificationRequest vnfSnapshotInfoModificationRequest = (VnfSnapshotInfoModificationRequest) o;
		return Objects.equals(this.vnfSnapshotPkgId, vnfSnapshotInfoModificationRequest.vnfSnapshotPkgId) &&
				Objects.equals(this.vnfSnapshot, vnfSnapshotInfoModificationRequest.vnfSnapshot);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfSnapshotPkgId, vnfSnapshot);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfSnapshotInfoModificationRequest {\n");

		sb.append("    vnfSnapshotPkgId: ").append(toIndentedString(vnfSnapshotPkgId)).append("\n");
		sb.append("    vnfSnapshot: ").append(toIndentedString(vnfSnapshot)).append("\n");
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
