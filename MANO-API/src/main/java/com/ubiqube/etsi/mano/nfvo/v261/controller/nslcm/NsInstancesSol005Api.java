package com.ubiqube.etsi.mano.nfvo.v261.controller.nslcm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.Link;
import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.NsInstantiate;
import com.ubiqube.etsi.mano.nfvo.controller.nslcm.NsLcmController;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.CreateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.HealNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsInstance;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsInstanceLinks;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.ScaleNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.TerminateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.UpdateNsRequest;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!VNFM" })
@RestController
public final class NsInstancesSol005Api implements NsInstancesSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(NsInstancesSol005Api.class);

	private final MapperFacade mapper;

	private final NsInstanceControllerService nsInstanceControllerService;
	private final NsLcmController nsLcmController;

	public NsInstancesSol005Api(final MapperFacade _mapper, final NsInstanceControllerService _nsInstanceControllerService, final NsLcmController _nsLcmController) {
		mapper = _mapper;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmController = _nsLcmController;
		LOG.debug("Starting Ns Instance SOL005 Controller.");
	}

	/**
	 * Query multiple NS instances.
	 *
	 * Query NS Instances. The GET method queries information about multiple NS
	 * instances. This method shall support the URI query parameters, request and
	 * response data structures, and response codes, as specified in the Tables
	 * 6.4.2.3.2-1 and 6.4.2.3.2-2.
	 *
	 */
	@Override
	public ResponseEntity<String> nsInstancesGet(final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault) {
		final List<NsdInstance> result = nsLcmController.nsInstancesGet(filter);
		final List<NsInstance> list = result.stream().map(x -> {
			final NsInstance nsi = mapper.map(x, NsInstance.class);
			nsi.setLinks(makeLink(x.getId().toString()));
			return nsi;
		}).collect(Collectors.toList());
		final ObjectMapper objectMapper = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(objectMapper.writeValueAsString(list), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Delete NS instance resource.
	 *
	 * Delete NS Identifier This method deletes an individual NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<Void> nsInstancesNsInstanceIdDelete(final String nsInstanceId) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		nsLcmController.nsInstancesNsInstanceIdDelete(nsInstanceUuid);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual NS instance resource.
	 *
	 * The GET method retrieves information about a NS instance by reading an
	 * individual NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdGet(final String nsInstanceId) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsdInstance nsInstanceDb = nsLcmController.nsInstancesNsInstanceIdGet(nsInstanceUuid);
		final NsInstance nsInstance = mapper.map(nsInstanceDb, NsInstance.class);
		nsInstance.setLinks(makeLink(nsInstanceId));
		return new ResponseEntity<>(nsInstance, HttpStatus.OK);
	}

	/**
	 * Heal a NS instance.
	 *
	 * The POST method requests to heal a NS instance resource. This method shall
	 * follow the provisions specified in the Tables 6.4.7.3.1-1 and 6.4.7.3.1-2 for
	 * URI query parameters, request and response data structures, and response
	 * codes.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdHealPost(final String nsInstanceId, final HealNsRequest body) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsdInstance nsInstanceDb = nsLcmController.nsInstancesNsInstanceIdGet(nsInstanceUuid);
		throw new GenericException("TODO");
	}

	/**
	 * Instantiate a NS.
	 *
	 * The POST method requests to instantiate a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdInstantiatePost(final String nsInstanceId, final InstantiateNsRequest body) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsInstantiate nsInst = mapper.map(body, NsInstantiate.class);
		final NsLcmOpOccs nsLcm = nsInstanceControllerService.instantiate(nsInstanceUuid, nsInst);

		final String link = NsLcmOpOccsSol005Api.makeSelfLink(nsLcm);
		return ResponseEntity.accepted().header("Location", link).build();
	}

	/**
	 * Scale a NS instance.
	 *
	 * The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdScalePost(final String nsInstanceId, final String contentType, final ScaleNsRequest body) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsdInstance nsInstanceDb = nsLcmController.nsInstancesNsInstanceIdScalePost(nsInstanceUuid);
		throw new GenericException("TODO");
	}

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
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdTerminatePost(final String nsInstanceId, final String contentType, final TerminateNsRequest request) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		final NsLcmOpOccs lcm = this.nsInstanceControllerService.terminate(nsInstanceUuid, request.getTerminationTime());

		final String link = NsLcmOpOccsSol005Api.makeSelfLink(lcm);
		return ResponseEntity.accepted().header("Location", link).build();
	}

	/**
	 * Updates a NS instance.
	 *
	 * Scale NS instance. The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdUpdatePost(final String nsInstanceId, final String contentType, final UpdateNsRequest body) {
		final UUID nsInstanceUuid = UUID.fromString(nsInstanceId);
		nsLcmController.nsInstancesNsInstanceIdUpdatePost(nsInstanceUuid);
		throw new GenericException("TODO");
	}

	/**
	 * Create a NS instance resource.
	 *
	 * The POST method creates a new NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesPost(final CreateNsRequest req) {
		if (req.getNsdId() == null) {
			throw new NotFoundException("NsdId field is empty.");
		}

		final NsdInstance nsInstance = nsInstanceControllerService.createNsd(req.getNsdId(), req.getNsName(), req.getNsDescription());
		final NsInstance nsInstanceWeb = mapper.map(nsInstance, NsInstance.class);

		nsInstanceWeb.setLinks(makeLink(nsInstance.getId().toString()));
		return ResponseEntity.created(URI.create(nsInstanceWeb.getLinks().getSelf().getHref())).body(nsInstanceWeb);
	}

	private static NsInstanceLinks makeLink(@Nonnull final String id) {
		final NsInstanceLinks nsInstanceLinks = new NsInstanceLinks();
		final Link heal = new Link();
		heal.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdHealPost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setHeal(heal);

		final Link instantiate = new Link();
		instantiate.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdInstantiatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setInstantiate(instantiate);
		// nsInstanceLinks.setNestedNsInstances(nestedNsInstances);
		final Link scale = new Link();
		scale.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdScalePost(id, null, null)).withSelfRel().getHref());
		nsInstanceLinks.setScale(scale);

		final Link self = new Link();
		self.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdGet(id)).withSelfRel().getHref());
		nsInstanceLinks.setSelf(self);

		final Link terminate = new Link();
		terminate.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdTerminatePost(id, null, null)).withSelfRel().getHref());
		nsInstanceLinks.setTerminate(terminate);

		final Link update = new Link();
		update.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdUpdatePost(id, null, null)).withSelfRel().getHref());
		nsInstanceLinks.setUpdate(update);
		return nsInstanceLinks;
	}

}
