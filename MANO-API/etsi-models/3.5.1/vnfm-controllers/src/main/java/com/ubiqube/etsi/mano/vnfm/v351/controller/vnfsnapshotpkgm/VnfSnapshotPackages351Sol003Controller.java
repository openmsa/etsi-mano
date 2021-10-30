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
package com.ubiqube.etsi.mano.vnfm.v351.controller.vnfsnapshotpkgm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.fc.vnflcm.VnfSnapshotsFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfsnapshotpkgm.Link;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfsnapshotpkgm.VnfSnapshotPkgInfo;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfsnapshotpkgm.VnfSnapshotPkgInfoLinks;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfSnapshotPackages351Sol003Controller implements VnfSnapshotPackages351Sol003Api {

	private final VnfSnapshotsFrontController vnfSnapshotsFrontController;

	public VnfSnapshotPackages351Sol003Controller(final VnfSnapshotsFrontController vnfSnapshotsFrontController) {
		super();
		this.vnfSnapshotsFrontController = vnfSnapshotsFrontController;
	}

	@Override
	public ResponseEntity<List<VnfSnapshotPkgInfo>> vnfSnapshotPackagesGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfSnapshotsFrontController.search(requestParams, nextpageOpaqueMarker, VnfSnapshotPkgInfo.class, VnfSnapshotPackages351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<VnfSnapshotPkgInfo> vnfSnapshotPackagesVnfSnapshotPkgIdGet(final String vnfSnapshotInfoId, final String filter, final String nextpageOpaqueMarker) {
		return vnfSnapshotsFrontController.findById(vnfSnapshotInfoId, VnfSnapshotPkgInfo.class, VnfSnapshotPackages351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Resource> vnfSnapshotPackagesVnfSnapshotPkgIdPackageContentGet(final String vnfSnapshotInfoId) {
		return vnfSnapshotsFrontController.fetch(vnfSnapshotInfoId, null);
	}

	@Override
	public ResponseEntity<Resource> vnfSnapshotPackagesVnfSnapshotPkgIdArtifactsArtifactPathGet(final String vnfSnapshotPkgId, final String artifactPath) {
		return vnfSnapshotsFrontController.fetchArtifact(vnfSnapshotPkgId, artifactPath);
	}

	private static void makeLinks(final VnfSnapshotPkgInfo subscription) {
		final VnfSnapshotPkgInfoLinks links = new VnfSnapshotPkgInfoLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(VnfSnapshotPackages351Sol003Api.class).vnfSnapshotPackagesVnfSnapshotPkgIdGet(subscription.getId(), null, null)).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}
}
