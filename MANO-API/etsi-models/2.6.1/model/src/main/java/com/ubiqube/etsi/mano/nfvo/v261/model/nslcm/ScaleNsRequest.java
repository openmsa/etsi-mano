/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.nfvo.v261.model.nslcm;

import java.time.OffsetDateTime;
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
 * This type represents a request for the scale NS operation.
 */
@ApiModel(description = "This type represents a request for the scale NS operation. ")
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
	private OffsetDateTime scaleTime = null;

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
	 * The necessary information to scale the referenced NS instance. It shall be
	 * present when scaleType = SCALE_NS.
	 * 
	 * @return scaleNsData
	 **/
	@ApiModelProperty(value = "The necessary information to scale the referenced NS instance. It shall be present when scaleType = SCALE_NS. ")

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

	public ScaleNsRequest scaleTime(final OffsetDateTime scaleTime) {
		this.scaleTime = scaleTime;
		return this;
	}

	/**
	 * Timestamp indicating the scale time of the NS, i.e. the NS will be scaled at
	 * this timestamp. Cardinality \"0\" indicates the NS scaling takes place
	 * immediately\".
	 * 
	 * @return scaleTime
	 **/
	@ApiModelProperty(value = "Timestamp indicating the scale time of the NS, i.e. the NS will be scaled at this timestamp. Cardinality \"0\" indicates the NS scaling takes place immediately\". ")

	@Valid

	public OffsetDateTime getScaleTime() {
		return scaleTime;
	}

	public void setScaleTime(final OffsetDateTime scaleTime) {
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
