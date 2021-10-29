package com.ubiqube.etsi.mano.em.v351.controller.vnflcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.vnflcm.CreateVnfSnapshotInfoRequest;
import com.ubiqube.etsi.mano.em.v351.model.vnflcm.Link;
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
