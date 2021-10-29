package com.ubiqube.etsi.mano.em.v351.controller.vnffm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.em.v351.model.vnffm.FmSubscription;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.FmSubscriptionLinks;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.FmSubscriptionRequest;
import com.ubiqube.etsi.mano.em.v351.model.vnffm.Link;
import com.ubiqube.etsi.mano.vnfm.fc.vnffm.FaultMngtSubscriptionsFrontController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class AlarmsSubscriptions351Sol002Controller implements AlarmsSubscriptions351Sol002Api {
	private final FaultMngtSubscriptionsFrontController faultMngtSubscriptionsFrontController;

	public AlarmsSubscriptions351Sol002Controller(final FaultMngtSubscriptionsFrontController faultMngtSubscriptionsFrontController) {
		super();
		this.faultMngtSubscriptionsFrontController = faultMngtSubscriptionsFrontController;
	}

	@Override
	public ResponseEntity<List<FmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return faultMngtSubscriptionsFrontController.search(requestParams, FmSubscription.class, AlarmsSubscriptions351Sol002Controller::makeLinks);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsPost(@Valid final FmSubscriptionRequest fmSubscriptionRequest) {
		return faultMngtSubscriptionsFrontController.create(fmSubscriptionRequest, FmSubscription.class, AlarmsSubscriptions351Sol002Controller::makeLinks, AlarmsSubscriptions351Sol002Controller::makeSelf);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return faultMngtSubscriptionsFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return faultMngtSubscriptionsFrontController.findById(subscriptionId, FmSubscription.class, AlarmsSubscriptions351Sol002Controller::makeLinks);
	}

	private static void makeLinks(final FmSubscription subscription) {
		final FmSubscriptionLinks links = new FmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(AlarmsSubscriptions351Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

	private static String makeSelf(final FmSubscription subscription) {
		return linkTo(methodOn(AlarmsSubscriptions351Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}

}
