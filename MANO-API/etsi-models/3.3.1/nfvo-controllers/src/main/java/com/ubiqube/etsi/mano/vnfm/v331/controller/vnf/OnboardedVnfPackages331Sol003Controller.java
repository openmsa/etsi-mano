package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.VnfPkgInfo;

@RestController
public class OnboardedVnfPackages331Sol003Controller implements OnboardedVnfPackages331Sol003Api {

	@Override
	public ResponseEntity<List<VnfPkgInfo>> onboardedVnfPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdArtifactsArtifactPathGet(final String artifactPath, final String vnfdId, final String range, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdArtifactsGet(final String vnfdId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfPkgInfo> onboardedVnfPackagesVnfdIdGet(final String vnfdId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(final String vnfdId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdPackageContentGet(final String vnfdId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdVnfdGet(final String vnfdId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

}
