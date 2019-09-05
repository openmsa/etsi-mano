package com.ubiqube.etsi.mano.service;

public interface EventManager {

	void sendNotification(NotificationEvent notificationEvent, String objectId);

}
