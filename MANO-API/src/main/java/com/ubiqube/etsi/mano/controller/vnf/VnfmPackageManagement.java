package com.ubiqube.etsi.mano.controller.vnf;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.service.rest.NfvoRest;

/**
 * This is a VNFM Only Implementation. All queries shall go to the NFVO (REST).
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile("VNFM")
@Service
public class VnfmPackageManagement implements VnfPackageManagement {

	private static final Logger LOG = LoggerFactory.getLogger(VnfmPackageManagement.class);
	private final NfvoRest nfvoRest;

	public VnfmPackageManagement(final NfvoRest _nfvoRest) {
		LOG.info("Starting VNF Package Management in a VNFM Only mode.");
		nfvoRest = _nfvoRest;
	}

	@Override
	public VnfPkgInfo vnfPackagesVnfPkgIdGet(final UUID vnfPkgId, final Linkable links) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("vnfPkgId", vnfPkgId);
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages/{vnfPkgId}")
				.buildAndExpand(uriVariables)
				.toUri();
		return nfvoRest.get(uri, VnfPkgInfo.class);
	}

	@Override
	public String vnfPackagesGet(final Map<String, String> queryParameters, final Linkable links) {
		final UriComponentsBuilder builder = nfvoRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages");
		for (final Entry<String, String> entry : queryParameters.entrySet()) {
			builder.queryParam(entry.getKey(), entry.getValue());
		}
		final URI uri = builder
				.build()
				.toUri();
		return nfvoRest.get(uri, String.class);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final UUID vnfPkgId, final String artifactPath, final String rangeHeader) {
		LOG.error("artifact = {}", artifactPath);
		if (null == rangeHeader) {
			final Map<String, Object> uriVariables = new HashMap<>();
			uriVariables.put("vnfPkgId", vnfPkgId);
			uriVariables.put("artifactPath", artifactPath);
			final URI uri = nfvoRest.uriBuilder()
					.pathSegment("vnfpkgm/v1/vnf_packages/{vnfPkgId}/artifacts/{artifactPath}")
					.buildAndExpand(uriVariables)
					.toUri();
			return nfvoRest.get(uri, ResponseEntity.class);
		}
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final UUID vnfPkgId, final String accept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final UUID _vnfPkgId, final String _range) {
		// TODO Auto-generated method stub
		return null;
	}

}
