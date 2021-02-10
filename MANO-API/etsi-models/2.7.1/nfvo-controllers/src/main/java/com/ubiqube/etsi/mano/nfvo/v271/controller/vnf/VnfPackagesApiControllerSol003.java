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

import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_MANDATORY_FIELDS;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.model.v271.sol003.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.utils.SpringUtils;

import ma.glasnost.orika.MapperFacade;

@javax.annotation.processing.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-24T10:38:36.740+02:00")

@Controller("VnfPackagesApiControllerVnfm271")
public class VnfPackagesApiControllerSol003 implements VnfPackagesApiSol003 {

	private final VnfPackageManagement vnfManagement;

	private final Linkable links = new Sol003Linkable();

	private final MapperFacade mapper;

	private final ManoSearchResponseService searchService;

	public VnfPackagesApiControllerSol003(final VnfPackageManagement _vnfManagement, final MapperFacade _mapper, final ManoSearchResponseService _searchService) {
		vnfManagement = _vnfManagement;
		mapper = _mapper;
		searchService = _searchService;
	}

	@Override
	public ResponseEntity<String> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(@RequestParam final Map<String, String> requestParams, final String range, @Valid final String includeSignatures) {
		final String filter = requestParams.get("filter");
		final String exclude = requestParams.get("exclude_fields");
		final String fields = requestParams.get("fields");
		final List<VnfPackage> result = vnfManagement.vnfPackagesGet(filter);
		final Consumer<VnfPkgInfo> setLink = x -> x.setLinks(links.getVnfLinks(x.getId()));
		return searchService.search(fields, exclude, VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNF_SEARCH_MANDATORY_FIELDS, result, VnfPkgInfo.class, setLink);
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
	public ResponseEntity<Void> vnfPackagesVnfPkgIdManifestGet(final String vnfPkgId, @Valid final String includeSignatures) {
		return vnfManagement.getPackageManifest(UUID.fromString(vnfPkgId), includeSignatures);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String version, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, @Valid final String includeSignatures) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString(vnfPkgId));
	}

}
