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

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Null;

import lombok.Data;

@Data
@Entity
public class PerformanceReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;

	/**
	 * Type of the measured object. The applicable measured object type for a
	 * measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	String objectType;

	/**
	 * Identifier of the measured object instance for which the performance metric
	 * is reported.
	 */
	UUID objectInstanceId;

	/**
	 * Identifier of the sub-object instance of the measured object instance for
	 * which the performance metric is reported. Shall be present if this is
	 * required in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the related measured
	 * object type. See note.
	 */
	@Null
	UUID subObjectInstanceId;

	/**
	 * Name of the metric collected. This attribute shall contain the related
	 * "Measurement Name" value as defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	String performanceMetric;

	/**
	 * List of performance values with associated timestamp.
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Set<PerformanceValue> performanceValues;

}
