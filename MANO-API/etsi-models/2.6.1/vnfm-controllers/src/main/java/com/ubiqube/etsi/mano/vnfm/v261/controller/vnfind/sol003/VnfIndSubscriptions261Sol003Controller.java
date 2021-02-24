package com.ubiqube.etsi.mano.vnfm.v261.controller.vnfind.sol003;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.SubscriptionServiceV2;
import com.ubiqube.etsi.mano.vnfm.v261.controller.vnffm.sol002.FaultmngtSubscriptions261Sol002Api;
import com.ubiqube.etsi.mano.vnfm.v261.model.vnfind.VnfIndicatorSubscription;
import com.ubiqube.etsi.mano.vnfm.v261.model.vnfind.VnfIndicatorSubscriptionLinks;
import com.ubiqube.etsi.mano.vnfm.v261.model.vnfind.VnfIndicatorSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfIndSubscriptions261Sol003Controller implements VnfIndSubscriptions261Sol003Api {
	private final SubscriptionServiceV2 subscriptionService;

	public VnfIndSubscriptions261Sol003Controller(final SubscriptionServiceV2 subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@Override
	public ResponseEntity<List<VnfIndicatorSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		final List<VnfIndicatorSubscription> ret = subscriptionService.query(requestParams, VnfIndicatorSubscription.class, VnfIndSubscriptions261Sol003Controller::makeLinks, SubscriptionType.VNFIND);
		return ResponseEntity.ok(ret);
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsPost(@Valid final VnfIndicatorSubscriptionRequest vnfIndicatorSubscriptionRequest) throws URISyntaxException {
		final VnfIndicatorSubscription res = subscriptionService.create(vnfIndicatorSubscriptionRequest, VnfIndicatorSubscription.class, VnfIndSubscriptions261Sol003Controller::makeLinks, SubscriptionType.VNFPM);
		final URI location = new URI(res.getLinks().getSelf().getHref());
		return ResponseEntity.created(location).body(res);
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		subscriptionService.deleteById(UUID.fromString(subscriptionId), SubscriptionType.VNFPM);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VnfIndicatorSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		final VnfIndicatorSubscription res = subscriptionService.findById(UUID.fromString(subscriptionId), VnfIndicatorSubscription.class, VnfIndSubscriptions261Sol003Controller::makeLinks, SubscriptionType.ALARM);
		return ResponseEntity.ok(res);
	}

	private static void makeLinks(final VnfIndicatorSubscription subscription) {
		final VnfIndicatorSubscriptionLinks links = new VnfIndicatorSubscriptionLinks();
		final Link link = new Link();
		link.setHref(linkTo(methodOn(FaultmngtSubscriptions261Sol002Api.class).subscriptionsSubscriptionIdGet(subscription.getId())).withSelfRel().getHref());
		links.setSelf(link);
		subscription.setLinks(links);
	}
}
