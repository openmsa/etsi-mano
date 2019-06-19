package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.ubiqube.etsi.mano.model.nslcm.sol005.InlineResponse200;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierCreationNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierDeletionNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdHealPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdInstantiatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdScalePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdTerminatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdUpdatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesPostQuery;
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
public interface DefaultApi {

	/**
	 * Query multiple NS instances.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	public List<Object> nsInstancesGet(@HeaderParam("Accept") String accept, @QueryParam("filter") String filter, @QueryParam("all_fields") String allFields, @QueryParam("fields") String fields, @QueryParam("exclude_fields") String excludeFields, @QueryParam("exclude_default") String excludeDefault, @Context SecurityContext securityContext);

	/**
	 * Delete NS instance resource.
	 *
	 * Delete NS Identifier This method deletes an individual NS instance resource.
	 *
	 */
	public void nsInstancesNsInstanceIdDelete(@PathParam("nsInstanceId") String nsInstanceId, @Context SecurityContext securityContext);

	/**
	 * Read an individual NS instance resource.
	 *
	 * The GET method retrieves information about a NS instance by reading an
	 * individual NS instance resource.
	 *
	 */
	public InlineResponse200 nsInstancesNsInstanceIdGet(@PathParam("nsInstanceId") String nsInstanceId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Context SecurityContext securityContext);

	/**
	 * Heal a NS instance.
	 *
	 * The POST method requests to heal a NS instance resource. This method shall
	 * follow the provisions specified in the Tables 6.4.7.3.1-1 and 6.4.7.3.1-2 for
	 * URI query parameters, request and response data structures, and response
	 * codes.
	 *
	 */
	public NsInstancesNsInstance nsInstancesNsInstanceIdHealPost(@PathParam("nsInstanceId") String nsInstanceId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NsInstancesNsInstanceIdHealPostQuery body, @Context SecurityContext securityContext);

	/**
	 * Instantiate a NS.
	 *
	 * The POST method requests to instantiate a NS instance resource.
	 *
	 */
	public NsInstancesNsInstance nsInstancesNsInstanceIdInstantiatePost(@PathParam("nsInstanceId") String nsInstanceId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NsInstancesNsInstanceIdInstantiatePostQuery body, @Context SecurityContext securityContext);

	/**
	 * Scale a NS instance.
	 *
	 * The POST method requests to scale a NS instance resource.
	 *
	 */
	public NsInstancesNsInstance nsInstancesNsInstanceIdScalePost(@PathParam("nsInstanceId") String nsInstanceId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NsInstancesNsInstanceIdScalePostQuery body, @Context SecurityContext securityContext);

	/**
	 * Terminate a NS instance.
	 *
	 * Terminate NS task. The POST method terminates a NS instance. This method can
	 * only be used with a NS instance in the INSTANTIATED state. Terminating a NS
	 * instance does not delete the NS instance identifier, but rather transitions
	 * the NS into the NOT_INSTANTIATED state. This method shall support the URI
	 * query parameters, request and response data structures, and response codes,
	 * as specified in the Tables 6.4.8.3.1-1 and 6.8.8.3.1-2.
	 *
	 */
	public NsInstancesNsInstance nsInstancesNsInstanceIdTerminatePost(@PathParam("nsInstanceId") String nsInstanceId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NsInstancesNsInstanceIdTerminatePostQuery body, @Context SecurityContext securityContext);

	/**
	 * Updates a NS instance.
	 *
	 * Scale NS instance. The POST method requests to scale a NS instance resource.
	 *
	 */
	public NsInstancesNsInstance nsInstancesNsInstanceIdUpdatePost(@PathParam("nsInstanceId") String nsInstanceId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NsInstancesNsInstanceIdUpdatePostQuery body, @Context SecurityContext securityContext);

