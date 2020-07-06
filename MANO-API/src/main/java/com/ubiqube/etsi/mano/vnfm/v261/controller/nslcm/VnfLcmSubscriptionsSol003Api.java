package com.ubiqube.etsi.mano.vnfm.v261.controller.nslcm;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.LccnSubscriptionRequest;


@Profile({ "!NFVO" })
@RestController
public class VnfLcmSubscriptionsSol003Api implements VnfLcmSubscriptionsSol003 {

	@Override
	public ResponseEntity<Void> subscriptionsGet() {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsPost(final LccnSubscriptionRequest lccnSubscriptionRequest, final String contentType) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		throw new GenericException("TODO");
	}
}
