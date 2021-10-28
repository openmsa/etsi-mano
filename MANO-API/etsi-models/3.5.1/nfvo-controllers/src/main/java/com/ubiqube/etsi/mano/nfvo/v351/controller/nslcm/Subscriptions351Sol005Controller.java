package com.ubiqube.etsi.mano.nfvo.v351.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.NsLcmSubscriptionsGenericFrontController;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.LccnSubscription;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.LccnSubscriptionRequest;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.Link;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class Subscriptions351Sol005Controller implements NsLcmSubscriptions351Sol005Api {
	private final NsLcmSubscriptionsGenericFrontController nsLcmSubscriptionsGenericFrontController;

	public Subscriptions351Sol005Controller(final NsLcmSubscriptionsGenericFrontController nsLcmSubscriptionsGenericFrontController) {
		super();
		this.nsLcmSubscriptionsGenericFrontController = nsLcmSubscriptionsGenericFrontController;
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(@Valid final LccnSubscriptionRequest body) {
		return nsLcmSubscriptionsGenericFrontController.create(body, LccnSubscription.class, Subscriptions351Sol005Controller::makeLink, Subscriptions351Sol005Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return nsLcmSubscriptionsGenericFrontController.delete(subscriptionId);
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return nsLcmSubscriptionsGenericFrontController.findById(subscriptionId, LccnSubscription.class, Subscriptions351Sol005Controller::makeLink);
	}

	@Override
	public ResponseEntity<List<LccnSubscription>> subscriptionsGet(final String filter, final String nextpageOpaqueMarker) {
		return nsLcmSubscriptionsGenericFrontController.search(filter, LccnSubscription.class, Subscriptions351Sol005Controller::makeLink);
	}

	private static void makeLink(final LccnSubscription lccnSubscription) {
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsLcmSubscriptions351Sol005Api.class).subscriptionsSubscriptionIdGet(lccnSubscription.getId())).withSelfRel().getHref());
		links.setSelf(self);
		lccnSubscription.setLinks(links);
	}

	private static String getSelfLink(final LccnSubscription lccnSubscription) {
		return linkTo(methodOn(NsLcmSubscriptions351Sol005Api.class).subscriptionsSubscriptionIdGet(lccnSubscription.getId())).withSelfRel().getHref();
	}

}
