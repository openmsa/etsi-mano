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
package com.ubiqube.etsi.mano.nfvo.service.event;

import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.nfvo.service.pkg.ns.NsPackageOnboardingImpl;
import com.ubiqube.etsi.mano.nfvo.service.pkg.vnf.VnfPackageOnboardingImpl;
import com.ubiqube.etsi.mano.service.event.ActionType;

@Service
public class NfvoActionController {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoActionController.class);

	private final NfvoActions nfvoActions;

	private final NsPackageOnboardingImpl nsPackagingManager;

	private final VnfPackageOnboardingImpl vnfPackageOnboarding;

	public NfvoActionController(final NfvoActions nfvoActions, final NsPackageOnboardingImpl nsPackagingManager, final VnfPackageOnboardingImpl _vnfPackageOnboarding) {
		super();
		this.nfvoActions = nfvoActions;
		this.nsPackagingManager = nsPackagingManager;
		vnfPackageOnboarding = _vnfPackageOnboarding;
	}

	public void dispatch(final ActionType eventType, @NotNull final UUID objectId, final Map<String, Object> parameters) {
		switch (eventType) {
		case VNF_PKG_ONBOARD_FROM_URI:
			vnfPackageOnboarding.vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(objectId.toString(), (String) parameters.get("uri"));
			break;
		case VNF_PKG_ONBOARD_FROM_BYTES:
			final byte[] bytes = Base64.getDecoder().decode((String) parameters.get("data"));
			vnfPackageOnboarding.vnfPackagesVnfPkgIdPackageContentPut(objectId.toString(), bytes);
			break;
		case NSD_PKG_ONBOARD_FROM_BYTES:
			nsPackagingManager.nsOnboarding(objectId);
			break;
		case NS_INSTANTIATE:
			nfvoActions.nsInstantiate(objectId);
			break;
		case NS_TERMINATE:
			nfvoActions.nsTerminate(objectId);
			break;
		default:
			LOG.warn("Unknown event: {}", eventType);
			break;
		}
	}

}
