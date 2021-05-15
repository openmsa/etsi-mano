package com.ubiqube.etsi.mano.em.v271.controller.vnfind;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.vnfind.VnfIndSubscriptionsFrontController;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.Link;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.VnfIndicatorSubscription;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.VnfIndicatorSubscriptionLinks;
import com.ubiqube.etsi.mano.em.v271.model.vnfind.VnfIndicatorSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class IndicatorsSubscriptions271Sol002Controller implements IndicatorsSubscriptions271Sol002Api {
	private final VnfIndSubscriptionsFrontController vnfIndSubscriptionsFrontController;

	public IndicatorsSubscriptions271Sol002Controller(final VnfIndSubscriptionsFrontController vnfIndSubscriptionsFrontController) {
		super();
		this.vnfIndSubscriptionsFrontController = vnfIndSubscriptionsFrontController;
	}

	@Override
	public ResponseEntity<List<VnfIndicatorSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return vnfIndSubscriptionsFrontController.search(requestParams, VnfIndicatorSubscription.class, IndicatorsSubscriptions271Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsPost(@Valid final VnfIndicatorSubscriptionRequest vnfIndicatorSubscriptionRequest) {
		return vnfIndSubscriptionsFrontController.create(vnfIndicatorSubscriptionRequest, VnfIndicatorSubscription.class, IndicatorsSubscriptions271Sol002Controller::makeLinks, IndicatorsSubscriptions271Sol002Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return vnfIndSubscriptionsFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return vnfIndSubscriptionsFrontController.findById(subscriptionId, VnfIndicatorSubscription.class, IndicatorsSubscriptions271Sol002Controller::makeLinks);
	}

	private static void makeLinks(final VnfIndicatorSubscription subscription) {
		final VnfIndicatorSubscriptionLinks links = new VnfIndicatorSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(IndicatorsSubscriptions271Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

	private static String getSelfLink(final VnfIndicatorSubscription subscription) {
		return linkTo(methodOn(IndicatorsSubscriptions271Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}

}
