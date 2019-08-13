package com.ubiqube.etsi.mano.service;

public interface EventManager {

	void sendEvent(NotificationEvent notificationEvent, String objectId);

}
