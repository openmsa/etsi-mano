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
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PerformanceInformationAvailableNotification {
	/**
	 * Identifier of this notification. If a notification is sent multiple times due to multiple subscriptions, the "id" attribute of all these notifications shall have the same value.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * Discriminator for the different notification types. Shall be set to "PerformanceInformationAvailableNotification" for this notification type.
	 */
	private String notificationType;

	/**
	 * Date and time of the generation of the notification.
	 */
	private LocalDateTime timeStamp;

	/**
	 * Identifier of the PM job for which performance information is available
	 */
	private UUID pmJobId;

	/**
	 * Type of the measured object. The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	private String objectType;

	/**
	 * Identifier of the measured object instance as per clause 6.2 of ETSI GS NFV-IFA 027 [5].
	 */
	private UUID objectInstanceId;

	/**
	 * Identifiers of the sub-object instances of the measured object instance for which the measurements have been taken. Shall be present if the related PM job has been set up to measure only a subset of all sub-object instances of the measured object instance and a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the related measured object type. Shall be absent otherwise.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	Set<UUID> subObjectInstanceIds;
}
