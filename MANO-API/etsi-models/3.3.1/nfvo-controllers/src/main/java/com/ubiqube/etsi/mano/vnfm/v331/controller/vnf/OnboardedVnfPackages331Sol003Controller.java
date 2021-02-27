package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_MANDATORY_FIELDS;

import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.utils.SpringUtils;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.VnfPkgInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_VNFM" })
@RestController
public class OnboardedVnfPackages331Sol003Controller implements OnboardedVnfPackages331Sol003Api {

	private final VnfPackageManagement vnfManagement;

	public OnboardedVnfPackages331Sol003Controller(final VnfPackageManagement vnfManagement) {
		super();
		this.vnfManagement = vnfManagement;
	}

	@Override
	public ResponseEntity<String> onboardedVnfPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfManagement.search(requestParams, VnfPkgInfo.class, VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNF_SEARCH_MANDATORY_FIELDS, LinksSol003::makeLinks);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(final HttpServletRequest request, final String vnfdId, final String range, @Valid final String includeSignature) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfdIdArtifactsArtifactPathGet(UUID.fromString(vnfdId), artifactPath, range);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsGet(final String vnfdId, final String range) {
		return vnfManagement.onboardedVnfPackagesVnfdIdArtifactsGet(UUID.fromString(vnfdId), range);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdGet(final String vnfdId) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.onboardedVnfPackagesVnfdIdGet(UUID.fromString(vnfdId), VnfPkgInfo.class);
		LinksSol003.makeLinks(vnfPkgInfo);
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdManifestGet(final String vnfdId, @Valid final String includeSignature) {
		return vnfManagement.onboardedGetManifestByVnfd(UUID.fromString(vnfdId), includeSignature);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(final String vnfdId, final String range) {
		return vnfManagement.onboardedVnfPackagesVnfdIdPackageContentGet(UUID.fromString(vnfdId), range);
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final String vnfdId, @Valid final String includeSignature) {
		return vnfManagement.onboardedVnfPackagesVnfdIdVnfdGet(UUID.fromString(vnfdId), includeSignature);
	}

}
