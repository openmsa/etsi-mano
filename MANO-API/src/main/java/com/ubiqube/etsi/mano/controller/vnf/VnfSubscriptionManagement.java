package com.ubiqube.etsi.mano.controller.vnf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.sol005.InlineResponse2001;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationVnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationsMessage;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequestAuthentication;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotification;
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

	public List<SubscriptionsPkgmSubscription> subscriptionsGet(final String filter, final Linkable links) {
		final List<SubscriptionObject> result = subscriptionRepository.query(filter);
		final List<SubscriptionsPkgmSubscription> response = result.stream()
				.map(x -> x.getSubscriptionsPkgmSubscription())
				.collect(Collectors.toList());
		response.stream().forEach(x -> x.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(x.getId())));
		return response;
	}

	public List<InlineResponse2001> subscriptionsPost(@Nonnull final SubscriptionsPkgmSubscriptionRequest subscriptionsPostQuery, @Nonnull final String id, final Linkable links) {
		// Response
		final ArrayList<InlineResponse2001> response = new ArrayList<>();
		final String callback = subscriptionsPostQuery.getCallbackUri();
		final SubscriptionsPkgmSubscriptionFilter filter = subscriptionsPostQuery.getFilter();
		final SubscriptionsPkgmSubscription subscription = VnfPackageFactory.createSubscriptionsPkgmSubscription(id, callback, filter);

		// TODO: Check test endpoint.
		final SubscriptionObject subscriptionObject = new SubscriptionObject(subscriptionsPostQuery.getAuthentication(), subscription);
		subscriptionObject.setApi(links.getApi());
		subscriptionRepository.save(subscriptionObject);
		subscription.setLinks(links.createSubscriptionsPkgmSubscriptionLinks(subscription.getId()));
		final InlineResponse2001 pack = new InlineResponse2001();
		pack.setPkgmSubscription(subscription);
		response.add(pack);
		return response;
	}

	public void vnfPackageChangeNotificationPost(@Nonnull final NotificationsMessage notificationsMessage, @Nonnull final String id, final Linkable links) {
		final String vnfPkgId = notificationsMessage.getVnfPkgId();
		final String vnfdId = notificationsMessage.getVnfdId();
		final String subscriptionId = notificationsMessage.getSubscriptionId();

		final SubscriptionObject subscriptionsRepository = subscriptionRepository.get(subscriptionId);
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionsRepository.getSubscriptionsPkgmSubscriptionRequestAuthentication();
		final String callbackUri = subscriptionsRepository.getSubscriptionsPkgmSubscription().getCallbackUri();

		final VnfPackageChangeNotification vnfPackageChangeNotification = VnfPackageFactory.createVnfPackageChangeNotification(id, subscriptionId, vnfPkgId, vnfdId, links);

		notifications.doNotification(vnfPackageChangeNotification, callbackUri, auth);
	}

	public void vnfPackageOnboardingNotificationPost(@Nonnull final NotificationsMessage notificationsMessage, @Nonnull final String id, final Linkable links) {
		final String subscriptionId = notificationsMessage.getSubscriptionId();
		final SubscriptionObject subscriptionsRepository = subscriptionRepository.get(subscriptionId);
		final SubscriptionsPkgmSubscription req = subscriptionsRepository.getSubscriptionsPkgmSubscription();
		final String cbUrl = req.getCallbackUri();
		final String vnfPkgId = notificationsMessage.getVnfPkgId();
		final String vnfdId = notificationsMessage.getVnfdId();
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionsRepository.getSubscriptionsPkgmSubscriptionRequestAuthentication();

		final NotificationVnfPackageOnboardingNotification notificationVnfPackageOnboardingNotification = VnfPackageFactory.createNotificationVnfPackageOnboardingNotification(id, subscriptionId, vnfPkgId, vnfdId, links);
		notifications.doNotification(notificationVnfPackageOnboardingNotification, cbUrl, auth);
	}

	public void subscriptionsSubscriptionIdDelete(@Nonnull final String _subscriptionId) {
		subscriptionRepository.get(_subscriptionId);
		subscriptionRepository.delete(_subscriptionId);
	}

	public SubscriptionsPkgmSubscription subscriptionsSubscriptionIdGet(@Nonnull final String _subscriptionId, final Linkable links) {
		return subscriptionRepository.get(_subscriptionId).getSubscriptionsPkgmSubscription().links(links.createSubscriptionsPkgmSubscriptionLinks(_subscriptionId));
	}

}