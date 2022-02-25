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
package com.ubiqube.etsi.mano.em.v271.model.vnflcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

/**
 * TerminateVnfRequest
 */
@Validated

public class TerminateVnfRequest {
	/**
	 * Indicates the type of termination is requested. Permitted values: * FORCEFUL:
	 * The VNFM will shut down the VNF and release the resources immediately after
	 * accepting the request.
	 */
	public enum TerminationTypeEnum {
		FORCEFUL("FORCEFUL"),
		GRACEFUL("GRACEFUL");

		private final String value;

		TerminationTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static TerminationTypeEnum fromValue(final String text) {
			for (final TerminationTypeEnum b : TerminationTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("terminationType")
	private TerminationTypeEnum terminationType = null;

	@JsonProperty("gracefulTerminationTimeout")
	private Integer gracefulTerminationTimeout = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	public TerminateVnfRequest terminationType(final TerminationTypeEnum terminationType) {
		this.terminationType = terminationType;
		return this;
	}

	/**
	 * Indicates the type of termination is requested. Permitted values: * FORCEFUL:
	 * The VNFM will shut down the VNF and release the resources immediately after
	 * accepting the request.
	 *
	 * @return terminationType
	 **/
	@ApiModelProperty(required = true, value = "Indicates the type of termination is requested. Permitted values: * FORCEFUL: The VNFM will shut down the VNF and release the   resources immediately after accepting the request. ")
	@NotNull

	public TerminationTypeEnum getTerminationType() {
		return terminationType;
	}

	public void setTerminationType(final TerminationTypeEnum terminationType) {
		this.terminationType = terminationType;
	}

	public TerminateVnfRequest additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Additional parameters passed by the NFVO as input to the termination process,
	 * specific to the VNF being terminated, as declared in the VNFD as part of
	 * \"TerminateVnfOpConfig\".
	 *
	 * @return additionalParams
	 **/
	@ApiModelProperty(value = "Additional parameters passed by the NFVO as input to the termination process, specific to the VNF being terminated, as declared in the VNFD as part of \"TerminateVnfOpConfig\". ")

	@Valid

	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	public Integer getGracefulTerminationTimeout() {
		return gracefulTerminationTimeout;
	}

	public void setGracefulTerminationTimeout(final Integer gracefulTerminationTimeout) {
		this.gracefulTerminationTimeout = gracefulTerminationTimeout;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final TerminateVnfRequest terminateVnfRequest = (TerminateVnfRequest) o;
		return Objects.equals(this.terminationType, terminateVnfRequest.terminationType) &&
				Objects.equals(this.additionalParams, terminateVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(terminationType, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class TerminateVnfRequest {\n");

		sb.append("    terminationType: ").append(toIndentedString(terminationType)).append("\n");
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
