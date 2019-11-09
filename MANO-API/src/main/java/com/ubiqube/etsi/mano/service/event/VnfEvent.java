package com.ubiqube.etsi.mano.service.event;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ubiqube.etsi.mano.controller.vnf.ApiTypesEnum;
import com.ubiqube.etsi.mano.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.controller.vnf.sol003.Sol003Linkable;
import com.ubiqube.etsi.mano.controller.vnf.sol005.Sol005Linkable;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.VnfPackageFactory;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter.NotificationTypesEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequestAuthentication;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;

/**
 * TODO: we cannot use the MANO filter query language, instead of this use AST
 * node directly.
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
		// transactionTemplate = new TransactionTemplate(_transactionManager);
	}

	public void onEvent(final String vnfPkgId, final NotificationTypesEnum event) {
		final List<SubscriptionObject> res = subscriptionRepository.selectNotifications(vnfPkgId, event.value());

		LOG.info("VNF Package event received: {}/{} with {} elements.", event, vnfPkgId, res.size());

		res.stream().forEach(x -> sendNotification(vnfPkgId, x, event));
	}

	private void sendNotification(final String vnfPkgId, final SubscriptionObject subscriptionObject, final NotificationTypesEnum event) {
		final Linkable links = getLinkable(subscriptionObject.getApi());
		final SubscriptionsPkgmSubscription req = subscriptionObject.getSubscriptionsPkgmSubscription();
		final String subscriptionId = req.getId();
		final String callbackUri = req.getCallbackUri();
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionObject.getSubscriptionsPkgmSubscriptionRequestAuthentication();

		final String id = UUID.randomUUID().toString();
		Object object;
		if (event == NotificationTypesEnum.VNFPACKAGEONBOARDINGNOTIFICATION) {
			object = VnfPackageFactory.createNotificationVnfPackageOnboardingNotification(subscriptionId, vnfPkgId, "", links);
		} else {
			object = VnfPackageFactory.createVnfPackageChangeNotification(subscriptionId, vnfPkgId, "", links);
		}

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
