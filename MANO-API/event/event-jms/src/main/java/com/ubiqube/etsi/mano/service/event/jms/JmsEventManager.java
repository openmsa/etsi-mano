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

import java.util.Map;
import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.EventMessage;
import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.service.event.ActionMessage;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

@Service
public class JmsEventManager implements EventManager {
	private final JmsTemplate jmsTemplate;
	private final EventMessageJpa eventMessageJpa;

	public JmsEventManager(final JmsTemplate jmsTemplate, final EventMessageJpa eventMessageJpa) {
		super();
		this.jmsTemplate = jmsTemplate;
		this.eventMessageJpa = eventMessageJpa;
	}

	@Override
	public void sendNotification(final NotificationEvent notificationEvent, final UUID objectId, final Map<String, String> additionalParameters) {
		EventMessage msg = new EventMessage(notificationEvent, objectId, additionalParameters);
		msg = eventMessageJpa.save(msg);
		jmsTemplate.convertAndSend("system.notifications", msg);
	}

	@Override
	public void sendActionVnfm(final ActionType actionType, final UUID objectId, final Map<String, Object> parameters) {
		final ActionMessage msg = new ActionMessage(actionType, objectId, parameters);
		jmsTemplate.convertAndSend("system.actions.vnfm", msg);
	}

	@Override
	public void sendActionNfvo(final ActionType actionType, final UUID objectId, final Map<String, Object> parameters) {
		final ActionMessage msg = new ActionMessage(actionType, objectId, parameters);
		jmsTemplate.convertAndSend("system.actions.nfvo", msg);
	}

	@Override
	public void sendGrant(final UUID objectId, final Map<String, Object> parameters) {
		final GrantMessage msg = new GrantMessage(objectId, parameters);
		jmsTemplate.convertAndSend("system.actions.grants", msg);
	}

	@Override
	public void sendAction(final ActionType actionType, final UUID objectId) {
		final ActionMessage msg = new ActionMessage(actionType, objectId, Map.of());
		jmsTemplate.convertAndSend("system.actions.common", msg);
	}
}
