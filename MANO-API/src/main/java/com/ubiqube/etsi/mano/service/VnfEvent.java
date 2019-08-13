package com.ubiqube.etsi.mano.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.vnf.ApiTypesEnum;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.controller.vnf.sol003.Sol003Linkable;
import com.ubiqube.etsi.mano.controller.vnf.sol005.Sol005Linkable;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationVnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequestAuthentication;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

@Service
public class VnfEvent {

	private static final Logger LOG = LoggerFactory.getLogger(VnfEvent.class);

	private final SubscriptionRepository subscriptionRepository;
	private final Notifications notifications;

	public VnfEvent(final SubscriptionRepository subscriptionRepository, final Notifications notifications) {
		super();
		this.subscriptionRepository = subscriptionRepository;
		this.notifications = notifications;
	}

	public void onEvent(final String vnfPkgId, final NotificationTypesEnum event) {
		final List<SubscriptionObject> res = selectNotifications(vnfPkgId, event.value());

		LOG.info("VNF Package event received: {}/{} with {} elements.", event, vnfPkgId, res.size());

		for (final SubscriptionObject subscriptionObject : res) {
			if (event == NotificationTypesEnum.VNFPACKAGEONBOARDINGNOTIFICATION) {
				onOnboarding(vnfPkgId, subscriptionObject);
			} else {
				onChange(vnfPkgId, subscriptionObject);
			}
		}
	}

	private List<SubscriptionObject> selectNotifications(final String vnfPkgId, final String event) {
		final StringBuilder sb = new StringBuilder("filter.vnfProductsFromProviders.vnfPkgId.eq=").append(vnfPkgId);
		sb.append("&").append("filter.notificationTypes.eq=").append(event);

		return subscriptionRepository.query(sb.toString());
	}

	private void onChange(final String vnfPkgId, final SubscriptionObject subscriptionObject) {
		final Linkable links = getLinkable(subscriptionObject.getApi());
		final SubscriptionsPkgmSubscription req = subscriptionObject.getSubscriptionsPkgmSubscription();
		final String subscriptionId = req.getId();
		final String callbackUri = req.getCallbackUri();
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionObject.getSubscriptionsPkgmSubscriptionRequestAuthentication();

		final String id = UUID.randomUUID().toString();
		final VnfPackageChangeNotification vnfPackageChangeNotification = VnfPackageFactory.createVnfPackageChangeNotification(id, subscriptionId, vnfPkgId, "", links);

		notifications.doNotification(vnfPackageChangeNotification, callbackUri, auth);
	}

	private void onOnboarding(final String vnfPkgId, final SubscriptionObject subscriptionObject) {
		final Linkable links = getLinkable(subscriptionObject.getApi());
		final SubscriptionsPkgmSubscription req = subscriptionObject.getSubscriptionsPkgmSubscription();
		final String subscriptionId = req.getId();
		final String callbackUri = req.getCallbackUri();
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionObject.getSubscriptionsPkgmSubscriptionRequestAuthentication();

		final String id = UUID.randomUUID().toString();
		final NotificationVnfPackageOnboardingNotification notificationVnfPackageOnboardingNotification = VnfPackageFactory.createNotificationVnfPackageOnboardingNotification(id, subscriptionId, vnfPkgId, "", links);

		notifications.doNotification(notificationVnfPackageOnboardingNotification, callbackUri, auth);
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
