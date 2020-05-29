package com.ubiqube.etsi.mano.service.event.jms;

import java.util.UUID;

import com.ubiqube.etsi.mano.service.event.NotificationEvent;

public class EventMessage {
	private NotificationEvent notificationEvent;
	private UUID objectId;

	public EventMessage() {
		// Nothing.
	}

	public EventMessage(final NotificationEvent _notificationEvent, final UUID _objectId) {
		notificationEvent = _notificationEvent;
		objectId = _objectId;
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
