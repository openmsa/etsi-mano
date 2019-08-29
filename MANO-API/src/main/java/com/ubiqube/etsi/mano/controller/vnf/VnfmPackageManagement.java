package com.ubiqube.etsi.mano.controller.vnf;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.utils.RangeHeader;

/**
 * This is a VNFM Only Implementation. All queries shall goes to the NFVO
 * (REST).
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile("VNFM")
@Service
public class VnfmPackageManagement implements VnfPackageManagement {

	private static final Logger LOG = LoggerFactory.getLogger(VnfmPackageManagement.class);

	public VnfmPackageManagement() {
		LOG.info("Starting VNF Package Management in a VNFM Only mode.");
	}

	@Override
	public VnfPkgInfo vnfPackagesVnfPkgIdGet(final String vnfPkgId, final Linkable links) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String vnfPackagesGet(final Map<String, String> queryParameters, final Linkable links) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfPkgId, final String artifactPath, final RangeHeader rangeHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, final String accept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(final String _vnfPkgId, final String _range) {
		// TODO Auto-generated method stub
		return null;
	}

}
