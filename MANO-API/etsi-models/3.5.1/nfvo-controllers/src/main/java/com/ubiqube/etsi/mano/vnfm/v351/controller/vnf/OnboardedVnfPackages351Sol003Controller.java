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
package com.ubiqube.etsi.mano.vnfm.v351.controller.vnf;

import static com.ubiqube.etsi.mano.nfvo.fc.controller.NfvoConstants.getSafeUUID;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.VnfPackageFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.VnfPkgInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class OnboardedVnfPackages351Sol003Controller implements OnboardedVnfPackages351Sol003Api {
	private final VnfPackageFrontController vnfPackageFrontController;

	public OnboardedVnfPackages351Sol003Controller(final VnfPackageFrontController vnfPackageFrontController) {
		super();
		this.vnfPackageFrontController = vnfPackageFrontController;
	}

	@Override
	public ResponseEntity<String> onboardedVnfPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfPackageFrontController.onboardedSearch(requestParams, VnfPkgInfo.class, LinksSol003::makeLinks);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(final HttpServletRequest request, final String vnfdId, final String range, @Valid final String includeSignature) {
		return vnfPackageFrontController.onboardedGetArtifact(request, getSafeUUID(vnfdId), range, includeSignature);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsGet(final String vnfdId, final String range) {
		return vnfPackageFrontController.onboardedGetVnfdByVnfdId(getSafeUUID(vnfdId), range);
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
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(final String vnfdId, final String range) {
		return vnfPackageFrontController.onboardedGetContentByVnfdId(vnfdId, range);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final String vnfdId, @Valid final String includeSignature) {
		return vnfPackageFrontController.onboardedGetVnfdByVnfdId(vnfdId, includeSignature);
	}

}
