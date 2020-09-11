package com.ubiqube.etsi.mano.nfvo.v261;

import java.util.UUID;

import com.ubiqube.etsi.mano.common.v261.VnfSubscriptionFactory;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.service.VersionService;

public class Nfvo261VersionService implements VersionService {

	@Override
	public String getVersion() {
		return "2.6.1";
	}

	@Override
	public boolean isNfvo() {
		return true;
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final VnfPackageOnboardingNotification obj = VnfSubscriptionFactory.createNotificationVnfPackageOnboardingNotification(subscriptionId, vnfPkgId, null, null);
		// obj.setLinks(new
		// Sol005Linkable().createVnfPackageOnboardingNotificationLinks(vnfPkgId,
		// subscriptionId));
		return obj;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final VnfPackageChangeNotification obj = VnfSubscriptionFactory.createVnfPackageChangeNotification(subscriptionId, vnfPkgId, null, null);
		// obj.setLinks(new Sol005Linkable().createNotificationLink(vnfPkgId,
		// subscriptionId));
		return obj;
	}

}
