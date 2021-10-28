package com.ubiqube.etsi.mano.vnfm.v351.controller.vnfpm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.fc.vnfpm.VnfmThresholdFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.CreateThresholdRequest;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.Link;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.Threshold;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.ThresholdLinks;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfpm.ThresholdModifications;

@RestController
public class Thresholds351Sol003Controller implements Thresholds351Sol003Api {

	private final VnfmThresholdFrontController vnfmThresholdFrontController;

	public Thresholds351Sol003Controller(final VnfmThresholdFrontController vnfmThresholdFrontController) {
		this.vnfmThresholdFrontController = vnfmThresholdFrontController;
	}

	@Override
	public ResponseEntity<Threshold> thresholdsPost(@Valid final CreateThresholdRequest createThresholdRequest) {
		return vnfmThresholdFrontController.thresholdsCreate(createThresholdRequest, Threshold.class, Thresholds351Sol003Controller::makeLinks, Thresholds351Sol003Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> thresholdsThresholdIdDelete(final String thresholdId) {
		return vnfmThresholdFrontController.deleteById(thresholdId);
	}

	@Override
	public ResponseEntity<Threshold> thresholdsThresholdIdGet(final String thresholdId) {
		return vnfmThresholdFrontController.findById(thresholdId, Threshold.class, Thresholds351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<String> thresholdsGet(final MultiValueMap<String, String> requestParams, final String nextpageOpaqueMarker) {
		return vnfmThresholdFrontController.search(requestParams, nextpageOpaqueMarker, Threshold.class, Thresholds351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<ThresholdModifications> thresholdsThresholdIdPatch(final String thresholdId, final ThresholdModifications body) {
		return vnfmThresholdFrontController.patch(thresholdId, body, ThresholdModifications.class);
	}

	private static void makeLinks(final Threshold x) {
		final ThresholdLinks links = new ThresholdLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Thresholds351Sol003Api.class).thresholdsThresholdIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref("");
		// links.setObjects(link);

		x.setLinks(links);
	}

	private static String getSelfLink(final Threshold threshold) {
		return linkTo(methodOn(Thresholds351Sol003Api.class).thresholdsThresholdIdGet(threshold.getId().toString())).withSelfRel().getHref();
	}

}
