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
package com.ubiqube.etsi.mano.em.v351.controller.vnflcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.lcmcoord.Link;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.CreateVnfSnapshotInfoRequest;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.VnfSnapshotInfo;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.VnfSnapshotInfoLinks;
import com.ubiqube.etsi.mano.vnfm.fc.vnflcm.VnfSnapshotsFrontController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfSnapshots351Sol002Controller implements VnfSnapshots351Sol002Api {
	private final VnfSnapshotsFrontController vnfSnapshotsFrontController;

	public VnfSnapshots351Sol002Controller(final VnfSnapshotsFrontController vnfSnapshotsFrontController) {
		super();
		this.vnfSnapshotsFrontController = vnfSnapshotsFrontController;
	}

	@Override
	public ResponseEntity<List<VnfSnapshotInfo>> vnfSnapshotsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfSnapshotsFrontController.search(requestParams, nextpageOpaqueMarker, VnfSnapshotInfo.class, VnfSnapshots351Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<VnfSnapshotInfo> vnfSnapshotsPost(@Valid final CreateVnfSnapshotInfoRequest body) {
		return vnfSnapshotsFrontController.create(body, VnfSnapshotInfo.class, VnfSnapshots351Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<Void> vnfSnapshotsVnfSnapshotInfoIdDelete(final String vnfSnapshotInfoId) {
		return vnfSnapshotsFrontController.delete(vnfSnapshotInfoId);
	}

	@Override
	public ResponseEntity<VnfSnapshotInfo> vnfSnapshotsVnfSnapshotInfoIdGet(final String vnfSnapshotInfoId) {
		return vnfSnapshotsFrontController.findById(vnfSnapshotInfoId, VnfSnapshotInfo.class, VnfSnapshots351Sol002Controller::makeLinks);
	}

	private static void makeLinks(final VnfSnapshotInfo subscription) {
		final VnfSnapshotInfoLinks links = new VnfSnapshotInfoLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(VnfSnapshots351Sol002Api.class).vnfSnapshotsVnfSnapshotInfoIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

}
