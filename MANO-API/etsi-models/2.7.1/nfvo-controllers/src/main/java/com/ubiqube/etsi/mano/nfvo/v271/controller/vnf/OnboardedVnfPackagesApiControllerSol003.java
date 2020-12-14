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
package com.ubiqube.etsi.mano.nfvo.v271.controller.vnf;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.VnfPkgInfo;

@javax.annotation.processing.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-24T10:38:36.740+02:00")

@Controller("OnboardedVnfPackagesApiController271")
public class OnboardedVnfPackagesApiControllerSol003 implements OnboardedVnfPackagesApiSol003 {

	private VnfPackageManagement vnfManagement;
	private final Linkable links = new Sol003Linkable();

	@Override
	public ResponseEntity<List<VnfPkgInfo>> onboardedVnfPackagesGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		return OnboardedVnfPackagesApiSol003.super.onboardedVnfPackagesGet(filter, allFields, fields, excludeFields, excludeDefault, nextpageOpaqueMarker);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(final String artifactPath, final String vnfdId, final String range, @Valid final String includeSignatures) {
		return vnfManagement.vnfPackagesVnfdIdArtifactsArtifactPathGet(UUID.fromString(vnfdId), artifactPath, range);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdArtifactsGet(final String vnfdId, final HttpServletRequest request, final String range) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(UUID.fromString(vnfdId), VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgInfo.getId()));
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdGet(final String vnfdId) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(UUID.fromString(vnfdId), VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgInfo.getId()));
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(final String vnfdId, @Valid final String includeSignatures) {
		return vnfManagement.onboardedVnfPackagesVnfdIdManifestGet(UUID.fromString(vnfdId), includeSignatures);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(final String vnfdId, final String range) {
		return vnfManagement.onboardedVnfPackagesVnfdIdPackageContentGet(UUID.fromString(vnfdId), range);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final String vnfdId, @Valid final String includeSignatures) {
		return vnfManagement.onboardedVnfPackagesVnfdIdVnfdGet(UUID.fromString(vnfdId), includeSignatures);
	}

}
