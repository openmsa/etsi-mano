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

package com.ubiqube.etsi.mano.vnfm.v261.model.nsperfo;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This type represents criteria that define a threshold.
 */
@Schema(description = "This type represents criteria that define a threshold. ")
@Validated


public class ThresholdCriteria {
	@JsonProperty("performanceMetric")
	private String performanceMetric = null;

	/**
	 * Type of threshold. This attribute determines which other attributes are
	 * present in the data structure. Permitted values: * SIMPLE: Single-valued
	 * static threshold In the present document, simple thresholds are defined. The
	 * definition of additional threshold types is left for future specification.
	 */
	public enum ThresholdTypeEnum {
		SIMPLE("SIMPLE");

		private final String value;

		ThresholdTypeEnum(final String value) {
			this.value = value;
		}

		@Override
		@JsonValue
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static ThresholdTypeEnum fromValue(final String text) {
			for (final ThresholdTypeEnum b : ThresholdTypeEnum.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	@JsonProperty("thresholdType")
	private ThresholdTypeEnum thresholdType = null;

	@JsonProperty("simpleThresholdDetails")
	private ThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails = null;

	public ThresholdCriteria performanceMetric(final String performanceMetric) {
		this.performanceMetric = performanceMetric;
		return this;
	}

	/**
	 * Defines the performance metric associated with the threshold. Valid values
	 * are specified as \"Measurement Name\" values in clause 7.2 of ETSI GS NFV-IFA
	 * 027.
	 * 
	 * @return performanceMetric
	 **/
	@Schema(required = true, description = "Defines the performance metric associated with the threshold. Valid values are specified as \"Measurement Name\" values in clause 7.2 of ETSI GS NFV-IFA 027. ")
	@NotNull

	public String getPerformanceMetric() {
		return performanceMetric;
	}

	public void setPerformanceMetric(final String performanceMetric) {
		this.performanceMetric = performanceMetric;
	}

	public ThresholdCriteria thresholdType(final ThresholdTypeEnum thresholdType) {
		this.thresholdType = thresholdType;
		return this;
	}

	/**
	 * Type of threshold. This attribute determines which other attributes are
	 * present in the data structure. Permitted values: * SIMPLE: Single-valued
	 * static threshold In the present document, simple thresholds are defined. The
	 * definition of additional threshold types is left for future specification.
	 * 
	 * @return thresholdType
	 **/
	@Schema(required = true, description = "Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: * SIMPLE: Single-valued static threshold In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification. ")
	@NotNull

	public ThresholdTypeEnum getThresholdType() {
		return thresholdType;
	}

	public void setThresholdType(final ThresholdTypeEnum thresholdType) {
		this.thresholdType = thresholdType;
	}

	public ThresholdCriteria simpleThresholdDetails(final ThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails) {
		this.simpleThresholdDetails = simpleThresholdDetails;
		return this;
	}

	/**
	 * Get simpleThresholdDetails
	 * 
	 * @return simpleThresholdDetails
	 **/
	@Schema(description = "")

	@Valid

	public ThresholdCriteriaSimpleThresholdDetails getSimpleThresholdDetails() {
		return simpleThresholdDetails;
	}

	public void setSimpleThresholdDetails(final ThresholdCriteriaSimpleThresholdDetails simpleThresholdDetails) {
		this.simpleThresholdDetails = simpleThresholdDetails;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final ThresholdCriteria thresholdCriteria = (ThresholdCriteria) o;
		return Objects.equals(this.performanceMetric, thresholdCriteria.performanceMetric) &&
				Objects.equals(this.thresholdType, thresholdCriteria.thresholdType) &&
				Objects.equals(this.simpleThresholdDetails, thresholdCriteria.simpleThresholdDetails);
	}

	@Override
	public int hashCode() {
		return Objects.hash(performanceMetric, thresholdType, simpleThresholdDetails);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class ThresholdCriteria {\n");

		sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
		sb.append("    thresholdType: ").append(toIndentedString(thresholdType)).append("\n");
		sb.append("    simpleThresholdDetails: ").append(toIndentedString(simpleThresholdDetails)).append("\n");
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
