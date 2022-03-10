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
package com.ubiqube.etsi.mano.nfvo.v331.controller.vnfsnapshotpkgm;

import static com.ubiqube.etsi.mano.nfvo.fc.controller.NfvoConstants.getSafeUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nssp.VnfSnapshotPackagesFrontController;
import com.ubiqube.etsi.mano.em.v331.model.vnflcm.Link;
import com.ubiqube.etsi.mano.nfvo.v331.controller.vnf.VnfPackages331Sol005Api;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.BuildVnfSnapshotPkgRequest;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.CancelVnfSnapshotPkgOperationRequest;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.CreateVnfSnapshotPkgInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.ExtractVnfSnapshotPkgRequest;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.UploadVnfSnapshotPkgFromUriRequest;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.VnfSnapshotPkgInfo;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.VnfSnapshotPkgInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v331.model.vnfsnapshotpkgm.VnfSnapshotPkgInfoModifications;

@RestController
public class VnfSnapshotPackages331Sol005Controller implements VnfSnapshotPackages331Sol005Api {
	private final VnfSnapshotPackagesFrontController frontController;

	public VnfSnapshotPackages331Sol005Controller(final VnfSnapshotPackagesFrontController vnfSnapshotPackagesFrontController) {
		super();
		this.frontController = vnfSnapshotPackagesFrontController;
	}

	@Override
	public ResponseEntity<String> vnfSnapshotPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return frontController.search(requestParams, VnfSnapshotPkgInfo.class, VnfSnapshotPackages331Sol005Controller::makeLinks);
	}

	@Override
	public ResponseEntity<VnfSnapshotPkgInfo> vnfSnapshotPackagesPost(@Valid final CreateVnfSnapshotPkgInfoRequest body) {
		return frontController.create(body);
	}

	@Override
	public ResponseEntity<Resource> vnfSnapshotPackagesVnfSnapshotPkgIdArtifactsArtifactPathGet(final String vnfSnapshotPkgId, final String artifactPath, final String range) {
		return frontController.getArtifact(getSafeUUID(vnfSnapshotPkgId), artifactPath, range);
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotPackagesVnfSnapshotPkgIdDelete(final String vnfSnapshotPkgId) {
		return frontController.delete(getSafeUUID(vnfSnapshotPkgId));
	}

	@Override
	public ResponseEntity<String> vnfSnapshotPackagesVnfSnapshotPkgIdExtArtifactsAccessGet(final String vnfSnapshotPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> vnfSnapshotPackagesVnfSnapshotPkgIdExtArtifactsAccessPut(final String vnfSnapshotPkgId, @Valid final String body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfSnapshotPkgInfo> vnfSnapshotPackagesVnfSnapshotPkgIdGet(final String vnfSnapshotPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentBuildPost(final String vnfSnapshotPkgId, @Valid final BuildVnfSnapshotPkgRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentCancelPost(final String vnfSnapshotPkgId, @Valid final CancelVnfSnapshotPkgOperationRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentExtractPost(final String vnfSnapshotPkgId, @Valid final ExtractVnfSnapshotPkgRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Resource> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentGet(final String vnfSnapshotPkgId, final String range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentPut(final String vnfSnapshotPkgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentUploadFromUriPost(final String vnfSnapshotPkgId, @Valid final UploadVnfSnapshotPkgFromUriRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VnfSnapshotPkgInfoModifications> vnfSnapshotPackagesVnfSnapshotPkgIdPatch(final String vnfSnapshotPkgId, @Valid final VnfSnapshotPkgInfoModifications body) {
		// TODO Auto-generated method stub
		return null;
	}

	private static void makeLinks(final VnfSnapshotPkgInfo vnfPackage) {
		final String vnfPkgId = vnfPackage.getId();
		final VnfSnapshotPkgInfoLinks links = new VnfSnapshotPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackages331Sol005Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId)).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackages331Sol005Api.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setPackageContent(vnfd);

		vnfPackage.setLinks(links);
	}
}
