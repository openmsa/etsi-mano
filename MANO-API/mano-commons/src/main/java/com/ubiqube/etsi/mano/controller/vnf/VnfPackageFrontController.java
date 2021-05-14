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

import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.VNF_SEARCH_MANDATORY_FIELDS;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.utils.SpringUtils;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfPackageFrontController {
	private final VnfPackageManagement vnfManagement;

	private final VnfPackageController vnfPackageController;

	private final MapperFacade mapper;

	public VnfPackageFrontController(final VnfPackageManagement vnfManagement, final VnfPackageController _vnfPackageController, final MapperFacade _mapper) {
		super();
		this.vnfManagement = vnfManagement;
		vnfPackageController = _vnfPackageController;
		mapper = _mapper;
	}

	public ResponseEntity<List<ResourceRegion>> getArtifact(final HttpServletRequest request, final UUID vnfPkgId, final String range, @Valid final String includeSignature) {
		final String artifactPath = SpringUtils.extractParams(request);
		return vnfManagement.vnfPackagesVnfPkgIdArtifactsArtifactPathGet(vnfPkgId, artifactPath, range);
	}

	public <U> ResponseEntity<U> findById(final UUID vnfPkgId, final Class<U> clazz, final Consumer<U> makeLinks) {
		final U vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgIdGet(vnfPkgId, clazz);
		makeLinks.accept(vnfPkgInfo);
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	public ResponseEntity<Void> getManifest(final UUID vnfPkgId, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<List<ResourceRegion>> getContent(final UUID vnfPkgId, final String range) {
		return vnfManagement.vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, range);
	}

	public ResponseEntity<Resource> getVfnd(final UUID vnfPkgId, @Valid final String includeSignature) {
		return vnfManagement.vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, includeSignature != null);
	}

	public ResponseEntity<Void> getSelectArtifacts(final HttpServletRequest request, final UUID vnfPkgId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Void> deleteById(final UUID vnfPkgId) {
		vnfPackageController.vnfPackagesVnfPkgIdDelete(vnfPkgId);
		return ResponseEntity.noContent().build();
	}

	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLinks) {
		return vnfManagement.search(requestParams, clazz, VNF_SEARCH_DEFAULT_EXCLUDE_FIELDS, VNF_SEARCH_MANDATORY_FIELDS, makeLinks);
	}

	public <U> ResponseEntity<U> create(@Valid final Map<String, String> userDefinedData, final Class<U> clazz, final Consumer<U> makeLinks, final Function<U, String> getSelfLink) {
		final VnfPackage vnfPackage = vnfPackageController.vnfPackagesPost(userDefinedData);
		final U vnfPkgInfo = mapper.map(vnfPackage, clazz);
		makeLinks.accept(vnfPkgInfo);
		final String link = getSelfLink.apply(vnfPkgInfo);
		return ResponseEntity.created(URI.create(link)).body(vnfPkgInfo);
	}

	public <U> ResponseEntity<U> getExternalArtifacts(final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> ResponseEntity<U> putExternalArtifact(@Valid final U body, final UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Void> putContent(final UUID id, final String accept, final MultipartFile file) {
		try {
			vnfPackageController.vnfPackagesVnfPkgIdPackageContentPut(id, file.getBytes(), accept);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.accepted().build();
	}

	public <U> ResponseEntity<Void> uploadFromUri(@Valid final U body, final UUID id, final String contentType) {
		final String uri = ""; // TODO
		vnfPackageController.vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(id, contentType, uri);
		return ResponseEntity.accepted().build();
	}

	public <U> ResponseEntity<U> modify(@Valid final String body, final UUID vnfPkgId, final Class<U> clazz, final Consumer<U> makeLinks) {
		final VnfPackage vnfPackage = vnfPackageController.vnfPackagesVnfPkgIdPatch(vnfPkgId, body);
		final U vnfPkgInfo = mapper.map(vnfPackage, clazz);
		makeLinks.accept(vnfPkgInfo);
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	public ResponseEntity<List<ResourceRegion>> searchArtifact(final UUID safeUUID, final String range, final String includeSignatures, final String excludeAllManoArtifacts, final String excludeAllNonManoArtifacts, final String selectNonManoArtifactSets) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> ResponseEntity<String> onboardedSearch(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLinks) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<List<ResourceRegion>> onboardedGetContentByVnfdId(final String vnfdId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Resource> onboardedGetVnfdByVnfdId(final String vnfdId, final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<List<ResourceRegion>> onboardedGetArtifact(final HttpServletRequest request, final UUID safeUUID, final String range, final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	public <U> ResponseEntity<U> onboardedFindById(final UUID safeUUID, final Class<U> clazz, final Consumer<U> makeLinks) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<List<ResourceRegion>> onboardedGetVnfdByVnfdId(final UUID safeUUID, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<Resource> onboardedGetManifestByVnfd(final UUID fromString, @Valid final String includeSignature) {
		// TODO Auto-generated method stub
		return null;
	}

}
