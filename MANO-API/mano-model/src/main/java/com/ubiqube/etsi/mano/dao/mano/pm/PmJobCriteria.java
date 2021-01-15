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
package com.ubiqube.etsi.mano.dao.mano.pm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Data
@Embeddable
public class PmJobCriteria {
	/**
	 * This defines the types of performance metrics for the specified object instances. Valid values are specified as "Measurement Name" values in clause 7.2 of ETSI GS NFV-IFA 027 [5]). At least one of the two attributes (performance metric or group) shall be present.
	 *
	 * Cardinaliy: 0..N
	 */
	@ElementCollection
	private Set<String> performanceMetric;

	/**
	 * Group of performance metrics. A metric group is a pre-defined list of metrics, known to the API producer that it can decompose to individual metrics. Valid values are specified as "Measurement Group" values in clause 7.2 of ETSI GS NFV-IFA 027 [5]). At least one of the two attributes (performance metric or group) shall be present.
	 *
	 * Cardinaliy: 0..N
	 */
	@ElementCollection
	private Set<String> performanceMetricGroup;

	/**
	 * Specifies the periodicity at which the API producer will collect performance information. The unit shall be seconds. See note 1 and note 2.
	 */
	@NotNull
	private Integer collectionPeriod;

	/**
	 * Specifies the periodicity at which the API producer will report to the API consumer about performance information. The unit shall be seconds. See note 1 and note 2.
	 */
	@NotNull
	private Integer reportingPeriod;

	/**
	 * Identifies a time boundary after which the reporting will stop. The boundary shall allow a single reporting as well as periodic reporting up to the boundary.
	 */
	@Null
	private LocalDateTime reportingBoundary;
}
