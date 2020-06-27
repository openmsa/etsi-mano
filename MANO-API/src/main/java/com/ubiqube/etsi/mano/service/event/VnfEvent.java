package com.ubiqube.etsi.mano.service.event;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.nfvo.v261.controller.vnf.Sol005Linkable;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnf.Sol003Linkable;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
@Transactional
public class VnfEvent {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(VnfEvent.class);

	private final SubscriptionRepository subscriptionRepository;
	private final Notifications notifications;

	public VnfEvent(final SubscriptionRepository subscriptionRepository, final Notifications notifications) {
		super();
		this.subscriptionRepository = subscriptionRepository;
		this.notifications = notifications;
	}

	public void onEvent(final UUID vnfPkgId, final String event) {
		final List<Subscription> res = subscriptionRepository.selectNotifications(vnfPkgId, event);
		LOG.info("VNF Package event received: {}/{} with {} elements.", event, vnfPkgId, res.size());
		res.stream().forEach(x -> sendNotification(vnfPkgId, x, event));
	}

	private void sendNotification(final UUID vnfPkgId, final Subscription subscription, final String event) {
		final Linkable links = getLinkable(subscription.getApi());

		Object object;
		if (event.equals("VnfPackageOnboardingNotification")) {
			object = VnfPackageFactory.createNotificationVnfPackageOnboardingNotification(subscription.getId(), vnfPkgId, "", links);
		} else {
			object = VnfPackageFactory.createVnfPackageChangeNotification(subscription.getId(), vnfPkgId, "", links);
		}

		final String callbackUri = subscription.getCallbackUri();
		final AuthentificationInformations auth = subscription.getAuthentificationInformations();
		notifications.doNotification(object, callbackUri, auth);
	}

	private static Linkable getLinkable(final ApiTypesEnum api) {
		switch (api) {
		case SOL003:
			return new Sol003Linkable();
		case SOL005:
			return new Sol005Linkable();
		default:
			throw new GenericException("Unknown value " + api);
		}
	}

}
