package com.ubiqube.etsi.mano.service.event;

import java.util.Map;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

public class JmsEventManager implements EventManager {
	private final JmsTemplate jmsTemplate;
	private Destination destinationName;

	public JmsEventManager(final JmsTemplate _jmsTemplate) {
		super();
		jmsTemplate = _jmsTemplate;
	}

	@Override
	public void sendNotification(final NotificationEvent notificationEvent, final String objectId) {
		final EventMessage msg = new EventMessage(notificationEvent, objectId);
		jmsTemplate.convertAndSend(destinationName, msg);
	}

	@Override
	public void sendAction(final ActionType actionType, final String objectId, final Map<String, Object> parameters) {
		final ActionMessage msg = new ActionMessage(actionType, objectId, parameters);
		jmsTemplate.convertAndSend(destinationName, msg);
	}

}
