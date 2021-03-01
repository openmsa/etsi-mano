package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnf.VnfPackageFrontController;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.VnfPkgInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_VNFM" })
@RestController
public class VnfPackages331Sol003Controller implements VnfPackages331Sol003Api {

	private final VnfPackageFrontController frontController;

	public VnfPackages331Sol003Controller(final VnfPackageFrontController frontController) {
		super();
		this.frontController = frontController;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final HttpServletRequest request, final String vnfPkgId, final String range, @Valid final String includeSignature) {
		return frontController.getArtifact(request, getSafeUUID(vnfPkgId), range, includeSignature);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdArtifactsGet(@Nonnull final HttpServletRequest request, final String vnfPkgId, final String range) {
		return frontController.getSelectArtifacts(request, getSafeUUID(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, @Valid final String includeSignature) {
		return frontController.findById(getSafeUUID(vnfPkgId), VnfPkgInfo.class, LinksSol003::makeLinks);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdManifestGet(final String vnfPkgId, @Valid final String includeSignature) {
		return frontController.getManifest(getSafeUUID(vnfPkgId), includeSignature);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String range) {
		return frontController.getContent(getSafeUUID(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, @Valid final String includeSignature) {
		return frontController.getVfnd(getSafeUUID(vnfPkgId), includeSignature);
	}

}
