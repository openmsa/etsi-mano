package com.ubiqube.etsi.mano.controller.vnf;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.model.vnf.sol005.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageOnboardingNotificationLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfoLinks;

/**
 * This is a technical interface for creating live links from VNF common
 * package.
 *
 * @author Olivier Vignaud <ovi@uniqube.com>
 *
 */
public interface Linkable {
	/**
	 * Create link for a VNF Package.
	 *
	 * @param vnfPkgId A Vnf ID.
	 * @return The VNF Packages Links object.
	 */
	VnfPkgInfoLinks getVnfLinks(@Nonnull String _vnfPkgId);

	VnfPackageOnboardingNotificationLinks createNotificationLink(String _vnfPkgId, String _subscriptionId);

	VnfPackageOnboardingNotificationLinks createVnfPackageOnboardingNotificationLinks(String _vnfPkgId, String _subscriptionId);

	PkgmSubscriptionLinks createSubscriptionsPkgmSubscriptionLinks(String _subscriptionId);

	ApiTypesEnum getApi();
}
