package com.ubiqube.etsi.mano.nfvo.v261;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.common.v261.VnfSubscriptionFactory;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol003Linkable;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol005Linkable;
import com.ubiqube.etsi.mano.service.NfvoFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NfvoFactory261Nfvo implements NfvoFactory {

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final var obj = VnfSubscriptionFactory.createNotificationVnfPackageOnboardingNotification(subscriptionId, vnfPkgId, null, new Sol003Linkable());
		obj.setLinks(new Sol005Linkable().createVnfPackageOnboardingNotificationLinks(vnfPkgId, subscriptionId));
		return obj;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final UUID vnfPkgId) {
		final var obj = VnfSubscriptionFactory.createVnfPackageChangeNotification(subscriptionId, vnfPkgId, null, new Sol003Linkable());
		obj.setLinks(new Sol005Linkable().createNotificationLink(vnfPkgId, subscriptionId));
		return obj;
	}

}
