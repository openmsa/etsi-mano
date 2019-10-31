package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierCreationNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsIdentifierDeletionNotification;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOperationOccurrenceNotification;

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
@Profile({ "!VNFM" })
@RestController
public class NsLcmSol005Api implements NsLcmSol005 {

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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierCreationNotificationPost(final NsIdentifierCreationNotification nsIdentifierCreationNotification, final String accept, final String contentType) {
		// : Implement...
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationGet(final String accept) {
		// : Implement...
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsIdentifierDeletionNotificationPost(final NsIdentifierDeletionNotification nsIdentifierDeletionNotification, final String accept, final String contentType) {
		// : Implement...
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
	public void uRIIsProvidedByTheClientWhenCreatingTheSubscriptionNsLcmOperationOccurrenceNotificationPost(final NsLcmOperationOccurrenceNotification nsLcmOperationOccurrenceNotification, final String accept, final String contentType) {
		// : Implement...
	}

}
