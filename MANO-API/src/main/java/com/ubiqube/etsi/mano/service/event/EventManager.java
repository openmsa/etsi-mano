package com.ubiqube.etsi.mano.service.event;

import java.util.Map;

public interface EventManager {

	void sendNotification(NotificationEvent notificationEvent, String objectId);

	void sendAction(ActionType actionType, String objectId, Map<String, Object> parameters);
}
