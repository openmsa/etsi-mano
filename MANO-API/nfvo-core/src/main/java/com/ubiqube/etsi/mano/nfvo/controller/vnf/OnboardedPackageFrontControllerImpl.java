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
package com.ubiqube.etsi.mano.nfvo.controller.vnf;

import java.util.UUID;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.controller.MetaStreamResource;
import com.ubiqube.etsi.mano.controller.vnf.OnboardedPackageFrontController;
import com.ubiqube.etsi.mano.controller.vnf.VnfPackageManagement;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.repository.ManoResource;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfPackageService;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class OnboardedPackageFrontControllerImpl implements OnboardedPackageFrontController {
	private final VnfPackageManagement vnfManagement;

	private final VnfPackageService vnfPackageService;

	private final VnfPackageRepository vnfPackageRepository;

	public OnboardedPackageFrontControllerImpl(final VnfPackageManagement vnfManagement, final VnfPackageService vnfPackageService, final VnfPackageRepository vnfPackageRepository) {
		super();
		this.vnfManagement = vnfManagement;
		this.vnfPackageService = vnfPackageService;
		this.vnfPackageRepository = vnfPackageRepository;
	}

	@Override
	public ResponseEntity<Resource> onboardedGetContentByVnfdId(final String vnfdId, final String accept, final String includeSignature) {
		final VnfPackage vnfPkg = vnfPackageService.findByVnfdIdAndOnboardingState(UUID.fromString(vnfdId), OnboardingStateType.ONBOARDED);
		final ManoResource content = vnfManagement.onboardedVnfPackagesVnfdIdVnfdGet(vnfPkg.getId(), accept, includeSignature);
		final MetaStreamResource res = new MetaStreamResource(content);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaTypeFactory
						.getMediaType(res)
						.orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(res);
	}

	@Override
	public ResponseEntity<Resource> onboardedGetVnfdByVnfdId(final String vnfdId, final String includeSignatures) {
		final VnfPackage vnfPkg = vnfPackageService.findByVnfdIdAndOnboardingState(UUID.fromString(vnfdId), OnboardingStateType.ONBOARDED);
		final ManoResource content = vnfPackageRepository.getBinary(vnfPkg.getId(), Constants.REPOSITORY_FILENAME_VNFD);
		final MetaStreamResource res = new MetaStreamResource(content);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaTypeFactory
						.getMediaType(res)
						.orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(res);
	}

	@Override
	public ResponseEntity<Resource> onboardedGetArtifact(final HttpServletRequest request, final UUID safeUUID, final String includeSignatures) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> ResponseEntity<U> onboardedFindById(final UUID vnfPkgId, final Class<U> clazz, final Consumer<U> makeLinks) {
		final U vnfPkgInfo = vnfManagement.vnfPackagesVnfPkgVnfdIdGet(vnfPkgId, clazz);
		makeLinks.accept(vnfPkgInfo);
		return new ResponseEntity<>(vnfPkgInfo, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Resource> onboardedGetArtifactByVnfdId(final UUID vnfdId) {
		final VnfPackage vnfPkg = vnfPackageService.findByVnfdIdAndOnboardingState(vnfdId, OnboardingStateType.ONBOARDED);
		final ManoResource content = vnfPackageRepository.getBinary(vnfPkg.getId(), Constants.REPOSITORY_FILENAME_VNFD);
		final MetaStreamResource res = new MetaStreamResource(content);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaTypeFactory
						.getMediaType(res)
						.orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(res);
	}

	@Override
	public ResponseEntity<Resource> onboardedGetManifestByVnfd(final UUID vnfdId, final String includeSignature) {
		final VnfPackage vnfPkg = vnfPackageService.findByVnfdIdAndOnboardingState(vnfdId, OnboardingStateType.ONBOARDED);
		final ManoResource content = vnfPackageRepository.getBinary(vnfPkg.getId(), Constants.REPOSITORY_FILENAME_MANIFEST);
		final MetaStreamResource res = new MetaStreamResource(content);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaTypeFactory
						.getMediaType(res)
						.orElse(MediaType.APPLICATION_OCTET_STREAM))
				.body(res);
	}

	@Override
	public <U> ResponseEntity<String> onboardedSearch(final MultiValueMap<String, String> requestParams, final Class<U> class1, final Consumer<U> makeLinks) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> onboardedGetVnfdByVnfdId(final UUID safeUUID, final String accept) {
		// TODO Auto-generated method stub
		return null;
	}

}
