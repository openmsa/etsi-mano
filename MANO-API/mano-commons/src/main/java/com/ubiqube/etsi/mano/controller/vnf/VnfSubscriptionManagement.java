package com.ubiqube.etsi.mano.controller.vnf;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;

public interface VnfSubscriptionManagement {

	List<Subscription> subscriptionsGet(final String filter);

	Subscription subscriptionsPost(@Nonnull final Subscription subscription, final ApiTypesEnum api);

	void vnfPackageChangeNotificationPost(@Nonnull final VnfPackageChangeNotification notificationsMessage);

	void vnfPackageOnboardingNotificationPost(@Nonnull final VnfPackageOnboardingNotification notificationsMessage);

	void subscriptionsSubscriptionIdDelete(@Nonnull final String _subscriptionId);

	Subscription subscriptionsSubscriptionIdGet(@Nonnull final UUID _subscriptionId);

}
