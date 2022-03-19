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
package com.ubiqube.etsi.mano.model;

import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
public class EventMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Enumerated(EnumType.STRING)
	private NotificationEvent notificationEvent;
	private UUID objectId;
	@ElementCollection
	private Map<String, String> additionalParameters;

	public EventMessage() {
		// Nothing.
	}

	public EventMessage(final NotificationEvent notificationEvent, final UUID objectId, final Map<String, String> additionalParameters) {
		this.notificationEvent = notificationEvent;
		this.objectId = objectId;
		this.additionalParameters = additionalParameters;
	}

	@Override
	public String toString() {
		return "EventMessage [notificationEvent=" + notificationEvent + ", objectId=" + objectId + "]";
	}

}
