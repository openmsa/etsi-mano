package com.ubiqube.etsi.mano.controller.vnf;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPkgChangeNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPkgOnboardingNotification;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;
import com.ubiqube.etsi.mano.service.event.Notifications;

@Service
public class VnfSubscriptionManagement {
	private final Notifications notifications;
	private final SubscriptionRepository subscriptionRepository;

	public VnfSubscriptionManagement(final Notifications _notifications, final SubscriptionRepository _subscriptionRepository) {
		super();
		this.notifications = _notifications;
		this.subscriptionRepository = _subscriptionRepository;
	}

	public List<Subscription> subscriptionsGet(final String filter) {
		return subscriptionRepository.query(filter);

	}

	public Subscription subscriptionsPost(@Nonnull final Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	public void vnfPackageChangeNotificationPost(@Nonnull final VnfPkgChangeNotification notificationsMessage) {
		final UUID subscriptionId = UUID.fromString(notificationsMessage.getSubscriptionId());

		final Subscription subscriptionsRepository = subscriptionRepository.get(subscriptionId);
		final AuthentificationInformations auth = subscriptionsRepository.getAuthentificationInformations();
		final String callbackUri = subscriptionsRepository.getCallbackUri();
		// There is a version, problem.
		notifications.doNotification(notificationsMessage, callbackUri, auth);
	}

	public void vnfPackageOnboardingNotificationPost(@Nonnull final VnfPkgOnboardingNotification notificationsMessage) {
		final UUID subscriptionId = UUID.fromString(notificationsMessage.getSubscriptionId());
		final Subscription subscription = subscriptionRepository.get(subscriptionId);
		final AuthentificationInformations auth = subscription.getAuthentificationInformations();
		final String cbUrl = subscription.getCallbackUri();
		// Version problem.
		notifications.doNotification(notificationsMessage, cbUrl, auth);
	}

	public void subscriptionsSubscriptionIdDelete(@Nonnull final String _subscriptionId) {
		final UUID subscriptionId = UUID.fromString(_subscriptionId);
		subscriptionRepository.get(subscriptionId);
		subscriptionRepository.delete(subscriptionId);
	}

	public Subscription subscriptionsSubscriptionIdGet(@Nonnull final UUID _subscriptionId) {
		return subscriptionRepository.get(_subscriptionId);
	}

}
