package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceLinksSelf;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOccIdGetResponse;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOccLinks;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;

@Profile({ "default", "NFVO" })
@RestController
public class NsLcmOpOccsSol005Api implements NsLcmOpOccsSol005 {

	private final NsLcmOpOccsRepository nsLcmOpOccsRepository;

	public NsLcmOpOccsSol005Api(final NsLcmOpOccsRepository _nsLcmOpOccsRepository) {
		nsLcmOpOccsRepository = _nsLcmOpOccsRepository;
	}

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
	public ResponseEntity<String> nsLcmOpOccsGet(final String accept, final String filter, final String fields, final String excludeFields, final String excludeDefault) {
		final List<NsLcmOpOccsNsLcmOpOcc> result = nsLcmOpOccsRepository.query(filter);
		result.stream().forEach(x -> x.setLinks(makeLink(x)));
		final ObjectMapper mapper = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
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
	public ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdContinuePost(final String nsLcmOpOccId) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
	public ResponseEntity<NsLcmOpOccsNsLcmOpOccIdGetResponse> nsLcmOpOccsNsLcmOpOccIdGet(final String nsLcmOpOccId, final String accept, final String contentType) {
		final NsLcmOpOccsNsLcmOpOcc nsLcmOpOccs = nsLcmOpOccsRepository.get(nsLcmOpOccId);
		nsLcmOpOccs.setLinks(makeLink(nsLcmOpOccs));
		final NsLcmOpOccsNsLcmOpOccIdGetResponse nsLcmOpOccIdGetResponse = new NsLcmOpOccsNsLcmOpOccIdGetResponse();
		nsLcmOpOccIdGetResponse.setNsLcmOpOcc(nsLcmOpOccs);

		return new ResponseEntity<>(nsLcmOpOccIdGetResponse, HttpStatus.OK);
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
	public ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdRetryPost(final String nsLcmOpOccId) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
	public ResponseEntity<Void> nsLcmOpOccsNsLcmOpOccIdRollbackPost(final String nsLcmOpOccId) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
	public ResponseEntity<Void> nslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPost(final String nsLcmOpOccId, final String accept, final String contentType, final NslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPostQuery body) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
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
	public ResponseEntity<NslcmV1NsLcmOpOccsNsLcmOpOccIdFailPostResponse> nslcmV1NsLcmOpOccsNsLcmOpOccIdFailPost(final String nsLcmOpOccId, final String accept) {
		// : Implement...
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	private NsLcmOpOccsNsLcmOpOccLinks makeLink(@NotNull final NsLcmOpOccsNsLcmOpOcc nsLcmOpOccs) {
		@NotNull
		final String id = nsLcmOpOccs.getId();
		final NsLcmOpOccsNsLcmOpOccLinks nsLcmOpOccLinks = new NsLcmOpOccsNsLcmOpOccLinks();
		final NsInstancesNsInstanceLinksSelf cancel = new NsInstancesNsInstanceLinksSelf();
		cancel.setHref(linkTo(methodOn(NsLcmOpOccsSol005.class).nslcmV1NsLcmOpOccsNsLcmOpOccIdCancelPost(id, null, null, null)).withSelfRel().getHref());
		nsLcmOpOccLinks.setCancel(cancel);

		final NsInstancesNsInstanceLinksSelf _continue = new NsInstancesNsInstanceLinksSelf();
		_continue.setHref(linkTo(methodOn(NsLcmOpOccsSol005.class).nsLcmOpOccsNsLcmOpOccIdContinuePost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setContinue(_continue);

		final NsInstancesNsInstanceLinksSelf fail = new NsInstancesNsInstanceLinksSelf();
		fail.setHref(linkTo(methodOn(NsLcmOpOccsSol005.class).nslcmV1NsLcmOpOccsNsLcmOpOccIdFailPost(id, null)).withSelfRel().getHref());
		nsLcmOpOccLinks.setFail(fail);

		final NsInstancesNsInstanceLinksSelf nsInstance = new NsInstancesNsInstanceLinksSelf();
		nsInstance.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesGet(nsLcmOpOccs.getNsInstanceId(), null, null, null, null, null)).withSelfRel().getHref());
		nsLcmOpOccLinks.setNsInstance(nsInstance);

		final NsInstancesNsInstanceLinksSelf retry = new NsInstancesNsInstanceLinksSelf();
		retry.setHref(linkTo(methodOn(NsLcmOpOccsSol005.class).nsLcmOpOccsNsLcmOpOccIdRetryPost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setRetry(retry);

		final NsInstancesNsInstanceLinksSelf rollback = new NsInstancesNsInstanceLinksSelf();
		rollback.setHref(linkTo(methodOn(NsLcmOpOccsSol005.class).nsLcmOpOccsNsLcmOpOccIdRollbackPost(id)).withSelfRel().getHref());
		nsLcmOpOccLinks.setRollback(rollback);

		final NsInstancesNsInstanceLinksSelf self = new NsInstancesNsInstanceLinksSelf();
		self.setHref(linkTo(methodOn(NsLcmOpOccsSol005.class).nsLcmOpOccsNsLcmOpOccIdGet(id, null, null)).withSelfRel().getHref());
		nsLcmOpOccLinks.setSelf(self);
		return nsLcmOpOccLinks;
	}

}
