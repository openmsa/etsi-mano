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
package com.ubiqube.etsi.mano.controller.vnf;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.service.SearchableService;
import com.ubiqube.etsi.mano.service.rest.NfvoRest;

/**
 * This is a VNFM Only Implementation. All queries shall go to the NFVO (REST).
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@ConditionalOnMissingBean(VnfPackageManagement.class)
@Service
public class VnfmPackageManagement extends SearchableService implements VnfPackageManagement {

	private static final Logger LOG = LoggerFactory.getLogger(VnfmPackageManagement.class);
	private final NfvoRest nfvoRest;

	public VnfmPackageManagement(final NfvoRest _nfvoRest, final EntityManager _em, final ManoSearchResponseService searchService) {
		super(searchService, _em, VnfPackage.class);
		LOG.info("Starting VNF Package Management in a VNFM Only mode.");
		nfvoRest = _nfvoRest;
	}

	@Override
	public <U> U vnfPackagesVnfPkgIdGet(final UUID vnfPkgId, final Class<U> u) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("vnfPkgId", vnfPkgId);
		final URI uri = nfvoRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages/{vnfPkgId}")
				.buildAndExpand(uriVariables)
				.toUri();
		return nfvoRest.get(uri, u);
	}

	@Override
	public List<VnfPackage> vnfPackagesGet(final String filter) {
		// XXX We have to change interface protocol.
		final UriComponentsBuilder builder = nfvoRest.uriBuilder()
				.pathSegment("vnfpkgm/v1/vnf_packages");
		/*
		 * for (final Entry<String, String> entry : queryParameters.entrySet()) { builder.queryParam(entry.getKey(), entry.getValue()); }
		 */
		final URI uri = builder
				.build()
				.toUri();
		return new ArrayList<>();
		// return nfvoRest.get(uri, String.class);
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
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final UUID _vnfPkgId, final String _range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> getPackageManifest(final UUID fromString, @Valid final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final UUID vnfPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfdIdArtifactsArtifactPathGet(final UUID fromString, final String artifactPath, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> onboardedVnfPackagesVnfdIdManifestGet(final UUID vnfdId, @Valid final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdPackageContentGet(final UUID vnfdId, final String range) {
		if (null == range) {
			final Map<String, Object> uriVariables = new HashMap<>();
			uriVariables.put("vnfdId", vnfdId);
			final URI uri = nfvoRest.uriBuilder()
					.pathSegment("/vnfpkgm/v1/onboarded_vnf_packages/{vnfdId}/package_content")
					.buildAndExpand(uriVariables)
					.toUri();
			return nfvoRest.get(uri, ResponseEntity.class);
		}
		return null;
	}

	@Override
	public ResponseEntity<Resource> onboardedVnfPackagesVnfdIdVnfdGet(final UUID vnfdId, @Valid final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> U onboardedVnfPackagesVnfdIdGet(final UUID vnfdId, final Class<U> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> onboardedGetManifestByVnfd(final UUID vnfdId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> onboardedVnfPackagesVnfdIdArtifactsGet(final UUID vnfdId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

}
