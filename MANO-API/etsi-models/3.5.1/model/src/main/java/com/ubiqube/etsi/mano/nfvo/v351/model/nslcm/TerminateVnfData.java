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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This type represents the information to terminate a VNF that is part of an
 * NS. NOTE: If the VNF is still in service, requesting forceful termination can
 * adversely impact network service.
 */
@Schema(description = "This type represents the information to terminate a VNF that is part of an NS. NOTE: If the VNF is still in service, requesting forceful termination can adversely impact network service. ")
@Validated

public class TerminateVnfData {
	@JsonProperty("vnfInstanceId")
	private String vnfInstanceId = null;

	/**
	 * Indicates whether forceful or graceful termination is requested. See note.
	 * Permitted values: - FORCEFUL - GRACEFUL
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

	public TerminateVnfData vnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
		return this;
	}

	/**
	 * Get vnfInstanceId
	 *
	 * @return vnfInstanceId
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getVnfInstanceId() {
		return vnfInstanceId;
	}

	public void setVnfInstanceId(final String vnfInstanceId) {
		this.vnfInstanceId = vnfInstanceId;
	}

	public TerminateVnfData terminationType(final TerminationTypeEnum terminationType) {
		this.terminationType = terminationType;
		return this;
	}

	/**
	 * Indicates whether forceful or graceful termination is requested. See note.
	 * Permitted values: - FORCEFUL - GRACEFUL
	 *
	 * @return terminationType
	 **/
	@Schema(description = "Indicates whether forceful or graceful termination is requested. See note. Permitted values: - FORCEFUL - GRACEFUL ")

	public TerminationTypeEnum getTerminationType() {
		return terminationType;
	}

	public void setTerminationType(final TerminationTypeEnum terminationType) {
		this.terminationType = terminationType;
	}

	public TerminateVnfData gracefulTerminationTimeout(final Integer gracefulTerminationTimeout) {
		this.gracefulTerminationTimeout = gracefulTerminationTimeout;
		return this;
	}

	/**
	 * The attribute is only applicable in case of graceful termination. It defines
	 * the time to wait for the VNF to be taken out of service before shutting down
	 * the VNF and releasing the resources. The unit is seconds.
	 *
	 * @return gracefulTerminationTimeout
	 **/
	@Schema(description = "The attribute is only applicable in case of graceful termination. It defines the time to wait for the VNF to be taken out of service before shutting down the VNF and releasing the resources. The unit is seconds. ")

	public Integer getGracefulTerminationTimeout() {
		return gracefulTerminationTimeout;
	}

	public void setGracefulTerminationTimeout(final Integer gracefulTerminationTimeout) {
		this.gracefulTerminationTimeout = gracefulTerminationTimeout;
	}

	public TerminateVnfData additionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
		return this;
	}

	/**
	 * Get additionalParams
	 *
	 * @return additionalParams
	 **/
	@Schema(description = "")

	@Valid
	public Map<String, String> getAdditionalParams() {
		return additionalParams;
	}

	public void setAdditionalParams(final Map<String, String> additionalParams) {
		this.additionalParams = additionalParams;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final TerminateVnfData terminateVnfData = (TerminateVnfData) o;
		return Objects.equals(this.vnfInstanceId, terminateVnfData.vnfInstanceId) &&
				Objects.equals(this.terminationType, terminateVnfData.terminationType) &&
				Objects.equals(this.gracefulTerminationTimeout, terminateVnfData.gracefulTerminationTimeout) &&
				Objects.equals(this.additionalParams, terminateVnfData.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfInstanceId, terminationType, gracefulTerminationTimeout, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class TerminateVnfData {\n");

		sb.append("    vnfInstanceId: ").append(toIndentedString(vnfInstanceId)).append("\n");
		sb.append("    terminationType: ").append(toIndentedString(terminationType)).append("\n");
		sb.append("    gracefulTerminationTimeout: ").append(toIndentedString(gracefulTerminationTimeout)).append("\n");
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
