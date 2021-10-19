package com.ubiqube.etsi.mano.service;

import java.util.UUID;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface NfvoFactory {

	Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId);

	Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId);
}
