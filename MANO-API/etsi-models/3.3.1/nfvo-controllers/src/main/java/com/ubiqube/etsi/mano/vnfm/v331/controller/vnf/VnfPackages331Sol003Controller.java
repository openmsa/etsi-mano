package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.VnfPkgInfo;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfPackages331Sol003Controller implements VnfPackages331Sol003Api {

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String artifactPath, final String vnfPkgId, final String range, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdArtifactsGet(final String vnfPkgId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdManifestGet(final String vnfPkgId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

}
