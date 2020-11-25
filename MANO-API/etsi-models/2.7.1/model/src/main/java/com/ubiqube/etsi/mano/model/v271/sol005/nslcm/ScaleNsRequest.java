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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This type represents a request for the scale NS operation. Either the
 * parameter scaleNsData or the parameter scaleVnfData, but not both shall be
 * provided
 */
@ApiModel(description = "This type represents a request for the scale NS operation. Either the parameter scaleNsData or the parameter scaleVnfData, but not both shall be provided ")
@Validated
public class ScaleNsRequest {
	/**
	 * Indicates the type of scaling to be performed. Possible values: - SCALE_NS -
	 * SCALE_VNF
	 */
	public enum ScaleTypeEnum {
		NS("SCALE_NS"),

		VNF("SCALE_VNF");

		private final String value;

		ScaleTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ScaleTypeEnum fromValue(final String text) {
			for (final ScaleTypeEnum b : ScaleTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("scaleType")
	private ScaleTypeEnum scaleType = null;

	@JsonProperty("scaleNsData")
	private ScaleNsData scaleNsData = null;

	@JsonProperty("scaleVnfData")
	@Valid
	private List<ScaleVnfData> scaleVnfData = null;

	@JsonProperty("scaleTime")
	private LocalDateTime scaleTime = null;

	public ScaleNsRequest scaleType(final ScaleTypeEnum scaleType) {
		this.scaleType = scaleType;
		return this;
	}

	/**
	 * Indicates the type of scaling to be performed. Possible values: - SCALE_NS -
	 * SCALE_VNF
	 *
	 * @return scaleType
	 **/
	@ApiModelProperty(required = true, value = "Indicates the type of scaling to be performed. Possible values: - SCALE_NS - SCALE_VNF ")
	@NotNull

	public ScaleTypeEnum getScaleType() {
		return scaleType;
	}

	public void setScaleType(final ScaleTypeEnum scaleType) {
		this.scaleType = scaleType;
	}

	public ScaleNsRequest scaleNsData(final ScaleNsData scaleNsData) {
		this.scaleNsData = scaleNsData;
		return this;
	}

	/**
	 * Get scaleNsData
	 *
	 * @return scaleNsData
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public ScaleNsData getScaleNsData() {
		return scaleNsData;
	}

	public void setScaleNsData(final ScaleNsData scaleNsData) {
		this.scaleNsData = scaleNsData;
	}

	public ScaleNsRequest scaleVnfData(final List<ScaleVnfData> scaleVnfData) {
		this.scaleVnfData = scaleVnfData;
		return this;
	}

	public ScaleNsRequest addScaleVnfDataItem(final ScaleVnfData scaleVnfDataItem) {
		if (this.scaleVnfData == null) {
			this.scaleVnfData = new ArrayList<>();
		}
		this.scaleVnfData.add(scaleVnfDataItem);
		return this;
	}

	/**
	 * The necessary information to scale the referenced NS instance. It shall be
	 * present when scaleType = SCALE_VNF.
	 *
	 * @return scaleVnfData
	 **/
	@ApiModelProperty(value = "The necessary information to scale the referenced NS instance. It shall be present when scaleType = SCALE_VNF. ")
	@Valid
	public List<ScaleVnfData> getScaleVnfData() {
		return scaleVnfData;
	}

	public void setScaleVnfData(final List<ScaleVnfData> scaleVnfData) {
		this.scaleVnfData = scaleVnfData;
	}

	public ScaleNsRequest scaleTime(final LocalDateTime scaleTime) {
		this.scaleTime = scaleTime;
		return this;
	}

	/**
	 * Get scaleTime
	 *
	 * @return scaleTime
	 **/
	@ApiModelProperty(value = "")

	@Valid
	public LocalDateTime getScaleTime() {
		return scaleTime;
	}

	public void setScaleTime(final LocalDateTime scaleTime) {
		this.scaleTime = scaleTime;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ScaleNsRequest scaleNsRequest = (ScaleNsRequest) o;
		return Objects.equals(this.scaleType, scaleNsRequest.scaleType) &&
				Objects.equals(this.scaleNsData, scaleNsRequest.scaleNsData) &&
				Objects.equals(this.scaleVnfData, scaleNsRequest.scaleVnfData) &&
				Objects.equals(this.scaleTime, scaleNsRequest.scaleTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(scaleType, scaleNsData, scaleVnfData, scaleTime);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ScaleNsRequest {\n");

		sb.append("    scaleType: ").append(toIndentedString(scaleType)).append("\n");
		sb.append("    scaleNsData: ").append(toIndentedString(scaleNsData)).append("\n");
		sb.append("    scaleVnfData: ").append(toIndentedString(scaleVnfData)).append("\n");
		sb.append("    scaleTime: ").append(toIndentedString(scaleTime)).append("\n");
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
