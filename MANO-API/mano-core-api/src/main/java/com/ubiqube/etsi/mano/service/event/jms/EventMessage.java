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
package com.ubiqube.etsi.mano.service.event.jms;

import java.util.UUID;

import com.ubiqube.etsi.mano.service.event.NotificationEvent;

public class EventMessage {
	private NotificationEvent notificationEvent;
	private UUID objectId;

	public EventMessage() {
		// Nothing.
	}

	public EventMessage(final NotificationEvent notificationEvent, final UUID objectId) {
		this.notificationEvent = notificationEvent;
		this.objectId = objectId;
	}

	public NotificationEvent getNotificationEvent() {
		return notificationEvent;
	}

	public void setNotificationEvent(final NotificationEvent notificationEvent) {
		this.notificationEvent = notificationEvent;
	}

	public UUID getObjectId() {
		return objectId;
	}

	public void setObjectId(final UUID objectId) {
		this.objectId = objectId;
	}

}
