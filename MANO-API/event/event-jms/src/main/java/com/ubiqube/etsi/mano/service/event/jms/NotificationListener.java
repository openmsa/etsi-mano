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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.EventMessage;
import com.ubiqube.etsi.mano.service.NfvoService;
import com.ubiqube.etsi.mano.service.event.NotificationController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@ConditionalOnBean(NfvoService.class)
public class NotificationListener {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationListener.class);

	private final NotificationController notificationController;

	public NotificationListener(final NotificationController notificationController) {
		super();
		this.notificationController = notificationController;
	}

	@JmsListener(destination = "system.notifications", concurrency = "2-4")
	public void onEvent(final EventMessage ev) {
		LOG.info("Notification Controller Received event: {}", ev);
		notificationController.onEvent(ev);
	}
}
