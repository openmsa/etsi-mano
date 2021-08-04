package com.ubiqube.etsi.mano.nfvo.v331.controller.nfvici;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.NfviCapacityInfoSubscription;
import com.ubiqube.etsi.mano.nfvo.v331.model.nfvici.NfviCapacityInfoSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class NfvIciSubscriptions331Sol005Controller implements NfvIciSubscriptions331Sol005Api {

	@Override
	public ResponseEntity<List<NfviCapacityInfoSubscription>> subscriptionsGet(@Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<NfviCapacityInfoSubscription> subscriptionsPost(@Valid final NfviCapacityInfoSubscriptionRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<NfviCapacityInfoSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
