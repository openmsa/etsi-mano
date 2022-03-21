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
package com.ubiqube.etsi.mano.nfvo.v261;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.common.v261.VnfSubscriptionFactory261;
import com.ubiqube.etsi.mano.common.v261.model.vnf.PackageOperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.EventMessage;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol003Linkable;
import com.ubiqube.etsi.mano.nfvo.v261.services.Sol005Linkable;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.NfvoFactory;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Service
public class NfvoFactory261Nfvo implements NfvoFactory {
	private final VnfPackageRepository vnfPackageRepository;

	public NfvoFactory261Nfvo(final VnfPackageRepository vnfPackageRepository) {
		super();
		this.vnfPackageRepository = vnfPackageRepository;
	}

	@Override
	public Object createNotificationVnfPackageOnboardingNotification(final UUID subcriptionId, final EventMessage event) {
		final VnfPackage vnfPkg = vnfPackageRepository.get(event.getObjectId());
		final var obj = VnfSubscriptionFactory261.createNotificationVnfPackageOnboardingNotification(event.getId(), subcriptionId, event.getObjectId(), vnfPkg.getVnfdId(), new Sol003Linkable());
		obj.setLinks(new Sol005Linkable().createVnfPackageOnboardingNotificationLinks(vnfPkg.getId(), subcriptionId));
		return obj;
	}

	@Override
	public Object createVnfPackageChangeNotification(final UUID subscriptionId, final EventMessage event) {
		boolean deleted = false;
		try {
			vnfPackageRepository.get(event.getObjectId());
		} catch (final RuntimeException e) {
			deleted = true;
		}
		final var obj = VnfSubscriptionFactory261.createVnfPackageChangeNotification(deleted, subscriptionId, event.getId(), event.getObjectId(), event.getAdditionalParameters().get("vnfdId"),
				PackageOperationalStateType.fromValue(event.getAdditionalParameters().get("state")), new Sol003Linkable());
		obj.setLinks(new Sol005Linkable().createNotificationLink(event.getObjectId(), subscriptionId));
		return obj;
	}

}