	/**
	 * Create a NS instance resource.
	 *
	 * The POST method creates a new NS instance resource.
	 *
	 */
	public InlineResponse200 nsInstancesPost(@HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NsInstancesPostQuery body, @Context SecurityContext securityContext);

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
	public List<Object> nsLcmOpOccsGet(@HeaderParam("Accept") String accept, @QueryParam("filter") String filter, @QueryParam("fields") String fields, @QueryParam("exclude_fields") String excludeFields, @QueryParam("exclude_default") String excludeDefault, @Context SecurityContext securityContext);

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
	public void nsLcmOpOccsNsLcmOpOccIdContinuePost(@PathParam("nsLcmOpOccId") String nsLcmOpOccId, @Context SecurityContext securityContext);

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
	public NsLcmOpOccsNsLcmOpOccIdGetResponse nsLcmOpOccsNsLcmOpOccIdGet(@PathParam("nsLcmOpOccId") String nsLcmOpOccId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Context SecurityContext securityContext);

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
	public void nsLcmOpOccsNsLcmOpOccIdRetryPost(@PathParam("nsLcmOpOccId") String nsLcmOpOccId, @Context SecurityContext securityContext);

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
	public void nsLcmOpOccsNsLcmOpOccIdRollbackPost(@PathParam("nsLcmOpOccId") String nsLcmOpOccId, @Context SecurityContext securityContext);

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
	public void nslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPost(@PathParam("nsLcmOpOccId") String nsLcmOpOccId, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery body, @Context SecurityContext securityContext);

	/**
	 * Mark a NS lifecycle management operation occurrence as failed.
	 *
	 * The POST method marks a NS lifecycle management operation occurrence as
	 * \&quot;finally failed\&quot; if that operation occurrence is in
	 * \&quot;FAILED_TEMP\&quot; state.
	 *
	 */
	public NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse nslcmV1NsLcmOpOccsNsLcmOpOccIdFailPost(@PathParam("nsLcmOpOccId") String nsLcmOpOccId, @HeaderParam("Accept") String accept, @Context SecurityContext securityContext);

	/**
	 * Query multiple subscriptions.
	 *
	 * Query Subscription Information. The GET method queries the list of active
	 * subscriptions of the functional block that invokes the method. It can be used
	 * e.g. for resynchronization after error situations.
	 *
	 */
	public List<Object> subscriptionsGet(@HeaderParam("Accept") String accept, @Context SecurityContext securityContext);

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
	public SubscriptionsPost subscriptionsPost(@HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Valid SubscriptionsPostQuery body, @Context SecurityContext securityContext);

	/**
	 * Terminate a subscription.
	 *
	 * The DELETE method terminates an individual subscription. This method shall
	 * support the URI query parameters, request and response data structures, and
	 * response codes, as specified in the Tables 6.4.17.3.5-1 and 6.4.17.3.5-2.
	 *
	 */
	public void subscriptionsSubscriptionIdDelete(@PathParam("subscriptionId") String subscriptionId, @Context SecurityContext securityContext);

	/**
	 * Read an individual subscription resource.
	 *
	 * The GET method retrieves information about a subscription by reading an
	 * individual subscription resource. This method shall support the URI query
	 * parameters, request and response data structures, and response codes, as
	 * specified in the Tables 6.4.17.3.2-1 and 6.4.17.3.2-2
	 *
	 */
	public SubscriptionsPost subscriptionsSubscriptionIdGet(@PathParam("subscriptionId") String subscriptionId, @HeaderParam("Accept") String accept, @Context SecurityContext securityContext);

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierCreationNotificationPost(@Valid NsIdentifierCreationNotification nsIdentifierCreationNotification, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Context SecurityContext securityContext);

	/**
	 * Test the notification endpoint.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationGet(@HeaderParam("Accept") String accept, @Context SecurityContext securityContext);

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationPost(@Valid NsIdentifierDeletionNotification nsIdentifierDeletionNotification, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Context SecurityContext securityContext);

	/**
	 * Notify about NS lifecycle change
	 *
	 * The POST method delivers a notification from the server to the client. This
	 * method shall support the URI query parameters, request and response data
	 * structures, and response codes, as specified in the Tables 6.4.18.3.1-1 and
	 * 6.4.18.3.1-2.
	 *
	 */
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsLcmOperationOccurrenceNotificationPost(@Valid NsLcmOperationOccurrenceNotification nsLcmOperationOccurrenceNotification, @HeaderParam("Accept") String accept, @HeaderParam("Content-Type") String contentType, @Context SecurityContext securityContext);
}
