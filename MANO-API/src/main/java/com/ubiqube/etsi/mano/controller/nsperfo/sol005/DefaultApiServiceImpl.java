package com.ubiqube.etsi.mano.controller.nsperfo.sol005;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.ubiqube.etsi.mano.model.nsperfo.sol005.CreatePmJobRequest;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.CreateThresholdRequest;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.PerformanceInformationAvailableNotification;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.PmJobsPmJobIdReportsReportIdGetResponse;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.PmJobsPostResponse;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.SubscriptionsPostQuery;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.SubscriptionsPostResponse;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.ThresholdCrossedNotification;
import com.ubiqube.etsi.mano.model.nsperfo.sol005.ThresholdsPostResponse;

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
@Path("/nspm/v1")
@Api(value = "/", description = "")
public class DefaultApiServiceImpl implements DefaultApi {
	/**
	 * Query PM jobs.
	 *
	 * \&quot;The client can use this method to retrieve information about PM
	 * jobs\&quot;
	 *
	 */
	@Override
	public List<Object> pmJobsGet(String accept, @Context SecurityContext securityContextn, String contentType, String filter, String allFields, String include, String exclude, String excludeDefault) {
		return new ArrayList<>();
	}

	/**
	 * Delete a PM job.
	 *
	 * This method terminates an individual PM job.
	 *
	 */
	@Override
	public void pmJobsPmJobIdDelete(String pmJobId, @Context SecurityContext securityContextn) {
		// TODO: Implement...

	}

	/**
	 * Read a single PM job.
	 *
	 * The client can use this method for reading an individual PM job.
	 *
	 */
	@Override
	public PmJobsPostResponse pmJobsPmJobIdGet(String pmJobId, String accept, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Read an individual performance report.
	 *
	 * The client can use this method for reading an individual performance report.
	 *
	 */
	@Override
	public PmJobsPmJobIdReportsReportIdGetResponse pmJobsPmJobIdReportsReportIdGet(String pmJobId, String reportId, String accept, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Create a PM job.
	 *
	 * The POST method creates a PM job. This method shall follow the provisions
	 * specified in the Tables 7.4.2.3.1-1 and 7.4.2.3.1-2 for URI query parameters,
	 * request and response data structures, and response codes.
	 *
	 */
	@Override
	public PmJobsPostResponse pmJobsPost(CreatePmJobRequest createPmJobRequest, String accept, String contentType, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Query PM related subscriptions.
	 *
	 * The client can use this method to query the list of active subscriptions to
	 * Performance management notifications subscribed by the client. This method
	 * shall follow the provisions specified in the Tables 7.4.7.3.2-1 and
	 * 7.4.7.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public List<Object> subscriptionsGet(String accept, @Context SecurityContext securityContextn, String filter) {
		return new ArrayList<>();
	}

	/**
	 * Subscribe to PM notifications.
	 *
	 * The POST method creates a new subscription. This method shall follow the
	 * provisions specified in the Tables 7.4.7.3.1-1 and 7.4.7.3.1-2 for URI query
	 * parameters, request and response data structures, and response codes.
	 * Creation of two subscription resources with the same callbackURI and the same
	 * filter can result in performance degradation and will provide duplicates of
	 * notifications to the OSS, and might make sense only in very rare use cases.
	 * Consequently, the NFVO may either allow creating a subscription resource if
	 * another subscription resource with the same filter and callbackUri already
	 * exists (in which case it shall return the \&quot;201 Created\&quot; response
	 * code), or may decide to not create a duplicate subscription resource (in
	 * which case it shall return a \&quot;303 See Other\&quot; response code
	 * referencing the existing subscription resource with the same filter and
	 * callbackUri)
	 *
	 */
	@Override
	public SubscriptionsPostResponse subscriptionsPost(String accept, String contentType, SubscriptionsPostQuery body, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Terminate a subscription.
	 *
	 * This method terminates an individual subscription. This method shall follow
	 * the provisions specified in the Tables 7.4.8.3.5-1 and 7.4.8.3.5-2 for URI
	 * query parameters, request and response data structures, and response codes
	 *
	 */
	@Override
	public void subscriptionsSubscriptionIdDelete(String subscriptionId, String accept, @Context SecurityContext securityContextn) {
		// TODO: Implement...

	}

	/**
	 * Query a single PM related subscription.
	 *
	 * The client can use this method for reading an individual subscription about
	 * Performance management notifications subscribed by the client. This method
	 * shall follow the provisions specified in the Tables 7.4.8.3.2-1 and
	 * 7.4.8.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public SubscriptionsPostResponse subscriptionsSubscriptionIdGet(String subscriptionId, String accept, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Query thresholds.
	 *
	 * The client can use this method to query information about thresholds.
	 *
	 */
	@Override
	public List<Object> thresholdsGet(String accept, @Context SecurityContext securityContextn, String filter) {
		return new ArrayList<>();
	}

	/**
	 * Create a threshold.
	 *
	 * The POST method can be used by the client to create a threshold. This method
	 * shall follow the provisions specified in the table 7.4.5.3.1-2 for URI query
	 * parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ThresholdsPostResponse thresholdsPost(CreateThresholdRequest createThresholdRequest, String accept, String contentType, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Delete a threshold.
	 *
	 * This method allows to delete a threshold.
	 *
	 */
	@Override
	public void thresholdsThresholdIdDelete(String thresholdId, String accept, @Context SecurityContext securityContextn) {
		// TODO: Implement...

	}

	/**
	 * Query a single threshold.
	 *
	 * The client can use this method for reading an individual threshold. This
	 * method shall follow the provisions specified in the Tables 7.4.6.3.2-1 and
	 * 7.4.6.3.2-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public ThresholdsPostResponse thresholdsThresholdIdGet(String thresholdId, String accept, @Context SecurityContext securityContextn) {
		// TODO: Implement...

		return null;
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionPerformanceInformationAvailableNotificationPost(PerformanceInformationAvailableNotification performanceInformationAvailableNotification, String accept, String contentType, @Context SecurityContext securityContextn) {
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationGet(String accept, @Context SecurityContext securityContextn) {
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionThresholdCrossedNotificationPost(ThresholdCrossedNotification thresholdCrossedNotification, String accept, String contentType, @Context SecurityContext securityContextn) {
		// TODO: Implement...

	}

}
