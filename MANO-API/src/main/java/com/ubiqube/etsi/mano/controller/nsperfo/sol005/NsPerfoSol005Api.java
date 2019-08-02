package com.ubiqube.etsi.mano.controller.nsperfo.sol005;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nsperfo.sol005.PerformanceInformationAvailableNotification;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.ThresholdCrossedNotification;

import io.swagger.annotations.Api;

/**
 * SOL005 - NS Performance Management Interface
 *
 * <p>
 * SOL005 - NS Performance Management Interface IMPORTANT: Please note that this
 * file might be not aligned to the current version of the ETSI Group
 * Specification it refers to and has not been approved by the ETSI NFV ISG. In
 * case of discrepancies the published ETSI Group Specification takes
 * precedence. Please report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
@RestController
@RequestMapping("/sol005/nspm/v1")
@Api(value = "/")
public class NsPerfoSol005Api implements NsPerfoSol005 {

	/**
	 * Notify about PM related events
	 *
	 * The POST method delivers a notification regarding a performance management
	 * event from the server to the client. This method shall follow the provisions
	 * specified in the Tables 7.4.9.3.1-1 and 7.4.9.3.1-2 for URI query parameters,
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPerformanceInformationAvailableNotificationPost(PerformanceInformationAvailableNotification performanceInformationAvailableNotification, String accept, String contentType) {
		// TODO: Implement...

	}

	/**
	 * Test the notification endpoint
	 *
	 * The GET method allows the server to test the notification endpoint that is
	 * provided by the client, e.g. during subscription. This method shall follow
	 * the provisions specified in the Tables 7.4.9.3.2-1 and 7.4.9.3.2-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationGet(String accept) {
		// TODO: Implement...

	}

	/**
	 * Notify about PM related events
	 *
	 * The POST method delivers a notification regarding a performance management
	 * event from the server to the client. This method shall follow the provisions
	 * specified in the Tables 7.4.9.3.1-1 and 7.4.9.3.1-2 for URI query parameters,
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationPost(ThresholdCrossedNotification thresholdCrossedNotification, String accept, String contentType) {
		// TODO: Implement...

	}

}
