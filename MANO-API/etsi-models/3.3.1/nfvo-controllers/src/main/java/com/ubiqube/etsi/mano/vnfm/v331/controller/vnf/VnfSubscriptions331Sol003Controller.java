package com.ubiqube.etsi.mano.vnfm.v331.controller.vnf;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PkgmSubscription;
import com.ubiqube.etsi.mano.vnfm.v331.model.vnf.PkgmSubscriptionRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
public class VnfSubscriptions331Sol003Controller implements VnfSubscriptions331Sol003Api {

	@Override
	public ResponseEntity<List<PkgmSubscription>> subscriptionsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<PkgmSubscription>> subscriptionsPost(@Valid final PkgmSubscriptionRequest body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> subscriptionsSubscriptionIdDelete(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<PkgmSubscription> subscriptionsSubscriptionIdGet(final String subscriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

}
