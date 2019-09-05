package com.ubiqube.etsi.mano.service;

import java.util.Map;

public interface EventManager {

	void sendNotification(NotificationEvent notificationEvent, String objectId);

	void sendAction(ActionType actionType, String objectId, Map<String, Object> parameters);
}
