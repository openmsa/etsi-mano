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

package com.ubiqube.etsi.mano.vnfm.v261;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.service.VersionService;

@Service
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
