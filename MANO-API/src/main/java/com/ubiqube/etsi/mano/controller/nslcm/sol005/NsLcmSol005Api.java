package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierCreationNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierDeletionNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOccIdGetResponse;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOperationOccurrenceNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse;
import com.ubiqube.etsi.mano.model.nslcm.sol005.SubscriptionsPost;
import com.ubiqube.etsi.mano.model.nslcm.sol005.SubscriptionsPostQuery;

/**
 * SOL005 - NS Lifecycle Management Interface
 *
 * <p>
 * SOL005 - NS Lifecycle Management Interface IMPORTANT: Please note that this
 * file might be not aligned to the current version of the ETSI Group
 * Specification it refers to and has not been approved by the ETSI NFV ISG. In
 * case of discrepancies the published ETSI Group Specification takes
 * precedence. Please report bugs to
 * https://forge.etsi.org/bugzilla/buglist.cgi?component=Nfv-Openapis
 *
 */
//@Path("/sol005/nslcm/v1")
@RestController
public class NsLcmSol005Api implements NsLcmSol005 {

	/**
	 * Query multiple NS LCM operation occurrences.
	 *
	 * Get Operation Status. The client can use this method to query status
	 * information about multiple NS lifecycle management operation occurrences.
	 * This method shall follow the provisions specified in the Tables 6.4.9.3.2-1
	 * and 6.4.9.3.2-2 for URI query parameters, request and response data
	 * structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> nsLcmOpOccsGet(String accept, String filter, String fields, String excludeFields, String excludeDefault) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}

	/**
	 * Continue a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates continuing an NS lifecycle operation if that
	 * operation has experienced a temporary failure, i.e. the related \&quot;NS LCM
	 * operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This
	 * method shall follow the provisions specified in the Tables 6.4.13.3.1-1 and
	 * 6.4.13.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public void nsLcmOpOccsNsLcmOpOccIdContinuePost(String nsLcmOpOccId) {
		// TODO: Implement...

	}

	/**
	 * Read an individual NS LCM operation occurrence resource.
	 *
	 * The client can use this method to retrieve status information about a NS
	 * lifecycle management operation occurrence by reading an individual \&quot;NS
	 * LCM operation occurrence\&quot; resource. This method shall follow the
	 * provisions specified in the Tables 6.4.10.3.2-1 and 6.4.10.3.2-2 for URI
	 * query parameters, request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<NsLcmOpOccsNsLcmOpOccIdGetResponse> nsLcmOpOccsNsLcmOpOccIdGet(String nsLcmOpOccId, String accept, String contentType) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Retry a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates retrying a NS lifecycle management operation if
	 * that operation has experienced a temporary failure, i.e. the related
	 * \&quot;NS LCM operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot;
	 * state. This method shall follow the provisions specified in the Tables
	 * 6.4.11.3.1-1 and 6.4.11.3.1-2 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public void nsLcmOpOccsNsLcmOpOccIdRetryPost(String nsLcmOpOccId) {
		// TODO: Implement...

	}

	/**
	 * Rollback a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates rolling back a NS lifecycle operation if that
	 * operation has experienced a temporary failure, i.e. the related \&quot;NS LCM
	 * operation occurrence\&quot; is in \&quot;FAILED_TEMP\&quot; state. This
	 * method shall follow the provisions specified in the Tables 6.4.12.3.1-1 and
	 * 6.4.12.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public void nsLcmOpOccsNsLcmOpOccIdRollbackPost(String nsLcmOpOccId) {
		// TODO: Implement...

	}

	/**
	 * Cancel a NS lifecycle management operation occurrence.
	 *
	 * The POST method initiates canceling an ongoing NS lifecycle management
	 * operation while it is being executed or rolled back, i.e. the related
	 * \&quot;NS LCM operation occurrence\&quot; is either in
	 * \&quot;PROCESSING\&quot; or \&quot;ROLLING_BACK\&quot; state. This method
	 * shall follow the provisions specified in the Tables 6.4.15.3.1-1 and
	 * 6.4.15.3.1-2 for URI query parameters, request and response data structures,
	 * and response codes.
	 *
	 */
	@Override
	public void nslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPost(String nsLcmOpOccId, String accept, String contentType, NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery body) {
		// TODO: Implement...

	}

	/**
	 * Mark a NS lifecycle management operation occurrence as failed.
	 *
	 * The POST method marks a NS lifecycle management operation occurrence as
	 * \&quot;finally failed\&quot; if that operation occurrence is in
	 * \&quot;FAILED_TEMP\&quot; state.
	 *
	 */
	@Override
	public ResponseEntity<NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse> nslcmV1NsLcmOpOccsNsLcmOpOccIdFailPost(String nsLcmOpOccId, String accept) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Query multiple subscriptions.
	 *
	 * Query Subscription Information. The GET method queries the list of active
	 * subscriptions of the functional block that invokes the method. It can be used
	 * e.g. for resynchronization after error situations.
	 *
	 */
	@Override
	public ResponseEntity<List<Object>> subscriptionsGet(String accept) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Subscribe to NS lifecycle change notifications.
	 *
	 * The POST method creates a new subscription. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 6.4.16.3.1-1 and 6.4.16.3.1-2. Creation of two
	 * subscription resources with the same callbackURI and the same filter can
	 * result in performance degradation and will provide duplicates of
	 * notifications to the OSS, and might make sense only in very rare use cases.
	 * Consequently, the NFVO may either allow creating a subscription resource if
	 * another subscription resource with the same filter and callbackUri already
	 * exists (in which case it shall return the \&quot;201 Created\&quot; response
	 * code), or may decide to not create a duplicate subscription resource (in
	 * which case it shall return a \&quot;303 See Other\&quot; response code
	 * referencing the existing subscription resource with the same filter and
	 * callbackUri).
	 *
	 */
	@Override
	public ResponseEntity<SubscriptionsPost> subscriptionsPost(String accept, String contentType, SubscriptionsPostQuery body) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Tables 6.4.17.3.5-1 and 6.4.17.3.5-2.
	 *
	 */
	@Override
	public void subscriptionsSubscriptionIdDelete(String subscriptionId) {
		// TODO: Implement...

	}

	/**
	 * Read an individual subscription resource.
	 *
	 * The GET method retrieves information about a subscription by reading an
	 * individual subscription resource. This method shall support the URI query
	 * parameters, request and response data structures, and response codes, as
	 * specified in the Tables 6.4.17.3.2-1 and 6.4.17.3.2-2
	 *
	 */
	@Override
	public ResponseEntity<SubscriptionsPost> subscriptionsSubscriptionIdGet(String subscriptionId, String accept) {
		// TODO: Implement...

		return null;
	}

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierCreationNotificationPost(NsIdentifierCreationNotification nsIdentifierCreationNotification, String accept, String contentType) {
		// TODO: Implement...

	}

	/**
	 * Test the notification endpoint.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationGet(String accept) {
		// TODO: Implement...

	}

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationPost(NsIdentifierDeletionNotification nsIdentifierDeletionNotification, String accept, String contentType) {
		// TODO: Implement...

	}

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	@Override
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsLcmOperationOccurrenceNotificationPost(NsLcmOperationOccurrenceNotification nsLcmOperationOccurrenceNotification, String accept, String contentType) {
		// TODO: Implement...

	}

}
