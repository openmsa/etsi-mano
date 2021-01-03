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

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Data;

@Embeddable
@Data
public class ThresholdCriteria {
	/**
	 * Defines the performance metric associated with the threshold. Valid values are specified as "Measurement Name" values in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	String performanceMetric;

	/**
	 * Type of threshold. This attribute determines which other attributes are present in the data structure. Permitted values: •SIMPLE: Single-valued static threshold.
	 * 
	 * NOTE: In the present document, simple thresholds are defined. The definition of additional threshold types is left for future specification.
	 */
	ThresholdType thresholdType;

	/**
	 * The hysteresis of the threshold. Shall be represented as a non-negative floating point number. A notification with crossing direction "UP" will be generated if the measured value reaches or exceeds "thresholdValue" + "hysteresis". A notification with crossing direction "DOWN" will be generated if the measured value reaches or undercuts "thresholdValue" - "hysteresis". 
	 * NOTE: The hysteresis is defined to prevent storms of threshold crossing notifications. When processing a request to create a threshold, implementations should enforce a suitable minimum value for this attribute (e.g. override the value or reject the request).
	 */
	@Embedded
	SimpleThresholdDetails simpleThresholdDetails;
}
