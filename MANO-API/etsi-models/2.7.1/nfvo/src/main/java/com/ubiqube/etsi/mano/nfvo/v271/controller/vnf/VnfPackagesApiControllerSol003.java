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
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.utils.SpringUtils;

import ma.glasnost.orika.MapperFacade;

@javax.annotation.processing.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-24T10:38:36.740+02:00")

@Controller("VnfPackagesApiControllerVnfm271")
public class VnfPackagesApiControllerSol003 implements VnfPackagesApiSol003 {

	private final VnfPackageManagement vnfManagement;
	private final Linkable links = new Sol003Linkable();
	private final MapperFacade mapper;

	public VnfPackagesApiControllerSol003(final VnfPackageManagement _vnfManagement, final MapperFacade _mapper) {
		vnfManagement = _vnfManagement;
		mapper = _mapper;
	}

	@Override
	public ResponseEntity<String> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@RequestParam final Map<String, String> requestParams, final String range, @Valid final String includeSignatures) {
		final String filter = requestParams.get("filter");
		final List<VnfPackage> vnfPackageInfos = vnfManagement.vnfPackagesGet(filter);
		final List<VnfPkgInfo> vnfPkginfos = vnfPackageInfos.stream()
				.map(x -> mapper.map(x, VnfPkgInfo.class))
				.collect(Collectors.toList());

		vnfPkginfos.forEach(x -> x.setLinks(links.getVnfLinks(x.getId())));

		final String exclude = requestParams.get("exclude_fields");
		final String fields = requestParams.get("fields");

		final ObjectMapper mapperForQuery = MapperForView.getMapperForView(exclude, fields, null, null);

		String resp = null;
		try {
			resp = mapperForQuery.writeValueAsString(vnfPkginfos);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.ok(resp);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsGet(final String vnfPkgId, final HttpServletRequest request, final String range) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString(vnfPkgId), artifactPath, range);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, final String version, @Valid final String includeSignature) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(UUID.fromString(vnfPkgId), VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdManifestGet(final String vnfPkgId, final String accept, final String version, final String authorization, @Valid final String includeSignatures) {
		return vnfManagement.getPackageManifest(UUID.fromString(vnfPkgId), includeSignatures);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String version, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, final String accept, final String version, @Valid final String includeSignatures) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString(vnfPkgId), accept);
	}

}
