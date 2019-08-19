package com.ubiqube.etsi.mano.controller.nsd.sol005;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfoIdGetResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfoIdPatchQuery;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPnfdInfoIdPatchResponse;
import com.ubiqube.etsi.mano.model.nsd.sol005.PnfDescriptorsPostQuery;

@RestController
public class PnfDescriptorsSol005Api implements PnfDescriptorsSol005 {

	/**
	 * Query information about multiple PNF descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple PNF descriptor
	 * resources.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<List<PnfDescriptorsPnfdInfo>> pnfDescriptorsGet(final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault) {
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Delete an individual PNF descriptor resource.
	 *
	 * The DELETE method deletes an individual PNF descriptor resource. An
	 * individual PNF descriptor resource can only be deleted when there is no NS
	 * instance using it or there is NSD referencing it. To delete all PNFD versions
	 * identified by a particular value of the \&quot;pnfdInvariantId\&quot;
	 * attribute, the procedure is to first use the GET method with filter
	 * \&quot;pnfdInvariantId\&quot; towards the PNF descriptors resource to find
	 * all versions of the PNFD. Then, the client uses the DELETE method described
	 * in this clause to delete each PNFD version individually.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdDelete(final String pnfdInfoId) {
		// PnfdDeletionNotification OSS/BSS
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual PNFD resource.
	 *
	 * The GET method reads information about an individual PNF descriptor. This
	 * method shall follow the provisions specified in the Tables 5.4.6.3.2-1 and
	 * 5.4.6.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<PnfDescriptorsPnfdInfoIdGetResponse> pnfDescriptorsPnfdInfoIdGet(final String pnfdInfoId, final String accept) {
		// : Implement...

		return null;
	}

	/**
	 * Modify the user defined data of an individual PNF descriptor resource.
	 *
	 * The PATCH method modifies the user defined data of an individual PNF
	 * descriptor resource.
	 *
	 */
	@Override
	public ResponseEntity<PnfDescriptorsPnfdInfoIdPatchResponse> pnfDescriptorsPnfdInfoIdPatch(final String pnfdInfoId, final String accept, final String contentType, final PnfDescriptorsPnfdInfoIdPatchQuery body) {
		// : Implement...

		return null;
	}

	/**
	 * Fetch the content of a PNFD.
	 *
	 * The GET method fetches the content of the PNFD. This method shall follow the
	 * provisions specified in the Table 5.4.7.3.2-2 for URI query parameters,
	 * request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdContentGet(final String pnfdInfoId, final String accept) {
		// : Implement...
		return ResponseEntity.noContent().build();
	}

	/**
	 * Upload the content of a PNFD.
	 *
	 * The PUT method is used to upload the content of a PNFD. This resource
	 * represents the content of the individual PNF descriptor, i.e. PNFD content.
	 * The client can use this resource to upload and download the content of the
	 * PNFD.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdContentPut(final String pnfdInfoId, final String accept) {
		// PnfdOnBoardingNotification OSS/BSS
		return ResponseEntity.noContent().build();
	}

	/**
	 * Create a new PNF descriptor resource.
	 *
	 * The POST method is used to create a new PNF descriptor resource
	 *
	 */
	@Override
	public ResponseEntity<PnfDescriptorsPnfdInfoIdGetResponse> pnfDescriptorsPost(final String accept, final String contentType, final PnfDescriptorsPostQuery body) {
		// : Implement...

		return null;
	}

}
