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
package com.ubiqube.etsi.mano.common.v261.model.nsperfo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty(required = true, value = "Time stamp indicating when the data has been collected. ")
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
	 * Value of the metric collected. The type of this attribute shall correspond to
	 * the related \"Measurement Unit\" as defined in clause 7.2. of ETSI GS NFV-IFA
	 * 027.
	 *
	 * @return value
	 **/
	@ApiModelProperty(value = "Value of the metric collected. The type of this attribute shall correspond to the related \"Measurement Unit\" as defined in clause 7.2. of ETSI GS NFV-IFA 027. ")

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
