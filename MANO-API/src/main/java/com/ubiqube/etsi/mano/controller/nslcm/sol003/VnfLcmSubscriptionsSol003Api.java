package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nslcm.sol003.LccnSubscriptionRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
@RestController
public class VnfLcmSubscriptionsSol003Api implements VnfLcmSubscriptionsSol003 {

	@Override
	public ResponseEntity<Void> subscriptionsGet(final String accept) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> subscriptionsPost(final LccnSubscriptionRequest lccnSubscriptionRequest, final String accept, final String contentType) {
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
