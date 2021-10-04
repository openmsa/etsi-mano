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

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This operation supports the healing of an NS instance, either by healing the
 * complete NS instance or by healing one of more of the VNF instances that are
 * part of this NS. It shall comply with the provisions defined in Table
 * 6.5.2.13-1.
 */
@Schema(description = "This operation supports the healing of an NS instance,  either by healing the complete NS instance or by healing one of more of the VNF instances that are part of this NS.  It shall comply with the provisions defined in Table 6.5.2.13-1. ")
@Validated


public class HealNsRequest {
	@JsonProperty("healNsData")
	private HealNsData healNsData = null;

	@JsonProperty("healVnfData")
	@Valid
	private List<HealVnfData> healVnfData = null;

	public HealNsRequest healNsData(final HealNsData healNsData) {
		this.healNsData = healNsData;
		return this;
	}

	/**
	 * Indicates the reason why a healing procedure is required.
	 * 
	 * @return healNsData
	 **/
	@Schema(description = "Indicates the reason why a healing procedure is required. ")

	@Valid

	public HealNsData getHealNsData() {
		return healNsData;
	}

	public void setHealNsData(final HealNsData healNsData) {
		this.healNsData = healNsData;
	}

	public HealNsRequest healVnfData(final List<HealVnfData> healVnfData) {
		this.healVnfData = healVnfData;
		return this;
	}

	public HealNsRequest addHealVnfDataItem(final HealVnfData healVnfDataItem) {
		if (this.healVnfData == null) {
			this.healVnfData = new ArrayList<>();
		}
		this.healVnfData.add(healVnfDataItem);
		return this;
	}

	/**
	 * Additional parameters passed by the NFVO as input to the healing process,
	 * specific to the VNF being healed, as declared in the VNFD as part of
	 * \"HealVnfOpConfig\".
	 * 
	 * @return healVnfData
	 **/
	@Schema(description = "Additional parameters passed by the NFVO as input to the healing process, specific to the VNF being healed, as declared in the VNFD as part of \"HealVnfOpConfig\".         ")

	@Valid

	public List<HealVnfData> getHealVnfData() {
		return healVnfData;
	}

	public void setHealVnfData(final List<HealVnfData> healVnfData) {
		this.healVnfData = healVnfData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final HealNsRequest healNsRequest = (HealNsRequest) o;
		return Objects.equals(this.healNsData, healNsRequest.healNsData) &&
				Objects.equals(this.healVnfData, healNsRequest.healVnfData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(healNsData, healVnfData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class HealNsRequest {\n");

		sb.append("    healNsData: ").append(toIndentedString(healNsData)).append("\n");
		sb.append("    healVnfData: ").append(toIndentedString(healVnfData)).append("\n");
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
