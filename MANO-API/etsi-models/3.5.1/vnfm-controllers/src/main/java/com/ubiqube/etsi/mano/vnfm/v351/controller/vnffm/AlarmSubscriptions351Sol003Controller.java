package com.ubiqube.etsi.mano.vnfm.v351.controller.vnffm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.fc.vnffm.FaultMngtSubscriptionsFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.FmSubscription;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.FmSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.FmSubscriptionRequest;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.Link;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class AlarmSubscriptions351Sol003Controller implements AlarmSubscriptions351Sol003Api {
	private final FaultMngtSubscriptionsFrontController faultMngtSubscriptionsFrontController;

	public AlarmSubscriptions351Sol003Controller(final FaultMngtSubscriptionsFrontController faultMngtSubscriptionsFrontController) {
		super();
		this.faultMngtSubscriptionsFrontController = faultMngtSubscriptionsFrontController;
	}

	@Override
	public ResponseEntity<List<FmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return faultMngtSubscriptionsFrontController.search(requestParams, FmSubscription.class, AlarmSubscriptions351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsPost(@Valid final FmSubscriptionRequest fmSubscriptionRequest) {
		return faultMngtSubscriptionsFrontController.create(fmSubscriptionRequest, FmSubscription.class, AlarmSubscriptions351Sol003Controller::makeLinks, AlarmSubscriptions351Sol003Controller::makeSelf);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return faultMngtSubscriptionsFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<FmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return faultMngtSubscriptionsFrontController.findById(subscriptionId, FmSubscription.class, AlarmSubscriptions351Sol003Controller::makeLinks);
	}

	private static void makeLinks(final FmSubscription subscription) {
		final FmSubscriptionLinks links = new FmSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(AlarmSubscriptions351Sol003Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}

	private static String makeSelf(final FmSubscription subscription) {
		return linkTo(methodOn(AlarmSubscriptions351Sol003Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}

}
