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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents configuration information for external CPs created from a CPD.
 */
@Schema(description = "This type represents configuration information for external CPs created from a CPD. ")
@Validated

public class VnfExtCpData {
	@JsonProperty("cpdId")
	private String cpdId = null;

	@JsonProperty("cpConfig")
	@Valid
	private List<VnfExtCpConfig> cpConfig = null;

	public VnfExtCpData cpdId(final String cpdId) {
		this.cpdId = cpdId;
		return this;
	}

	/**
	 * The identifier of the CPD in the VNFD.
	 *
	 * @return cpdId
	 **/
	@Schema(required = true, description = "The identifier of the CPD in the VNFD. ")
	@NotNull

	public String getCpdId() {
		return cpdId;
	}

	public void setCpdId(final String cpdId) {
		this.cpdId = cpdId;
	}

	public VnfExtCpData cpConfig(final List<VnfExtCpConfig> cpConfig) {
		this.cpConfig = cpConfig;
		return this;
	}

	public VnfExtCpData addCpConfigItem(final VnfExtCpConfig cpConfigItem) {
		if (this.cpConfig == null) {
			this.cpConfig = new ArrayList<>();
		}
		this.cpConfig.add(cpConfigItem);
		return this;
	}

	/**
	 * List of instance data that need to be configured on the CP instances created from the respective CPD.
	 *
	 * @return cpConfig
	 **/
	@Schema(description = "List of instance data that need to be configured on the CP instances created from the respective CPD. ")

	@Valid

	public List<VnfExtCpConfig> getCpConfig() {
		return cpConfig;
	}

	public void setCpConfig(final List<VnfExtCpConfig> cpConfig) {
		this.cpConfig = cpConfig;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VnfExtCpData vnfExtCpData = (VnfExtCpData) o;
		return Objects.equals(this.cpdId, vnfExtCpData.cpdId) &&
				Objects.equals(this.cpConfig, vnfExtCpData.cpConfig);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpdId, cpConfig);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VnfExtCpData {\n");

		sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
		sb.append("    cpConfig: ").append(toIndentedString(cpConfig)).append("\n");
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
