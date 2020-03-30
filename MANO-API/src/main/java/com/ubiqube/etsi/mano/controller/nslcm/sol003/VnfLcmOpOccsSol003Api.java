package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.controller.lcmgrant.LcmGrants;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.Link;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOccLinks;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-06-13T10:04:39.223+02:00")
@Profile({ "!NFVO" })
@RestController
public class VnfLcmOpOccsSol003Api implements VnfLcmOpOccsSol003 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmOpOccsSol003Api.class);
	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	public VnfLcmOpOccsSol003Api(final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository) {
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		LOG.info("Starting VNF LCM OP OCCS SOL003 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfLcmOpOccsGet(final String accept, final String version, final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault, final String nextpageOpaqueMarker) {
		final List<VnfLcmOpOcc> result = vnfLcmOpOccsRepository.query(filter);
		result.stream().forEach(x -> x.setLinks(makeLink(x)));
		final ObjectMapper mapper = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdCancelPost(final String vnfLcmOpOccId, final String version) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdFailPost(final String vnfLcmOpOccId, final String accept, final String version) {
		// VnfLcmOperationOccurenceNotification(result, FAILED, changes) NFVO
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<VnfLcmOpOcc> vnfLcmOpOccsVnfLcmOpOccIdGet(final String vnfLcmOpOccId, final String accept, final String version) {
		final VnfLcmOpOcc entity = vnfLcmOpOccsRepository.get(vnfLcmOpOccId);
		entity.setLinks(makeLink(entity));
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRetryPost(final String vnfLcmOpOccId, final String version) {
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING, CHANGES) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(final String vnfLcmOpOccId, final String version) {
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING, ROLLBACK) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	private static VnfLcmOpOccLinks makeLink(@NotNull final VnfLcmOpOcc vnfLcmOpOcc) {
		@NotNull
		final String id = vnfLcmOpOcc.getId();
		final VnfLcmOpOccLinks link = new VnfLcmOpOccLinks();
		final Link cancel = new Link();
		cancel.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdCancelPost(id, "")).withSelfRel().getHref());
		link.setCancel(cancel);

		final Link fail = new Link();
		fail.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdFailPost(id, "", "")).withSelfRel().getHref());
		link.setFail(fail);

		final Link grant = new Link();
		grant.setHref(linkTo(methodOn(LcmGrants.class).grantsGrantIdGet(vnfLcmOpOcc.getGrantId(), "")).withSelfRel().getHref());
		link.setGrant(grant);

		final Link retry = new Link();
		retry.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdRetryPost(id, "")).withSelfRel().getHref());
		link.setRetry(retry);

		final Link rollback = new Link();
		rollback.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdRollbackPost(id, "")).withSelfRel().getHref());
		link.setRollback(rollback);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfLcmOpOccsSol003.class).vnfLcmOpOccsVnfLcmOpOccIdGet(id, "", "")).withSelfRel().getHref());
		link.setSelf(self);

		final Link vnfInstance = new Link();
		vnfInstance.setHref(linkTo(methodOn(VnfLcmSol003.class).vnfInstancesVnfInstanceIdGet(vnfLcmOpOcc.getId())).withSelfRel().getHref());
		link.setVnfInstance(vnfInstance);

		return link;
	}

}
