package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOccIdGetResponse;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse;

@RestController
public class NsLcmOpOccsSol005Api implements NsLcmOpOccsSol005 {

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
		// : Implement...
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
		// : Implement...
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
		// : Implement...
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
		// : Implement...
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
		// : Implement...
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
		// : Implement...
		return null;
	}

}
