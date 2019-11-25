package com.ubiqube.etsi.mano.service.event.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.vnf.sol005.PkgmNotificationsFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.service.event.VnfEvent;

@Service
public class NotificationsController {

	private static final Logger LOG = LoggerFactory.getLogger(NotificationsController.class);
	private final VnfEvent vnfEvent;

	public NotificationsController(final VnfEvent vnfEvent) {
		super();
		this.vnfEvent = vnfEvent;
	}

	@JmsListener(destination = "system.notifications")
	public void onEvent(final EventMessage ev) {
		LOG.info("Receiving event: {}", ev);
		switch (ev.notificationEvent) {
		case VNF_PKG_ONBOARDING:
			vnfEvent.onEvent(ev.getObjectId(), NotificationTypesEnum.VNFPACKAGEONBOARDINGNOTIFICATION);
			break;
		case VNF_PKG_ONCHANGE:
			vnfEvent.onEvent(ev.getObjectId(), NotificationTypesEnum.VNFPACKAGECHANGENOTIFICATION);
			break;
		default:
			LOG.error("Unable to handle event type {}", ev.notificationEvent);
			break;
		}
	}
}
