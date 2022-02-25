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
package com.ubiqube.etsi.mano.nfvo.v281.controller.vnf;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ubiqube.etsi.mano.controller.vnf.VnfPackageFrontController;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.Link;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.CreateVnfPkgInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.ExternalArtifactsAccessConfig;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.UploadVnfPkgFromUriRequest;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.nfvo.v281.model.vnf.VnfPkgInfoLinks;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfPackages281Sol005Controller implements VnfPackages281Sol005Api {
	private final VnfPackageFrontController frontController;

	public VnfPackages281Sol005Controller(final VnfPackageFrontController frontController) {
		super();
		this.frontController = frontController;
	}

	@Override
	public ResponseEntity<String> vnfPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return frontController.search(requestParams, VnfPkgInfo.class, VnfPackages281Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesPost(@Valid final CreateVnfPkgInfoRequest body) {
		return frontController.create(body.getUserDefinedData(), VnfPkgInfo.class, VnfPackages281Sol005Controller::makeLinks, null);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(final String vnfPkgId, final HttpServletRequest requestParams, final String range, @Valid final String includeSignatures) {
		return frontController.getArtifact(requestParams, getSafeUUID(vnfPkgId), includeSignatures);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsGet(final String vnfPkgId, final String range, final String includeSignatures, final String excludeAllManoArtifacts, final String excludeAllNonManoArtifacts, final String includeExternalArtifacts, final String selectNonManoArtifactSets) {
		return frontController.searchArtifact(getSafeUUID(vnfPkgId), includeSignatures, excludeAllManoArtifacts, excludeAllNonManoArtifacts, selectNonManoArtifactSets);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdDelete(final String vnfPkgId) {
		return frontController.deleteById(getSafeUUID(vnfPkgId));
	}

	@Override
	public ResponseEntity<ExternalArtifactsAccessConfig> vnfPackagesVnfPkgIdExtArtifactsAccessGet(final String vnfPkgId) {
		return frontController.getExternalArtifacts(getSafeUUID(vnfPkgId));
	}

	@Override
	public ResponseEntity<ExternalArtifactsAccessConfig> vnfPackagesVnfPkgIdExtArtifactsAccessPut(@Valid final ExternalArtifactsAccessConfig body, final String vnfPkgId) {
		return frontController.putExternalArtifact(body, getSafeUUID(vnfPkgId));
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdGet(final String vnfPkgId) {
		return frontController.findById(getSafeUUID(vnfPkgId), VnfPkgInfo.class, VnfPackages281Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdManifestGet(final String vnfPkgId, @Valid final String includeSignatures) {
		return frontController.getManifest(getSafeUUID(vnfPkgId), includeSignatures);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(final String vnfPkgId, final String range) {
		return frontController.getContent(getSafeUUID(vnfPkgId));
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentPut(@Valid final MultipartFile file, final String accept, final String vnfPkgId) {
		return frontController.putContent(getSafeUUID(vnfPkgId), accept, file);
	}

	@Override
	public ResponseEntity<Void> vnfPackagesVnfPkgIdPackageContentUploadFromUriPost(@Valid final UploadVnfPkgFromUriRequest body, final String vnfPkgId, final String accept) {
		return frontController.uploadFromUri(body, getSafeUUID(vnfPkgId), accept);
	}

	@Override
	public ResponseEntity<VnfPkgInfo> vnfPackagesVnfPkgIdPatch(@Valid final String body, final String vnfPkgId, final String ifMatch) {
		return frontController.modify(body, getSafeUUID(vnfPkgId), ifMatch, VnfPkgInfo.class, VnfPackages281Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(final String vnfPkgId, @Valid final String includeSignatures) {
		return frontController.getVfnd(getSafeUUID(vnfPkgId), includeSignatures);
	}

	private static void makeLinks(final VnfPkgInfo vnfPackage) {
		final String vnfPkgId = vnfPackage.getId();
		final VnfPkgInfoLinks links = new VnfPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackages281Sol005Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId)).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackages281Sol005Api.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final Link packageContent = new Link();
		packageContent.setHref(linkTo(methodOn(VnfPackages281Sol005Api.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "")).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		vnfPackage.setLinks(links);
	}

}
