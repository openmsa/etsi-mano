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

import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents the information to heal a VNF that is part of an NS. The
 * NFVO shall then invoke the HealVNF operation towards the appropriate VNFM. It
 * shall comply with the provisions defined in Table 6.5.3.44-1.
 */
@ApiModel(description = "This type represents the information to heal a VNF that is part of an NS.  The NFVO shall then invoke the HealVNF operation towards the appropriate VNFM.  It shall comply with the provisions defined in Table 6.5.3.44-1. ")
@Validated
public class HealVnfData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	@JsonProperty("cause")
	private String cause = null;

	@JsonProperty("additionalParams")
	private Map<String, Object> additionalParams = null;

	public HealVnfData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public HealVnfData cause(final String cause) {
		this.cause = cause;
		return this;
	}

	/**
	 * Indicates the reason why a healing procedure is required.
	 *
	 * @return cause
	 **/
	@ApiModelProperty(value = "Indicates the reason why a healing procedure is required. ")

	public String getCause() {
		return cause;
	}

	public void setCause(final String cause) {
		this.cause = cause;
	}

	public HealVnfData additionalParams(final Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public Map<String, Object> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, Object> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final HealVnfData healVnfData = (HealVnfData) o;
		return Objects.equals(this.vnfInstanceId, healVnfData.vnfInstanceId) &&
				Objects.equals(this.cause, healVnfData.cause) &&
				Objects.equals(this.additionalParams, healVnfData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, cause, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class HealVnfData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    cause: ").append(toIndentedString(cause)).append("\n");
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
