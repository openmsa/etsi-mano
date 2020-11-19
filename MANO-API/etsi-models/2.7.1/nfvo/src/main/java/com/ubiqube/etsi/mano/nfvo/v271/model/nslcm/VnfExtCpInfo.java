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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents information about an external CP of a VNF. It shall
 * comply with the provisions defined in Table 6.5.3.70-1.
 */
@ApiModel(description = "This type represents information about an external CP of a VNF. It shall comply  with the provisions defined in Table 6.5.3.70-1. ")
@Validated
public class VnfExtCpInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpProtocolInfo")
	@Valid
	private List<CpProtocolInfo> cpProtocolInfo = null;

	@JsonProperty("extLinkPortId")
	private CpProtocolInfo extLinkPortId = null;

	@JsonProperty("metadata")
	private Map<String, Object> metadata = null;

	@JsonProperty("associatedVnfcCpId")
	private String associatedVnfcCpId = null;

	@JsonProperty("associatedVnfVirtualLinkId")
	private String associatedVnfVirtualLinkId = null;

	public VnfExtCpInfo id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public VnfExtCpInfo cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Get cpdId
	 *
	 * @return cpdId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public VnfExtCpInfo cpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
		return this;
	}

	public VnfExtCpInfo addCpProtocolInfoItem(final CpProtocolInfo cpProtocolInfoItem) {
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
	@ApiModelProperty(value = "Network protocol information for this CP. ")
	@Valid
	@Size(min = 1)
	public List<CpProtocolInfo> getCpProtocolInfo() {
		return cpProtocolInfo;
	}

	public void setCpProtocolInfo(final List<CpProtocolInfo> cpProtocolInfo) {
		this.cpProtocolInfo = cpProtocolInfo;
	}

	public VnfExtCpInfo extLinkPortId(final CpProtocolInfo extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
		return this;
	}

	/**
	 * Get extLinkPortId
	 *
	 * @return extLinkPortId
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public CpProtocolInfo getExtLinkPortId() {
		return extLinkPortId;
	}

	public void setExtLinkPortId(final CpProtocolInfo extLinkPortId) {
		this.extLinkPortId = extLinkPortId;
	}

	public VnfExtCpInfo metadata(final Map<String, Object> metadata) {
		this.metadata = metadata;
		return this;
	}

	/**
	 * Get metadata
	 *
	 * @return metadata
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, Object> metadata) {
		this.metadata = metadata;
	}

	public VnfExtCpInfo associatedVnfcCpId(final String associatedVnfcCpId) {
		this.associatedVnfcCpId = associatedVnfcCpId;
		return this;
	}

	/**
	 * Get associatedVnfcCpId
	 *
	 * @return associatedVnfcCpId
	 **/
	@ApiModelProperty(value = "")

	public String getAssociatedVnfcCpId() {
		return associatedVnfcCpId;
	}

	public void setAssociatedVnfcCpId(final String associatedVnfcCpId) {
		this.associatedVnfcCpId = associatedVnfcCpId;
	}

	public VnfExtCpInfo associatedVnfVirtualLinkId(final String associatedVnfVirtualLinkId) {
		this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
		return this;
	}

	/**
	 * Get associatedVnfVirtualLinkId
	 *
	 * @return associatedVnfVirtualLinkId
	 **/
	@ApiModelProperty(value = "")

	public String getAssociatedVnfVirtualLinkId() {
		return associatedVnfVirtualLinkId;
	}

	public void setAssociatedVnfVirtualLinkId(final String associatedVnfVirtualLinkId) {
		this.associatedVnfVirtualLinkId = associatedVnfVirtualLinkId;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfExtCpInfo vnfExtCpInfo = (VnfExtCpInfo) o;
		return Objects.equals(this.id, vnfExtCpInfo.id) &&
				Objects.equals(this.cpdId, vnfExtCpInfo.cpdId) &&
				Objects.equals(this.cpProtocolInfo, vnfExtCpInfo.cpProtocolInfo) &&
				Objects.equals(this.extLinkPortId, vnfExtCpInfo.extLinkPortId) &&
				Objects.equals(this.metadata, vnfExtCpInfo.metadata) &&
				Objects.equals(this.associatedVnfcCpId, vnfExtCpInfo.associatedVnfcCpId) &&
				Objects.equals(this.associatedVnfVirtualLinkId, vnfExtCpInfo.associatedVnfVirtualLinkId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, cpdId, cpProtocolInfo, extLinkPortId, metadata, associatedVnfcCpId, associatedVnfVirtualLinkId);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfExtCpInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpProtocolInfo: ").append(toIndentedString(cpProtocolInfo)).append("\n");
		sb.append("    extLinkPortId: ").append(toIndentedString(extLinkPortId)).append("\n");
		sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
		sb.append("    associatedVnfcCpId: ").append(toIndentedString(associatedVnfcCpId)).append("\n");
		sb.append("    associatedVnfVirtualLinkId: ").append(toIndentedString(associatedVnfVirtualLinkId)).append("\n");
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