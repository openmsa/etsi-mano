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

package com.ubiqube.etsi.mano.model.v271.sol005.nslcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.em.v271.model.vnflcm.CpProtocolInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents an SAP instance. It shall comply with the provisions
 * defined in Table 6.5.3.67-1.
 */
@ApiModel(description = "This type represents an SAP instance. It shall comply with the provisions defined in Table 6.5.3.67-1. ")
@Validated
public class SapInfo {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("sapdId")
	private String sapdId = null;

	@JsonProperty("sapName")
	private String sapName = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("sapProtocolInfo")
	@Valid
	private List<CpProtocolInfo> sapProtocolInfo = new ArrayList<>();

	public SapInfo id(final String id) {
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

	public SapInfo sapdId(final String sapdId) {
		this.sapdId = sapdId;
		return this;
	}

	/**
	 * Get sapdId
	 *
	 * @return sapdId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getSapdId() {
		return sapdId;
	}

	public void setSapdId(final String sapdId) {
		this.sapdId = sapdId;
	}

	public SapInfo sapName(final String sapName) {
		this.sapName = sapName;
		return this;
	}

	/**
	 * Human readable name for the SAP instance.
	 *
	 * @return sapName
	 **/
	@ApiModelProperty(required = true, value = "Human readable name for the SAP instance. ")
	@NotNull

	public String getSapName() {
		return sapName;
	}

	public void setSapName(final String sapName) {
		this.sapName = sapName;
	}

	public SapInfo description(final String description) {
		this.description = description;
		return this;
	}

	/**
	 * Human readable description for the SAP instance.
	 *
	 * @return description
	 **/
	@ApiModelProperty(value = "Human readable description for the SAP instance. ")

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public SapInfo sapProtocolInfo(final List<CpProtocolInfo> sapProtocolInfo) {
		this.sapProtocolInfo = sapProtocolInfo;
		return this;
	}

	public SapInfo addSapProtocolInfoItem(final CpProtocolInfo sapProtocolInfoItem) {
		this.sapProtocolInfo.add(sapProtocolInfoItem);
		return this;
	}

	/**
	 * Network protocol information for this SAP.
	 *
	 * @return sapProtocolInfo
	 **/
	@ApiModelProperty(required = true, value = "Network protocol information for this SAP. ")
	@NotNull
	@Valid
	public List<CpProtocolInfo> getSapProtocolInfo() {
		return sapProtocolInfo;
	}

	public void setSapProtocolInfo(final List<CpProtocolInfo> sapProtocolInfo) {
		this.sapProtocolInfo = sapProtocolInfo;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final SapInfo sapInfo = (SapInfo) o;
		return Objects.equals(this.id, sapInfo.id) &&
				Objects.equals(this.sapdId, sapInfo.sapdId) &&
				Objects.equals(this.sapName, sapInfo.sapName) &&
				Objects.equals(this.description, sapInfo.description) &&
				Objects.equals(this.sapProtocolInfo, sapInfo.sapProtocolInfo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, sapdId, sapName, description, sapProtocolInfo);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class SapInfo {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    sapdId: ").append(toIndentedString(sapdId)).append("\n");
		sb.append("    sapName: ").append(toIndentedString(sapName)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("    sapProtocolInfo: ").append(toIndentedString(sapProtocolInfo)).append("\n");
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
