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
package com.ubiqube.etsi.mano.em.v351.model.vnflcm;

import java.util.ArrayList;
import java.util.List;
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
 * This type represents request parameters for the \&quot;Operate VNF\&quot;
 * operation. * NOTE: The \&quot;stopType\&quot; and
 * \&quot;gracefulStopTimeout\&quot; attributes shall be absent, when the
 * \&quot;changeStateTo\&quot; attribute is equal to \&quot;STARTED\&quot;. The
 * \&quot;gracefulStopTimeout\&quot; attribute shall be present, when the
 * \&quot;changeStateTo\&quot; is equal to \&quot;STOPPED\&quot; and the
 * \&quot;stopType\&quot; attribute is equal to \&quot;GRACEFUL\&quot;. The
 * \&quot;gracefulStopTimeout\&quot; attribute shall be absent, when the
 * \&quot;changeStateTo\&quot; attribute is equal to \&quot;STOPPED\&quot; and
 * the \&quot;stopType\&quot; attribute is equal to \&quot;FORCEFUL\&quot;. The
 * request shall be treated as if the \&quot;stopType\&quot; attribute has been
 * set to \&quot;FORCEFUL\&quot;, when the \&quot;changeStateTo\&quot; attribute
 * is equal to \&quot;STOPPED\&quot; and the \&quot;stopType\&quot; attribute is
 * absent.
 */
@Schema(description = "This type represents request parameters for the \"Operate VNF\" operation. * NOTE: The \"stopType\" and \"gracefulStopTimeout\" attributes shall be absent, when the \"changeStateTo\"           attribute is equal to \"STARTED\". The \"gracefulStopTimeout\" attribute shall be present, when the           \"changeStateTo\" is equal to \"STOPPED\" and the \"stopType\" attribute is equal to \"GRACEFUL\".           The \"gracefulStopTimeout\" attribute shall be absent, when the \"changeStateTo\" attribute is equal to           \"STOPPED\" and the \"stopType\" attribute is equal to \"FORCEFUL\". The request shall be treated as if           the \"stopType\" attribute has been set to \"FORCEFUL\", when the \"changeStateTo\" attribute is equal           to \"STOPPED\" and the \"stopType\" attribute is absent. ")
@Validated

public class OperateVnfRequest {
	@JsonProperty("vnfcInstanceId")
	@Valid
	private List<String> vnfcInstanceId = null;

	@JsonProperty("changeStateTo")
	private VnfOperationalStateType changeStateTo = null;

	/**
	 * It signals whether forceful or graceful stop is requested. See note
	 */
	public enum StopTypeEnum {
		FORCEFUL("FORCEFUL"),

		GRACEFUL("GRACEFUL");

		private final String value;

		StopTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static StopTypeEnum fromValue(final String text) {
			for (final StopTypeEnum b : StopTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("stopType")
	private StopTypeEnum stopType = null;

	@JsonProperty("gracefulStopTimeout")
	private Integer gracefulStopTimeout = null;

	@JsonProperty("additionalParams")
	private Map<String, String> additionalParams = null;

	public OperateVnfRequest vnfcInstanceId(final List<String> vnfcInstanceId) {
		this.vnfcInstanceId = vnfcInstanceId;
		return this;
	}

	public OperateVnfRequest addVnfcInstanceIdItem(final String vnfcInstanceIdItem) {
		if (this.vnfcInstanceId == null) {
			this.vnfcInstanceId = new ArrayList<>();
		}
		this.vnfcInstanceId.add(vnfcInstanceIdItem);
		return this;
	}

	/**
	 * List of identifiers of VNFC instances. Each identifier references the \"id\"
	 * attribute in a \"VnfcInfo\" structure. Cardinality can be \"0\" to denote
	 * that the request applies to the whole VNF and not a specific VNFC instance.
	 *
	 * @return vnfcInstanceId
	 **/
	@Schema(description = "List of identifiers of VNFC instances. Each identifier references the \"id\" attribute in a \"VnfcInfo\" structure. Cardinality can be \"0\" to denote that the request applies to the whole VNF and not a specific VNFC instance. ")

	public List<String> getVnfcInstanceId() {
		return vnfcInstanceId;
	}

	public void setVnfcInstanceId(final List<String> vnfcInstanceId) {
		this.vnfcInstanceId = vnfcInstanceId;
	}

	public OperateVnfRequest changeStateTo(final VnfOperationalStateType changeStateTo) {
		this.changeStateTo = changeStateTo;
		return this;
	}

	/**
	 * Get changeStateTo
	 *
	 * @return changeStateTo
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public VnfOperationalStateType getChangeStateTo() {
		return changeStateTo;
	}

	public void setChangeStateTo(final VnfOperationalStateType changeStateTo) {
		this.changeStateTo = changeStateTo;
	}

	public OperateVnfRequest stopType(final StopTypeEnum stopType) {
		this.stopType = stopType;
		return this;
	}

	/**
	 * It signals whether forceful or graceful stop is requested. See note
	 *
	 * @return stopType
	 **/
	@Schema(description = "It signals whether forceful or graceful stop is requested. See note ")

	public StopTypeEnum getStopType() {
		return stopType;
	}

	public void setStopType(final StopTypeEnum stopType) {
		this.stopType = stopType;
	}

	public OperateVnfRequest gracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
		return this;
	}

	/**
	 * The time interval (in seconds) to wait for the VNF to be taken out of service
	 * during graceful stop, before stopping the VNF. See note.
	 *
	 * @return gracefulStopTimeout
	 **/
	@Schema(description = "The time interval (in seconds) to wait for the VNF to be taken out of service during graceful stop, before stopping the VNF. See note. ")

	public Integer getGracefulStopTimeout() {
		return gracefulStopTimeout;
	}

	public void setGracefulStopTimeout(final Integer gracefulStopTimeout) {
		this.gracefulStopTimeout = gracefulStopTimeout;
	}

	public OperateVnfRequest additionalParams(final Map<String, String> additionalParams) {
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
		final OperateVnfRequest operateVnfRequest = (OperateVnfRequest) o;
		return Objects.equals(this.vnfcInstanceId, operateVnfRequest.vnfcInstanceId) &&
				Objects.equals(this.changeStateTo, operateVnfRequest.changeStateTo) &&
				Objects.equals(this.stopType, operateVnfRequest.stopType) &&
				Objects.equals(this.gracefulStopTimeout, operateVnfRequest.gracefulStopTimeout) &&
				Objects.equals(this.additionalParams, operateVnfRequest.additionalParams);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vnfcInstanceId, changeStateTo, stopType, gracefulStopTimeout, additionalParams);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class OperateVnfRequest {\n");

		sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
		sb.append("    changeStateTo: ").append(toIndentedString(changeStateTo)).append("\n");
		sb.append("    stopType: ").append(toIndentedString(stopType)).append("\n");
		sb.append("    gracefulStopTimeout: ").append(toIndentedString(gracefulStopTimeout)).append("\n");
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
