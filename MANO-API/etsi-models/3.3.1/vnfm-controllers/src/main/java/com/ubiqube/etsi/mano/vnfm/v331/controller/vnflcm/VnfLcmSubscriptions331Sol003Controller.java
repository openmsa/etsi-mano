package com.ubiqube.etsi.mano.vnfm.v331.controller.vnflcm;

import static com.ubiqube.etsi.mano.Constants.getSafeUUID;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.VnfLcmSubscriptionFrontController;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.LccnSubscription;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.LccnSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.LccnSubscriptionRequest;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnflcm.Link;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RolesAllowed({ "ROLE_NFVO" })
@RestController
public class VnfLcmSubscriptions331Sol003Controller implements VnfLcmSubscriptions331Sol003Api {
	private final VnfLcmSubscriptionFrontController frontController;

	public VnfLcmSubscriptions331Sol003Controller(final VnfLcmSubscriptionFrontController frontController) {
		super();
		this.frontController = frontController;
	}

	@Override
	public ResponseEntity<List<LccnSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return frontController.search(requestParams, nextpageOpaqueMarker, LccnSubscription.class, VnfLcmSubscriptions331Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(@Valid final LccnSubscriptionRequest body) {
		return frontController.create(body, LccnSubscription.class, VnfLcmSubscriptions331Sol003Controller::makeLinks, VnfLcmSubscriptions331Sol003Controller::getSelfLink);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		return frontController.deleteById(getSafeUUID(subscriptionId));
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		return frontController.findById(getSafeUUID(subscriptionId), LccnSubscription.class, VnfLcmSubscriptions331Sol003Controller::makeLinks);
	}

	private static String getSelfLink(final LccnSubscription subscription) {
		return linkTo(methodOn(VnfLcmSubscriptions331Sol003Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref();
	}

	private static void makeLinks(final LccnSubscription subs) {
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmSubscriptions331Sol003Api.class).subscriptionsSubscriptionIdGet(subs.getId())).withSelfRel().getHref());
		links.setSelf(self);

		subs.setLinks(links);
	}

}
