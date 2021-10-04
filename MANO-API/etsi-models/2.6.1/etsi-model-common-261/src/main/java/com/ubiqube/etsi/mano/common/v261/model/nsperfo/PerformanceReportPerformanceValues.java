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

package com.ubiqube.etsi.mano.common.v261.model.nsperfo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * PerformanceReportPerformanceValues
 */
@Validated
public class PerformanceReportPerformanceValues {
	@JsonProperty("timeStamp")
	private String timeStamp = null;

	@JsonProperty("value")
	private Object value = null;

	public PerformanceReportPerformanceValues timeStamp(final String timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Time stamp indicating when the data has been collected.
	 *
	 * @return timeStamp
	 **/
	@Schema(required = true, description = "Time stamp indicating when the data has been collected. ")
	@NotNull

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(final String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public PerformanceReportPerformanceValues value(final Object value) {
		this.value = value;
		return this;
	}

	/**
	 * Value of the metric collected. The type of this attribute shall correspond to the related \"Measurement Unit\" as defined in clause 7.2. of ETSI GS NFV-IFA 027.
	 *
	 * @return value
	 **/
	@Schema(description = "Value of the metric collected. The type of this attribute shall correspond to the related \"Measurement Unit\" as defined in clause 7.2. of ETSI GS NFV-IFA 027. ")

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final PerformanceReportPerformanceValues performanceReportPerformanceValues = (PerformanceReportPerformanceValues) o;
		return Objects.equals(this.timeStamp, performanceReportPerformanceValues.timeStamp) &&
				Objects.equals(this.value, performanceReportPerformanceValues.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(timeStamp, value);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class PerformanceReportPerformanceValues {\n");

		sb.append("    timeStamp: ").append(toIndentedString(timeStamp)).append("\n");
		sb.append("    value: ").append(toIndentedString(value)).append("\n");
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
