package com.ubiqube.etsi.mano.vnfm.v261;

import java.util.UUID;

import com.ubiqube.etsi.mano.service.VersionService;

public class Vnfm261VersionService implements VersionService {

	@Override
	public String getVersion() {
		return "2.6.1";
	}

	@Override
	public boolean isNfvo() {
		return false;
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		throw new IllegalArgumentException("No Package notification on VNFM.");
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		throw new IllegalArgumentException("No Package notification on VNFM.");
	}

}
