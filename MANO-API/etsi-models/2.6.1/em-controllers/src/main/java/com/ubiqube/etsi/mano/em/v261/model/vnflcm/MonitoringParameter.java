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
package com.ubiqube.etsi.mano.em.v261.model.vnflcm;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * MonitoringParameter
 */
@Validated

public class MonitoringParameter {
	@JsonProperty("id")
	private String id = null;

	@JsonProperty("name")
	private String name = null;

	@JsonProperty("performanceMetric")
	private String performanceMetric = null;

	public MonitoringParameter id(final String id) {
		this.id = id;
		return this;
	}

	/**
	 * Identifier of the monitoring parameter defined in the VNFD.
	 *
	 * @return id
	 **/
	@Schema(required = true, description = "Identifier of the monitoring parameter defined in the VNFD. ")
	@NotNull

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public MonitoringParameter name(final String name) {
		this.name = name;
		return this;
	}

	/**
	 * Human readable name of the monitoring parameter, as defined in the VNFD.
	 *
	 * @return name
	 **/
	@Schema(description = "Human readable name of the monitoring parameter, as defined in the VNFD. ")

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public MonitoringParameter performanceMetric(final String performanceMetric) {
		this.performanceMetric = performanceMetric;
		return this;
	}

	/**
	 * Performance metric that is monitored. This attribute shall contain the related \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027.
	 *
	 * @return performanceMetric
	 **/
	@Schema(required = true, description = "Performance metric that is monitored. This attribute shall contain the related \"Measurement Name\" value as defined in clause 7.2 of ETSI GS NFV-IFA 027. ")
	@NotNull

	public String getPerformanceMetric() {
		return performanceMetric;
	}

	public void setPerformanceMetric(final String performanceMetric) {
		this.performanceMetric = performanceMetric;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final MonitoringParameter monitoringParameter = (MonitoringParameter) o;
		return Objects.equals(this.id, monitoringParameter.id) &&
				Objects.equals(this.name, monitoringParameter.name) &&
				Objects.equals(this.performanceMetric, monitoringParameter.performanceMetric);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, performanceMetric);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class MonitoringParameter {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    performanceMetric: ").append(toIndentedString(performanceMetric)).append("\n");
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
