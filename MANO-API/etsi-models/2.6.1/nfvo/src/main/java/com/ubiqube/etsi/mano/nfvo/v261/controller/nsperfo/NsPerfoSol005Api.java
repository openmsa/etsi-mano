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
package com.ubiqube.etsi.mano.nfvo.v261.controller.nsperfo;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.PerformanceInformationAvailableNotification;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo.ThresholdCrossedNotification;

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
@Profile({ "!VNFM" })
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPerformanceInformationAvailableNotificationPost(final PerformanceInformationAvailableNotification performanceInformationAvailableNotification, final String accept, final String contentType) {
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationGet(final String accept) {
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationPost(final ThresholdCrossedNotification thresholdCrossedNotification, final String accept, final String contentType) {
		// TODO: Implement...

	}

}
