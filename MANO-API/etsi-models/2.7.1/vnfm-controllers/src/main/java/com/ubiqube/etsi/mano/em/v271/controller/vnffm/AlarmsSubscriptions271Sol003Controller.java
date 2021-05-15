package com.ubiqube.etsi.mano.em.v271.controller.vnffm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.vnffm.FaultMngtSubscriptionsFrontController;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.FmSubscription;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.FmSubscriptionLinks;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.FmSubscriptionRequest;
import com.ubiqube.etsi.mano.em.v271.model.vnffm.Link;

@Controller
public class AlarmsSubscriptions271Sol003Controller implements AlarmsSubscriptions271Sol002Api {
	private final FaultMngtSubscriptionsFrontController faultMngtSubscriptionsFrontController;

	public AlarmsSubscriptions271Sol003Controller(final FaultMngtSubscriptionsFrontController faultMngtSubscriptionsFrontController) {
		super();
		this.faultMngtSubscriptionsFrontController = faultMngtSubscriptionsFrontController;
	}

	@Override
	public ResponseEntity<List<FmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return faultMngtSubscriptionsFrontController.search(requestParams, FmSubscription.class, AlarmsSubscriptions271Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsPost(@Valid final FmSubscriptionRequest fmSubscriptionRequest) {
		return faultMngtSubscriptionsFrontController.create(fmSubscriptionRequest, FmSubscription.class, AlarmsSubscriptions271Sol003Controller::makeLinks, AlarmsSubscriptions271Sol003Controller::makeSelf);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return faultMngtSubscriptionsFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return faultMngtSubscriptionsFrontController.findById(subscriptionId, FmSubscription.class, AlarmsSubscriptions271Sol003Controller::makeLinks);
	}

	private static void makeLinks(final FmSubscription subscription) {
		final FmSubscriptionLinks links = new FmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(AlarmsSubscriptions271Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

	private static String makeSelf(final FmSubscription subscription) {
		return linkTo(methodOn(AlarmsSubscriptions271Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}
}
