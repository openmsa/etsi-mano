package com.ubiqube.etsi.mano.vnfm.v331.controller.vnflcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionServiceV2;
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
	private final SubscriptionServiceV2 subscriptionService;

	@Override
	public ResponseEntity<List<LccnSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		final List<LccnSubscription> ret = subscriptionService.query(requestParams, LccnSubscription.class, VnfLcmSubscriptions331Sol003Controller::makeLinks, SubscriptionType.VNFPM);
		return ResponseEntity.ok(ret);
	}

	public VnfLcmSubscriptions331Sol003Controller(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsPost(@Valid final LccnSubscriptionRequest body) throws URISyntaxException {
		final LccnSubscription res = subscriptionService.create(body, LccnSubscription.class, VnfLcmSubscriptions331Sol003Controller::makeLinks, SubscriptionType.VNFPM);
		final URI location = new URI(res.getLinks().getSelf().getHref());
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.VNFPM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<LccnSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final LccnSubscription res = subscriptionService.findById(UUID.fromString(subscriptionId), LccnSubscription.class, VnfLcmSubscriptions331Sol003Controller::makeLinks, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}

	private static void makeLinks(final LccnSubscription subs) {
		final LccnSubscriptionLinks links = new LccnSubscriptionLinks();
		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmSubscriptions331Sol003Api.class).subscriptionsSubscriptionIdGet(subs.getId())).withSelfRel().getHref());
		links.setSelf(self);

		subs.setLinks(links);
	}

}
