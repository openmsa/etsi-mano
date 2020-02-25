package com.ubiqube.etsi.mano.service.event.jms;

import com.ubiqube.etsi.mano.service.event.NotificationEvent;

public class EventMessage {
	private NotificationEvent notificationEvent;
	private String objectId;

	public EventMessage() {
		// Nothing.
	}

	public EventMessage(final NotificationEvent _notificationEvent, final String _objectId) {
		notificationEvent = _notificationEvent;
		objectId = _objectId;
	}

	public NotificationEvent getNotificationEvent() {
		return notificationEvent;
	}

	public void setNotificationEvent(final NotificationEvent notificationEvent) {
		this.notificationEvent = notificationEvent;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(final String objectId) {
		this.objectId = objectId;
	}

}
