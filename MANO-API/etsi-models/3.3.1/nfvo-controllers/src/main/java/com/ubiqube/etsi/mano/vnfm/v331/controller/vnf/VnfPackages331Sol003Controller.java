package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class VnfPackages331Sol003Controller implements VnfPackages331Sol003Api {
	private final VnfPackageManagement vnfManagement;

	public VnfPackages331Sol003Controller(final VnfPackageManagement vnfManagement) {
		super();
		this.vnfManagement = vnfManagement;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final HttpServletRequest request, final String vnfPkgId, final String range, @Valid final String includeSignature) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString(vnfPkgId), artifactPath, range);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdArtifactsGet(final String vnfPkgId, final String range) {
		// TODO
		return null;
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, @Valid final String includeSignature) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(UUID.fromString(vnfPkgId), VnfPkgInfo.class);
		LinksSol003.makeLinks(vnfPkgInfo);
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdManifestGet(final String vnfPkgId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

}
