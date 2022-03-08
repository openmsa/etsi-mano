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

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.LcmCoordResultType;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * NsLcmOpOccLcmCoordinations
 */
@Validated

public class NsLcmOpOccLcmCoordinations {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("coordinationActionName")
	private String coordinationActionName = null;

	@JsonProperty("coordinationResult")
	private LcmCoordResultType coordinationResult = null;

	@JsonProperty("startTime")
	private OffsetDateTime startTime = null;

	@JsonProperty("endTime")
	private OffsetDateTime endTime = null;

	/**
	 * The endpoint type used by this coordination action. Valid values: - MGMT:
	 * coordination with other operation supporting management systems (e.g.
	 * OSS/BSS)
	 */
	public enum EndpointTypeEnum {
		MGMT("MGMT");

		private final String value;

		EndpointTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static EndpointTypeEnum fromValue(final String text) {
			for (final EndpointTypeEnum b : EndpointTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("endpointType")
	private EndpointTypeEnum endpointType = null;

	@JsonProperty("delay")
	private OffsetDateTime delay = null;

	public NsLcmOpOccLcmCoordinations id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public NsLcmOpOccLcmCoordinations coordinationActionName(final String coordinationActionName) {
		this.coordinationActionName = coordinationActionName;
		return this;
	}

	/**
	 * Get coordinationActionName
	 *
	 * @return coordinationActionName
	 **/
	@Schema(required = true, description = "")
	@NotNull

	public String getCoordinationActionName() {
		return coordinationActionName;
	}

	public void setCoordinationActionName(final String coordinationActionName) {
		this.coordinationActionName = coordinationActionName;
	}

	public NsLcmOpOccLcmCoordinations coordinationResult(final LcmCoordResultType coordinationResult) {
		this.coordinationResult = coordinationResult;
		return this;
	}

	/**
	 * Get coordinationResult
	 *
	 * @return coordinationResult
	 **/
	@Schema(description = "")

	@Valid
	public LcmCoordResultType getCoordinationResult() {
		return coordinationResult;
	}

	public void setCoordinationResult(final LcmCoordResultType coordinationResult) {
		this.coordinationResult = coordinationResult;
	}

	public NsLcmOpOccLcmCoordinations startTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
		return this;
	}

	/**
	 * Get startTime
	 *
	 * @return startTime
	 **/
	@Schema(required = true, description = "")
	@NotNull

	@Valid
	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(final OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public NsLcmOpOccLcmCoordinations endTime(final OffsetDateTime endTime) {
		this.endTime = endTime;
		return this;
	}

	/**
	 * Get endTime
	 *
	 * @return endTime
	 **/
	@Schema(description = "")

	@Valid
	public OffsetDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(final OffsetDateTime endTime) {
		this.endTime = endTime;
	}

	public NsLcmOpOccLcmCoordinations endpointType(final EndpointTypeEnum endpointType) {
		this.endpointType = endpointType;
		return this;
	}

	/**
	 * The endpoint type used by this coordination action. Valid values: - MGMT:
	 * coordination with other operation supporting management systems (e.g.
	 * OSS/BSS)
	 *
	 * @return endpointType
	 **/
	@Schema(required = true, description = "The endpoint type used by this coordination action. Valid values: - MGMT: coordination with other operation supporting management systems (e.g. OSS/BSS) ")
	@NotNull

	public EndpointTypeEnum getEndpointType() {
		return endpointType;
	}

	public void setEndpointType(final EndpointTypeEnum endpointType) {
		this.endpointType = endpointType;
	}

	public NsLcmOpOccLcmCoordinations delay(final OffsetDateTime delay) {
		this.delay = delay;
		return this;
	}

	/**
	 * Get delay
	 *
	 * @return delay
	 **/
	@Schema(description = "")

	@Valid
	public OffsetDateTime getDelay() {
		return delay;
	}

	public void setDelay(final OffsetDateTime delay) {
		this.delay = delay;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final NsLcmOpOccLcmCoordinations nsLcmOpOccLcmCoordinations = (NsLcmOpOccLcmCoordinations) o;
		return Objects.equals(this.id, nsLcmOpOccLcmCoordinations.id) &&
				Objects.equals(this.coordinationActionName, nsLcmOpOccLcmCoordinations.coordinationActionName) &&
				Objects.equals(this.coordinationResult, nsLcmOpOccLcmCoordinations.coordinationResult) &&
				Objects.equals(this.startTime, nsLcmOpOccLcmCoordinations.startTime) &&
				Objects.equals(this.endTime, nsLcmOpOccLcmCoordinations.endTime) &&
				Objects.equals(this.endpointType, nsLcmOpOccLcmCoordinations.endpointType) &&
				Objects.equals(this.delay, nsLcmOpOccLcmCoordinations.delay);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, coordinationActionName, coordinationResult, startTime, endTime, endpointType, delay);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class NsLcmOpOccLcmCoordinations {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    coordinationActionName: ").append(toIndentedString(coordinationActionName)).append("\n");
		sb.append("    coordinationResult: ").append(toIndentedString(coordinationResult)).append("\n");
		sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
		sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
		sb.append("    endpointType: ").append(toIndentedString(endpointType)).append("\n");
		sb.append("    delay: ").append(toIndentedString(delay)).append("\n");
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
