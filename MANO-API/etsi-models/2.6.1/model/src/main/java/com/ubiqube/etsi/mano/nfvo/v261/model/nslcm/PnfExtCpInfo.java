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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.CpProtocolData;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents the information about the external CP of the PNF. It
 * shall comply with the provisions defined in Table 6.5.3.17-1.
 */
@Schema(description = "This type represents the information about the external CP of the PNF.  It shall comply with the provisions defined in Table 6.5.3.17-1. ")
@Validated


public class PnfExtCpInfo {
	@JsonProperty("cpInstanceId")
	private String cpInstanceId = null;

	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpProtocolData")
	@Valid
	private List<CpProtocolData> cpProtocolData = null;

	public PnfExtCpInfo cpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
		return this;
	}

	/**
	 * Identifier of the CP in the scope of the PNF.
	 *
	 * @return cpInstanceId
	 **/
	@Schema(required = true, description = "Identifier of the CP in the scope of the PNF. ")
	@NotNull

	public String getCpInstanceId() {
		return cpInstanceId;
	}

	public void setCpInstanceId(final String cpInstanceId) {
		this.cpInstanceId = cpInstanceId;
	}

	public PnfExtCpInfo cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * Identifier of (reference to) the Connection Point Descriptor (CPD) for this
	 * CP.
	 *
	 * @return cpdId
	 **/
	@Schema(required = true, description = "Identifier of (reference to) the Connection Point Descriptor (CPD) for this CP. ")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public PnfExtCpInfo cpProtocolData(final List<CpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
		return this;
	}

	public PnfExtCpInfo addCpProtocolDataItem(final CpProtocolData cpProtocolDataItem) {
		if (this.cpProtocolData == null) {
			this.cpProtocolData = new ArrayList<>();
		}
		this.cpProtocolData.add(cpProtocolDataItem);
		return this;
	}

	/**
	 * Parameters for configuring the network protocols on the CP.
	 *
	 * @return cpProtocolData
	 **/
	@Schema(description = "Parameters for configuring the network protocols on the CP. ")

	@Valid

	public List<CpProtocolData> getCpProtocolData() {
		return cpProtocolData;
	}

	public void setCpProtocolData(final List<CpProtocolData> cpProtocolData) {
		this.cpProtocolData = cpProtocolData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PnfExtCpInfo pnfExtCpInfo = (PnfExtCpInfo) o;
		return Objects.equals(this.cpInstanceId, pnfExtCpInfo.cpInstanceId) &&
				Objects.equals(this.cpdId, pnfExtCpInfo.cpdId) &&
				Objects.equals(this.cpProtocolData, pnfExtCpInfo.cpProtocolData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpInstanceId, cpdId, cpProtocolData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PnfExtCpInfo {\n");

		sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
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
