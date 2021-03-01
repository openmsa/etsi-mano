package com.ubiqube.etsi.mano.vnfm.v331.controller.vrqan;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.vrqan.VrQuotaAvailSubscription;
import com.ubiqube.etsi.mano.vnfm.v331.model.vrqan.VrQuotaAvailSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VrQanSubscriptions331Sol003Controller implements VrQanSubscriptions331Sol003Api {

	@Override
	public ResponseEntity<List<VrQuotaAvailSubscription>> subscriptionsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VrQuotaAvailSubscription> subscriptionsPost(@Valid final VrQuotaAvailSubscriptionRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<VrQuotaAvailSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}
}
