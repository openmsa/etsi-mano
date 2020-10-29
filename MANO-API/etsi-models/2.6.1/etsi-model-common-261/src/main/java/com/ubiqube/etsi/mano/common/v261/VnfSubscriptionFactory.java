/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.common.v261;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PackageChangeType;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageOnboardingNotification;

public class VnfSubscriptionFactory {
	@Nonnull
	public static VnfPackageChangeNotification createVnfPackageChangeNotification(final UUID _subscriptionId, @Nonnull final UUID _vnfPkgId, final String _vnfdId, final Linkable links) {
		final VnfPackageChangeNotification ret = new VnfPackageChangeNotification();
		ret.setChangeType(PackageChangeType.OP_STATE_CHANGE);
		ret.setNotificationType("VnfPackageChangeNotification");
		ret.setOperationalState(PackageOperationalStateType.DISABLED);
		ret.setSubscriptionId(_subscriptionId.toString());
		ret.setTimeStamp(OffsetDateTime.now());
		ret.setVnfdId(_vnfdId);
		ret.setVnfPkgId(_vnfPkgId.toString());
		ret.setLinks(links.createNotificationLink(_vnfPkgId, _subscriptionId));
		return ret;
	}

	@Nonnull
	public static VnfPackageOnboardingNotification createNotificationVnfPackageOnboardingNotification(final UUID _subscriptionId, @Nonnull final UUID _vnfPkgId, final String _vnfdId, final Linkable links) {
		final VnfPackageOnboardingNotification ret = new VnfPackageOnboardingNotification();
		ret.setTimeStamp(OffsetDateTime.now());
		ret.setNotificationType("VnfPackageOnboardingNotification");
		ret.setSubscriptionId(_subscriptionId.toString());
		ret.setVnfPkgId(_vnfPkgId.toString());
		ret.setVnfdId(_vnfdId);
		ret.setLinks(links.createVnfPackageOnboardingNotificationLinks(_vnfPkgId, _subscriptionId));
		return ret;
	}
}
