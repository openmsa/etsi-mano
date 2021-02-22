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
package com.ubiqube.etsi.mano.dao.mano.alarm;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import lombok.Data;

@Data
@Entity
@Indexed
public class Alarms{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@DocumentId
	@FullTextField
	private UUID id;

	/**
	 * Identifier of the affected VNF instance.
	 */
	@FullTextField
	private UUID managedObjectId;

	/**
	 * Identifiers of the affected VNFC instances. Each identifier references the "id" attribute in a "VnfcInfo" structure. Shall be present if the alarm affects at least one VNFC instance.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<UUID> vnfcInstanceIds;

	/**
	 * The virtualised resources that are causing the VNF fault. Shall be present if the alarm affects virtualised resources.
	 */
	@Embedded
	private FaultyResourceInfo rootCauseFaultyResource;

	/**
	 * Time stamp indicating when the alarm is raised by the managed object.
	 */
	private LocalDateTime alarmRaisedTime;

	/**
	 * Time stamp indicating when the alarm was last changed. It shall be present if the alarm has been updated.
	 */
	private LocalDateTime alarmChangedTime;

	/**
	 * Time stamp indicating when the alarm was cleared. It shall be present if the alarm has been cleared.
	 */
	private LocalDateTime alarmClearedTime;

	/**
	 * Time stamp indicating when the alarm was acknowledged. It shall be present if the alarm has been acknowledged.
	 */
	private LocalDateTime alarmAcknowledgedTime;

	/**
	 * Acknowledgement state of the alarm.
	 */
	@Enumerated(EnumType.STRING)
	private AckState ackState;

	/**
	 * Perceived severity of the managed object failure.
	 */
	@Enumerated(EnumType.STRING)
	private PerceivedSeverityType perceivedSeverity;

	/**
	 * Time stamp indicating when the fault was observed.
	 */
	private LocalDateTime eventTime;

	/**
	 * Type of event.
	 */
	@Enumerated(EnumType.STRING)
	private EventType eventType;

	/**
	 * Additional information to clarify the type of the fault.
	 */
	@FullTextField
	private String faultType;

	/**
	 * Information about the probable cause of the fault.
	 */
	@FullTextField
	private String probableCause;

	/**
	 * Attribute indicating if this fault is the root for other correlated alarms. If true, then the alarms listed in the attribute "correlatedAlarmIds" are caused by this fault. 
	 */
	private boolean rootCause;

	/**
	 * List of identifiers of other alarms correlated to this fault.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<UUID> correlatedAlarmIds;

	/**
	 * Provides additional information about the fault.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> faultDetails;

}
