/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.nfvo.v271.services;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.controller.FrontApiTypesEnum;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.VnfPkgInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.PkgmLinks;

public interface Linkable {
	VnfPkgInfoLinks getVnfLinks(@Nonnull String vnfPkgId);

	PkgmSubscriptionLinks createSubscriptionsPkgmSubscriptionLinks(@Nonnull String subscriptionId);

	FrontApiTypesEnum getApi();

	void makeLinks(final VnfPkgInfo vnfPkgInfo);

	String getSelfLink(final VnfPkgInfo vnfPkgInfo);

	PkgmLinks createNotificationLink(UUID vnfPkgId, UUID subscriptionId);

	PkgmLinks createVnfPackageOnboardingNotificationLinks(UUID vnfPkgId, UUID subscriptionId);
}
