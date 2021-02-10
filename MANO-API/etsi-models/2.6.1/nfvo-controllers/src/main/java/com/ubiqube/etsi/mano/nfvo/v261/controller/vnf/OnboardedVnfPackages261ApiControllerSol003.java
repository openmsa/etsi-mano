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
package com.ubiqube.etsi.mano.nfvo.v261.controller.vnf;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.utils.SpringUtils;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Controller
public class OnboardedVnfPackages261ApiControllerSol003 implements OnboardedVnfPackages261ApiSol003 {
	private final Linkable links = new Sol003Linkable();
	private final VnfPackageManagement vnfManagement;

	public OnboardedVnfPackages261ApiControllerSol003(final VnfPackageManagement _vnfManagement) {
		vnfManagement = _vnfManagement;
	}

	@Override
	public final ResponseEntity<List<VnfPkgInfo>> onboardedVnfPackagesGet(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, @Valid final String nextpageOpaqueMarker) {
		return ResponseEntity.ok(new ArrayList<>());
	}

	@Override
	public final ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(final HttpServletRequest request, final String vnfdId, final String range, @Valid final String includeSignatures) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfdIdArtifactsArtifactPathGet(UUID.fromString(vnfdId), artifactPath, range);
	}

	@Override
	public final ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdGet(final String vnfdId) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.onboardedVnfPackagesVnfdIdGet(UUID.fromString(vnfdId), VnfPkgInfo.class);
		setLinks(vnfPkgInfo);
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public final ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(final String vnfdId, final String range) {
		return vnfManagement.onboardedVnfPackagesVnfdIdPackageContentGet(UUID.fromString(vnfdId), range);
	}

	@Override
	public final ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final String vnfdId, @Valid final String includeSignatures) {
		return vnfManagement.onboardedVnfPackagesVnfdIdVnfdGet(UUID.fromString(vnfdId), includeSignatures);
	}

	protected void setLinks(final VnfPkgInfo vnfPkgInfo) {
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgInfo.getId()));
	}
}
