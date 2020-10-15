package com.ubiqube.etsi.mano.service.event;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;
import com.ubiqube.etsi.mano.service.VersionManager;
import com.ubiqube.etsi.mano.service.VersionService;

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
	private final VersionManager versionManager;

	public VnfEvent(final SubscriptionRepository subscriptionRepository, final Notifications notifications, final VersionManager _versionManager) {
		super();
		this.subscriptionRepository = subscriptionRepository;
		this.notifications = notifications;
		versionManager = _versionManager;
	}

	public void onEvent(final UUID vnfPkgId, final String event) {
		final List<Subscription> res = subscriptionRepository.selectNotifications(vnfPkgId, event);
		LOG.info("VNF Package event received: {}/{} with {} elements.", event, vnfPkgId, res.size());
		res.stream().forEach(x -> sendNotification(vnfPkgId, x, event));
	}

	private void sendNotification(final UUID vnfPkgId, final Subscription subscription, final String event) {
		final VersionService subscrService = versionManager.getSubscriptionService(event);
		Object object;
		if ("VnfPackageChangeNotification".equals(event)) {
			object = subscrService.createVnfPackageChangeNotification(subscription.getId(), vnfPkgId);
		} else if ("".equals(event)) {
			object = subscrService.createNotificationVnfPackageOnboardingNotification(subscription.getId(), vnfPkgId);
		} else {
			LOG.warn("Unknown event received: {}", event);
			return;
		}
		final String callbackUri = subscription.getCallbackUri();
		final AuthentificationInformations auth = subscription.getAuthentificationInformations();
		notifications.doNotification(object, callbackUri, auth);
	}

}