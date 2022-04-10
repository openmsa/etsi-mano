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
package tosca.datatypes.nfv;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.scalar.Time;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class VnfMonitoringParameter {
	/**
	 * Describes the periodicity at which to collect the performance information.
	 */
	@Valid
	@JsonProperty("collection_period")
	private Time collectionPeriod;

	/**
	 * Identifies a performance metric to be monitored, according to ETSI GS NFV-IFA
	 * 027.
	 */
	@Valid
	@NotNull
	@JsonProperty("performance_metric")
	private String performanceMetric;

	/**
	 * Human readable name of the monitoring parameter
	 */
	@Valid
	@NotNull
	@JsonProperty("name")
	private String name;

	public Time getCollectionPeriod() {
		return this.collectionPeriod;
	}

	public void setCollectionPeriod(final Time collectionPeriod) {
		this.collectionPeriod = collectionPeriod;
	}

	@NotNull
	public String getPerformanceMetric() {
		return this.performanceMetric;
	}

	public void setPerformanceMetric(@NotNull final String performanceMetric) {
		this.performanceMetric = performanceMetric;
	}

	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(@NotNull final String name) {
		this.name = name;
	}

}
