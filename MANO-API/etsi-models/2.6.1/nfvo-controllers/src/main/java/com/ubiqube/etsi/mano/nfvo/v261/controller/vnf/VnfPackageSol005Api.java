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

package com.ubiqube.etsi.mano.nfvo.v261.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_MANDATORY_FIELDS;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.common.v261.controller.vnf.Linkable;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageController;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.CreateVnfPkgInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.vnf.UploadVnfPkgFromUriRequest;
import com.ubiqube.etsi.mano.service.ManoSearchResponseService;
import com.ubiqube.etsi.mano.utils.SpringUtils;

import ma.glasnost.orika.MapperFacade;

/**
 * SOL005 - VNF Package Management Interface
 *
 * <p>
 * SOL005 - VNF Package Management Interface IMPORTANT: Please note that this file might be not aligned to the current version of the ETSI Group Specification it refers to and has not been approved by the ETSI NFV ISG. In case of discrepancies the published ETSI Group Specification takes precedence. Please report bugs to https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 * NOTE: Normaly we should receive object in methods but Genson seems to be on the classpath and is unable to unserialize objects. So we use a string2Object to do So. Note same problems occurred when returning object some times genson could be here and not Jackson, in this case you can use object2String.
 *
 */
@RestController
@RolesAllowed({ "ROLE_OSSBSS" })
public class VnfPackageSol005Api implements VnfPackageSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfPackageSol005Api.class);

	@Nonnull
	private final Linkable links = new Sol005Linkable();

	private final VnfPackageManagement vnfManagement;

	private final MapperFacade mapper;

	private final VnfPackageController vnfPackageController;

	private final ManoSearchResponseService searchService;

	public VnfPackageSol005Api(final VnfPackageManagement _vnfManagement, final MapperFacade _mapper, final VnfPackageController _vnfPackageController, final ManoSearchResponseService _searchService) {
		vnfManagement = _vnfManagement;
		mapper = _mapper;
		vnfPackageController = _vnfPackageController;
		searchService = _searchService;
		LOG.info("Starting VNF Package SOL005 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfPackagesGet(final Map<String, String> requestParams) {
		final String filter = requestParams.get("filter");
		final String exclude = requestParams.get("exclude_fields");
		final String fields = requestParams.get("fields");
		final List<VnfPackage> result = vnfManagement.vnfPackagesGet(filter);
		final Consumer<VnfPkgInfo> setLink = x -> x.setLinks(links.getVnfLinks(x.getId()));
		requestParams.containsKey("exclude_default");
		return searchService.search(fields, exclude, VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNF_SEARCH_MANDATORY_FIELDS, result, VnfPkgInfo.class, setLink);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfdId, final HttpServletRequest request, final String accept, final String range) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(UUID.fromString(vnfdId), artifactPath, range);
	}

	@Override
	// TODO: Same as SOL003 ?
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId, final String accept) {
		final VnfPkgInfo vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(UUID.fromString(vnfPkgId), VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgId));
		return ResponseEntity.ok(vnfPkgInfo);
	}

	@Override
	public ResponseEntity<List<ResourceRegion>> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String accept, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(UUID.fromString(vnfPkgId), range);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(UUID.fromString(vnfPkgId));
	}

	/**
	 * Create a new individual VNF package resource. 9.5.2.4
	 *
	 * The POST method creates a new individual VNF package resource.
	 *
	 */
	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesPost(final String accept, final String contentType, final CreateVnfPkgInfoRequest vnfPackagePostQuery) {
		final Map<String, String> userData = vnfPackagePostQuery.getUserDefinedData();
		final VnfPackage vnfPackage = vnfPackageController.vnfPackagesPost(userData);
		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfPackage, VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPackage.getId().toString()));
		return ResponseEntity.created(URI.create(vnfPkgInfo.getLinks().getSelf().getHref())).body(vnfPkgInfo);
	}

	/**
	 * Delete an individual VNF package.
	 *
	 * The DELETE method deletes an individual VNF Package resource.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdDelete(final String vnfPkgId) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		vnfPackageController.vnfPackagesVnfPkgIdDelete(vnfPkgUuid);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Update information about an individual VNF package.
	 *
	 * \&quot;The PATCH method updates the information of a VNF package.\&quot; \&quot;This method shall follow the provisions specified in the Tables 9.4.3.3.4-1 and 9.4.3.3.4-2 for URI query parameters, request and response data structures, and response codes.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdPatch(final String vnfPkgId, final String body, final String contentType) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		final VnfPackage vnfPackage = vnfPackageController.vnfPackagesVnfPkgIdPatch(vnfPkgUuid, body);

		final VnfPkgInfo vnfPkgInfo = mapper.map(vnfPackage, VnfPkgInfo.class);
		vnfPkgInfo.setLinks(links.getVnfLinks(vnfPkgUuid.toString()));
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	/**
	 * Upload a VNF package by providing the content of the VNF package.
	 *
	 * The PUT method uploads the content of a VNF package. This method shall follow the provisions specified in the Tables 9.4.5.3.3-1 and 9.4.5.3.3-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentPut(final String vnfPkgId, final String accept, final MultipartFile file) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);
		try {
			vnfPackageController.vnfPackagesVnfPkgIdPackageContentPut(vnfPkgUuid, file.getBytes());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.accepted().build();
	}

	/**
	 * Upload a VNF package by providing the address information of the VNF package.
	 *
	 * The POST method provides the information for the NFVO to get the content of a VNF package. This method shall follow the provisions specified in the Tables 9.4.6.3.1-1 and 9.4.6.3.1-2 for URI query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(final String accept, final String contentType, final String vnfPkgId, final UploadVnfPkgFromUriRequest contentUploadFromUriPostRequest) {
		final UUID vnfPkgUuid = UUID.fromString(vnfPkgId);

		return ResponseEntity.noContent().build();
	}

}
