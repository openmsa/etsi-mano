/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
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
