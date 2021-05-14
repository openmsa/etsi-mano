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

package com.ubiqube.etsi.mano.common.v261.controller.vnf;

import java.util.UUID;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PkgmSubscriptionLinks;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.controller.FrontApiTypesEnum;

/**
 * This is a technical interface for creating live links from VNF common package.
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
	void makeLinks(VnfPkgInfo _vnfPkgInfo);

	String getSelfLink(VnfPkgInfo _vnfPkgInfo);

	PkgmLinks createNotificationLink(@Nonnull UUID _vnfPkgId, UUID _subscriptionId);

	PkgmLinks createVnfPackageOnboardingNotificationLinks(@Nonnull UUID _vnfPkgId, UUID _subscriptionId);

	PkgmSubscriptionLinks createSubscriptionsPkgmSubscriptionLinks(@Nonnull String _subscriptionId);

	FrontApiTypesEnum getApi();
}
