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
package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present.
 */
@Schema(description = "This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")
@Validated

public class NsdInfoModifications implements OneOfNsdInfoModifications {
	@JsonProperty("nsdOperationalState")
	private NsdOperationalStateType nsdOperationalState = null;

	@JsonProperty("userDefinedData")
	@Valid
	private Map<String, String> userDefinedData = null;

	public NsdInfoModifications nsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
		return this;
	}

	/**
	 * Get nsdOperationalState
	 *
	 * @return nsdOperationalState
	 **/
	@Schema(description = "")

	@Valid
	public NsdOperationalStateType getNsdOperationalState() {
		return nsdOperationalState;
	}

	public void setNsdOperationalState(final NsdOperationalStateType nsdOperationalState) {
		this.nsdOperationalState = nsdOperationalState;
	}

	public NsdInfoModifications userDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
		return this;
	}

	/**
	 * Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge Patch (see IETF RFC 7396). NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present.
	 *
	 * @return userDefinedData
	 **/
	@Schema(description = "Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge Patch (see IETF RFC 7396). NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")
	@Valid
	public Map<String, String> getUserDefinedData() {
		return userDefinedData;
	}

	public void setUserDefinedData(final Map<String, String> userDefinedData) {
		this.userDefinedData = userDefinedData;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final NsdInfoModifications nsdInfoModifications = (NsdInfoModifications) o;
		return Objects.equals(this.nsdOperationalState, nsdInfoModifications.nsdOperationalState) &&
				Objects.equals(this.userDefinedData, nsdInfoModifications.userDefinedData);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nsdOperationalState, userDefinedData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsdInfoModifications {\n");

		sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
		sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
