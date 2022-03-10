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
package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import static com.ubiqube.etsi.mano.nfvo.fc.controller.NfvoConstants.getSafeUUID;

import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.annotation.Conditional;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.SingleControllerCondition;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageFrontController;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.vnfm.v331.service.LinksSol003;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_VNFM" })
@RestController
@Conditional(SingleControllerCondition.class)
public class OnboardedVnfPackages331Sol003Controller implements OnboardedVnfPackages331Sol003Api {
	private final VnfPackageFrontController vnfPackageFrontController;

	public OnboardedVnfPackages331Sol003Controller(final VnfPackageFrontController vnfPackageFrontController) {
		super();
		this.vnfPackageFrontController = vnfPackageFrontController;
	}

	@Override
	public ResponseEntity<String> onboardedVnfPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfPackageFrontController.onboardedSearch(requestParams, VnfPkgInfo.class, LinksSol003::makeLinks);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(final HttpServletRequest request, final String vnfdId, final String range, @Valid final String includeSignature) {
		return vnfPackageFrontController.onboardedGetArtifact(request, getSafeUUID(vnfdId), includeSignature);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdArtifactsGet(final String vnfdId, final String range) {
		return vnfPackageFrontController.onboardedGetVnfdByVnfdId(getSafeUUID(vnfdId));
	}

	@Override
	public ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdGet(final String vnfdId) {
		return vnfPackageFrontController.onboardedFindById(getSafeUUID(vnfdId), VnfPkgInfo.class, LinksSol003::makeLinks);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdManifestGet(final String vnfdId, @Valid final String includeSignature) {
		return vnfPackageFrontController.onboardedGetManifestByVnfd(UUID.fromString(vnfdId), includeSignature);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdPackageContentGet(final String vnfdId, final String range) {
		return vnfPackageFrontController.onboardedGetContentByVnfdId(vnfdId);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final String vnfdId, @Valid final String includeSignature) {
		return vnfPackageFrontController.onboardedGetVnfdByVnfdId(vnfdId, includeSignature);
	}

}
