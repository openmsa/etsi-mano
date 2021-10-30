package com.ubiqube.etsi.mano.em.v351.controller.vnfind;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.vnfind.Link;
import com.ubiqube.etsi.mano.em.v351.model.vnfind.VnfIndicator;
import com.ubiqube.etsi.mano.em.v351.model.vnfind.VnfIndicatorLinks;
import com.ubiqube.etsi.mano.vnfm.fc.vnfind.IndicatorsFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.controller.vnflcm.VnfInstances351Sol003Api;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@ConditionalOnMissingClass("com.ubiqube.etsi.mano.em.v331.controller.vnfind.Indicators331Sol002Api")
@RestController
public class Indicators351Sol002Controller implements Indicators351Sol002Api {
	private final IndicatorsFrontController indicatorsFrontController;

	public Indicators351Sol002Controller(final IndicatorsFrontController indicatorsFrontController) {
		super();
		this.indicatorsFrontController = indicatorsFrontController;
	}

	@Override
	public ResponseEntity<List<VnfIndicator>> indicatorsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		return indicatorsFrontController.search(filter, nextpageOpaqueMarker, VnfIndicator.class, Indicators351Sol002Controller::makeLink);
	}

	@Override
	public ResponseEntity<List<VnfIndicator>> indicatorsVnfInstanceIdGet(final String vnfInstanceId, @Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		return indicatorsFrontController.findByVnfInstanceId(vnfInstanceId, filter, nextpageOpaqueMarker, VnfIndicator.class, Indicators351Sol002Controller::makeLink);
	}

	@Override
	public ResponseEntity<VnfIndicator> indicatorsVnfInstanceIdIndicatorIdGet(final String vnfInstanceId, final String indicatorId) {
		return indicatorsFrontController.findByVnfInstanceIdAndIndicatorId(vnfInstanceId, indicatorId, VnfIndicator.class, Indicators351Sol002Controller::makeLink);
	}

	private static void makeLink(final VnfIndicator x) {
		final VnfIndicatorLinks links = new VnfIndicatorLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Indicators351Sol002Api.class).indicatorsVnfInstanceIdIndicatorIdGet(x.getVnfInstanceId(), x.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();

		link.setHref(linkTo(methodOn(VnfInstances351Sol003Api.class).vnfInstancesVnfInstanceIdGet(x.getVnfInstanceId())).withSelfRel().getHref());
		links.setVnfInstance(link);

		x.setLinks(links);
	}

}
