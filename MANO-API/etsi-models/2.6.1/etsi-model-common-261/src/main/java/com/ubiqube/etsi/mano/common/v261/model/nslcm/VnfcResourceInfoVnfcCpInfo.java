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

package com.ubiqube.etsi.mano.common.v261.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * VnfcResourceInfoVnfcCpInfo
 */
@Validated
public class VnfcResourceInfoVnfcCpInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("vnfExtCpId")
	private String vnfExtCpId = null;

	@JsonProperty("cpProtocolInfo")
	@Valid
	private List<CpProtocolInfo> cpProtocolInfo = null;

	@JsonProperty("vnfLinkPortId")
	private String vnfLinkPortId = null;

	@JsonProperty("metadata")
	private Map<String, String> metadata = null;

	public VnfcResourceInfoVnfcCpInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of this VNFC CP instance and the associated array entry.
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of this VNFC CP instance and the associated array entry. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfcResourceInfoVnfcCpInfo cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Identifier of the VDU CPD, cpdId, in the VNFD.
	 *
	 * @return cpdId
	 **/
	@Schema(required = true, description = "Identifier of the VDU CPD, cpdId, in the VNFD. ")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public VnfcResourceInfoVnfcCpInfo vnfExtCpId(final String vnfExtCpId) {
		this.vnfExtCpId = vnfExtCpId;
		return this;
	}

	/**
	 * When the VNFC CP is exposed as external CP of the VNF, the identifier of this external VNF CP.
	 *
	 * @return vnfExtCpId
	 **/
	@Schema(description = "When the VNFC CP is exposed as external CP of the VNF, the identifier of this external VNF CP. ")

	public String getVnfExtCpId() {
		return vnfExtCpId;
	}

	public void setVnfExtCpId(final String vnfExtCpId) {
		this.vnfExtCpId = vnfExtCpId;
	}

	public VnfcResourceInfoVnfcCpInfo cpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
		return this;
	}

	public VnfcResourceInfoVnfcCpInfo addCpProtocolInfoItem(final CpProtocolInfo cpProtocolInfoItem) {
		if (this.cpProtocolInfo == null) {
			this.cpProtocolInfo = new ArrayList<>();
		}
		this.cpProtocolInfo.add(cpProtocolInfoItem);
		return this;
	}

	/**
	 * Network protocol information for this CP.
	 *
	 * @return cpProtocolInfo
	 **/
	@Schema(description = "Network protocol information for this CP. ")

	@Valid

	public List<CpProtocolInfo> getCpProtocolInfo() {
		return cpProtocolInfo;
	}

	public void setCpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
	}

	public VnfcResourceInfoVnfcCpInfo vnfLinkPortId(final String vnfLinkPortId) {
		this.vnfLinkPortId = vnfLinkPortId;
		return this;
	}

	/**
	 * Identifier of the \"vnfLinkPorts\" structure in the \"VnfVirtualLinkResourceInfo\" structure. Shall be present if the CP is associated to a link port.
	 *
	 * @return vnfLinkPortId
	 **/
	@Schema(description = "Identifier of the \"vnfLinkPorts\" structure in the \"VnfVirtualLinkResourceInfo\" structure. Shall be present if the CP is associated to a link port. ")

	public String getVnfLinkPortId() {
		return vnfLinkPortId;
	}

	public void setVnfLinkPortId(final String vnfLinkPortId) {
		this.vnfLinkPortId = vnfLinkPortId;
	}

	public VnfcResourceInfoVnfcCpInfo metadata(final Map<String, String> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Metadata about this CP.
	 *
	 * @return metadata
	 **/
	@Schema(description = "Metadata about this CP. ")

	@Valid

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfcResourceInfoVnfcCpInfo vnfcResourceInfoVnfcCpInfo = (VnfcResourceInfoVnfcCpInfo) o;
		return Objects.equals(this.id, vnfcResourceInfoVnfcCpInfo.id) &&
				Objects.equals(this.cpdId, vnfcResourceInfoVnfcCpInfo.cpdId) &&
				Objects.equals(this.vnfExtCpId, vnfcResourceInfoVnfcCpInfo.vnfExtCpId) &&
				Objects.equals(this.cpProtocolInfo, vnfcResourceInfoVnfcCpInfo.cpProtocolInfo) &&
				Objects.equals(this.vnfLinkPortId, vnfcResourceInfoVnfcCpInfo.vnfLinkPortId) &&
				Objects.equals(this.metadata, vnfcResourceInfoVnfcCpInfo.metadata);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpdId, vnfExtCpId, cpProtocolInfo, vnfLinkPortId, metadata);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfcResourceInfoVnfcCpInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    vnfExtCpId: ").append(toIndentedString(vnfExtCpId)).append("\n");
		sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
		sb.append("    vnfLinkPortId: ").append(toIndentedString(vnfLinkPortId)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
