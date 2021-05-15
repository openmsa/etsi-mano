package com.ubiqube.etsi.mano.em.v271.controller.vnfind;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mano.controller.vnfind.IndicatorsFrontController;
import com.ubiqube.etsi.mano.em.v271.controller.vnflcm.VnfInstances271Sol002Api;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.Link;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.VnfIndicator;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.VnfIndicatorLinks;

@Controller
public class Indicators271Sol002Controller implements Indicators271Sol002Api {
	private final IndicatorsFrontController indicatorsFrontController;

	public Indicators271Sol002Controller(final IndicatorsFrontController indicatorsFrontController) {
		super();
		this.indicatorsFrontController = indicatorsFrontController;
	}

	@Override
	public ResponseEntity<List<VnfIndicator>> indicatorsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		return indicatorsFrontController.search(filter, nextpageOpaqueMarker, VnfIndicator.class, Indicators271Sol002Controller::makeLink);
	}

	@Override
	public ResponseEntity<List<VnfIndicator>> indicatorsVnfInstanceIdGet(final String vnfInstanceId, @Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		return indicatorsFrontController.findByVnfInstanceId(vnfInstanceId, filter, nextpageOpaqueMarker, VnfIndicator.class, Indicators271Sol002Controller::makeLink);
	}

	@Override
	public ResponseEntity<VnfIndicator> indicatorsVnfInstanceIdIndicatorIdGet(final String vnfInstanceId, final String indicatorId) {
		return indicatorsFrontController.findByVnfInstanceIdAndIndicatorId(vnfInstanceId, indicatorId, VnfIndicator.class, Indicators271Sol002Controller::makeLink);
	}

	private static void makeLink(final VnfIndicator x) {
		final VnfIndicatorLinks links = new VnfIndicatorLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Indicators271Sol002Api.class).indicatorsVnfInstanceIdIndicatorIdGet(x.getVnfInstanceId(), x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();

		link.setHref(linkTo(methodOn(VnfInstances271Sol002Api.class).vnfInstancesVnfInstanceIdGet(x.getVnfInstanceId())).withSelfRel().getHref());
		links.setVnfInstance(link);

		x.setLinks(links);
	}

}
