package com.ubiqube.etsi.mano.service.event;

public class EventMessage {
	NotificationEvent notificationEvent;
	String objectId;

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
