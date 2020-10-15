package com.ubiqube.etsi.mano.service;

import java.util.UUID;

public interface VersionService {

	String getVersion();

	boolean isNfvo();

	Object createNotificationVnfPackageOnboardingNotification(UUID subscriptionId, UUID vnfPkgId);

	Object createVnfPackageChangeNotification(UUID subscriptionId, UUID vnfPkgId);
}
