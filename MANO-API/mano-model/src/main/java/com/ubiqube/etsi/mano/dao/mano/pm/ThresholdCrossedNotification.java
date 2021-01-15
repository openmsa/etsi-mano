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
import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Null;

import lombok.Data;

@Data
@Entity
public class ThresholdCrossedNotification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * Discriminator for the different notification types. Shall be set to "ThresholdCrossedNotification" for this notification type.
	 */
	private String notificationType;

	/**
	 * Date and time of the generation of the notification.
	 */
	private LocalDateTime timeStamp;

	/**
	 * Identifier of the threshold which has been crossed.
	 */
	private UUID thresholdId;

	/**
	 * An indication of whether the threshold was crossed in upward or downward direction.
	 */
	private CrossingDirectionType CrossingDirection;

	/**
	 * Type of the measured object.  The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	private String objectType;

	/**
	 * Identifier of the measured object instance as per clause 6.2 of ETSI GS NFV-IFA 027 [5].
	 */
	private UUID objectInstanceId;

	/**
	 * Identifier of the sub-object of the measured object to which the measurement applies. Shall be present if this is required in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the related measured object type. See note.
	 */
	@Null
	private UUID subObjectInstanceId;

	/**
	 * Performance metric associated with the threshold. This attribute shall contain the related "Measurement Name" value as defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	private String performanceMetric;

	/**
	 * Value of the metric that resulted in threshold crossing. The type of this attribute shall correspond to the related "Measurement Unit" as defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	private Double performanceValue;

	/**
	 * Measurement context information related to the measured value. The set of applicable keys is defined per measurement in the related "Measurement Context" in clause 7.2 of ETSI GS NFV-IFA 027 [5]
	 * @See ETSI GS NFV-TST 008 [10]
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> context;

}
