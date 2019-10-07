package com.ubiqube.etsi.mano.controller.nslcm.sol005;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;
import static com.ubiqube.etsi.mano.Constants.ensureIsEnabled;
import static com.ubiqube.etsi.mano.Constants.ensureIsOnboarded;
import static com.ubiqube.etsi.mano.Constants.ensureNotInstantiated;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.factory.NsInstanceFactory;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nsd.NsdPkgIndex;
import com.ubiqube.etsi.mano.model.nsd.NsdPkgInstance;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsDescriptorsNsdInfo.NsdUsageStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.InlineResponse200;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesCreateNsRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdHealPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdInstantiatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdScalePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdTerminatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceIdUpdatePostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceLinks;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceLinksSelf;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesNsInstanceVnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstancesPostQuery;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mano.service.event.EventManager;

@Profile({ "default", "NFVO" })
@RestController
public final class NsInstancesSol005Api implements NsInstancesSol005 {
	private static final Logger LOG = LoggerFactory.getLogger(NsInstancesSol005Api.class);

	private final NsdRepository nsdRepository;
	private final NsInstanceRepository nsInstanceRepository;

	private final NsLcmOpOccsRepository lcmOpOccsRepository;

	private final VnfmInterface vnfm;
	private final VnfPackageRepository vnfPackageRepository;
	private final EventManager eventManager;

	public NsInstancesSol005Api(final NsdRepository _nsdRepository, final NsInstanceRepository _nsInstanceRepository, final NsLcmOpOccsRepository _lcmOpOccsRepository, final VnfPackageRepository _vnfPackageRepository, final VnfmInterface _vnfm, final EventManager _eventManager) {
		nsdRepository = _nsdRepository;
		nsInstanceRepository = _nsInstanceRepository;
		lcmOpOccsRepository = _lcmOpOccsRepository;
		vnfPackageRepository = _vnfPackageRepository;
		vnfm = _vnfm;
		eventManager = _eventManager;
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
	public ResponseEntity<String> nsInstancesGet(final String accept, final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault) {
		final List<NsInstance> result = nsInstanceRepository.query(filter);
		result.stream().forEach(x -> x.setLinks(makeLink(x.getId())));
		final ObjectMapper mapper = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
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
	public void nsInstancesNsInstanceIdDelete(final String nsInstanceId) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		ensureNotInstantiated(nsInstance);

		nsInstanceRepository.delete(nsInstanceId);
	}

	/**
	 * Read an individual NS instance resource.
	 *
	 * The GET method retrieves information about a NS instance by reading an
	 * individual NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<InlineResponse200> nsInstancesNsInstanceIdGet(final String nsInstanceId) {
		final InlineResponse200 resp = new InlineResponse200();
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		nsInstance.setLinks(makeLink(nsInstanceId));
		resp.setNsInstance(nsInstance);
		return new ResponseEntity<>(resp, HttpStatus.OK);
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
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdHealPost(final String nsInstanceId, final NsInstancesNsInstanceIdHealPostQuery body) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		ensureInstantiated(nsInstance);
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(nsInstanceId, LcmOperationTypeEnum.HEAL);
		lcmOpOccsRepository.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	/**
	 * Instantiate a NS.
	 *
	 * The POST method requests to instantiate a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdInstantiatePost(final String nsInstanceId, final NsInstancesNsInstanceIdInstantiatePostQuery body) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		ensureNotInstantiated(nsInstance);

		eventManager.sendAction(ActionType.NS_INSTANTIATE, nsInstanceId, new HashMap<String, Object>());

		nsInstance.setLinks(makeLink(nsInstanceId));
		return new ResponseEntity<>(nsInstance, HttpStatus.OK);
	}

	/**
	 * Scale a NS instance.
	 *
	 * The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdScalePost(final String nsInstanceId, final String accept, final String contentType, final NsInstancesNsInstanceIdScalePostQuery body) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		ensureInstantiated(nsInstance);
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(nsInstanceId, LcmOperationTypeEnum.SCALE);
		lcmOpOccsRepository.save(lcmOpOccs);
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
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdTerminatePost(final String nsInstanceId, final String accept, final String contentType, final NsInstancesNsInstanceIdTerminatePostQuery body) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		ensureInstantiated(nsInstance);

		eventManager.sendAction(ActionType.NS_TERMINATE, nsInstanceId, new HashMap<String, Object>());

		nsInstance.setLinks(makeLink(nsInstanceId));
		return new ResponseEntity<>(nsInstance, HttpStatus.OK);
	}

	/**
	 * Updates a NS instance.
	 *
	 * Scale NS instance. The POST method requests to scale a NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<NsInstance> nsInstancesNsInstanceIdUpdatePost(final String nsInstanceId, final String accept, final String contentType, final NsInstancesNsInstanceIdUpdatePostQuery body) {
		final NsInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		ensureInstantiated(nsInstance);
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(nsInstanceId, LcmOperationTypeEnum.UPDATE);
		lcmOpOccsRepository.save(lcmOpOccs);
		throw new GenericException("TODO");
	}

	/**
	 * Create a NS instance resource.
	 *
	 * The POST method creates a new NS instance resource.
	 *
	 */
	@Override
	public ResponseEntity<InlineResponse200> nsInstancesPost(final NsInstancesPostQuery body) {
		final NsInstancesCreateNsRequest req = body.getCreateNsRequest();
		if (req.getNsdId() == null) {
			throw new NotFoundException("NsdId field is empty.");
		}
		final NsDescriptorsNsdInfo nsd = nsdRepository.get(req.getNsdId());
		ensureIsOnboarded(nsd);
		ensureIsEnabled(nsd);
		nsd.setNsdUsageState(NsdUsageStateEnum.IN_USE);
		nsdRepository.save(nsd);

		final NsInstance nsInstance = NsInstanceFactory.createNsInstancesNsInstance(req, nsd);
		nsInstanceRepository.save(nsInstance);

		final List<NsInstancesNsInstanceVnfInstance> vnfInstances = new ArrayList<>();
		final List<String> vnfs = nsd.getVnfPkgIds();
		for (final String id : vnfs) {
			final VnfPkgInfo vnf = vnfPackageRepository.get(id);
			ensureIsOnboarded(vnf);
			ensureIsEnabled(vnf);
			final VnfInstance vnfInstance = vnfm.createVnfInstance(vnf, "VNF instance hold by: " + nsInstance.getId(), id);
			final NsInstancesNsInstanceVnfInstance nsInstancesNsInstanceVnfInstance = NsInstanceFactory.createNsInstancesNsInstanceVnfInstance(vnfInstance, vnf);
			vnfInstances.add(nsInstancesNsInstanceVnfInstance);
		}

		nsInstance.setVnfInstance(vnfInstances);
		nsInstanceRepository.save(nsInstance);

		final NsdPkgIndex nsdIndex = nsdRepository.loadObject(req.getNsdId(), NsdPkgIndex.class, "indexes.json");
		nsdIndex.addNsdPkgInstance(new NsdPkgInstance(nsInstance.getId()));
		nsdRepository.storeObject(req.getNsdId(), nsdIndex, "indexes.json");

		nsInstance.setLinks(makeLink(nsInstance.getId()));
		final InlineResponse200 resp = new InlineResponse200();
		resp.setNsInstance(nsInstance);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	private static NsInstancesNsInstanceLinks makeLink(@Nonnull final String id) {
		final NsInstancesNsInstanceLinks nsInstanceLinks = new NsInstancesNsInstanceLinks();
		final NsInstancesNsInstanceLinksSelf heal = new NsInstancesNsInstanceLinksSelf();
		heal.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdHealPost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setHeal(heal);

		final NsInstancesNsInstanceLinksSelf instantiate = new NsInstancesNsInstanceLinksSelf();
		instantiate.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdInstantiatePost(id, null)).withSelfRel().getHref());
		nsInstanceLinks.setInstantiate(instantiate);
		// nsInstanceLinks.setNestedNsInstances(nestedNsInstances);
		final NsInstancesNsInstanceLinksSelf scale = new NsInstancesNsInstanceLinksSelf();
		scale.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdScalePost(id, null, null, null)).withSelfRel().getHref());
		nsInstanceLinks.setScale(scale);

		final NsInstancesNsInstanceLinksSelf self = new NsInstancesNsInstanceLinksSelf();
		self.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdGet(id)).withSelfRel().getHref());
		nsInstanceLinks.setSelf(self);

		final NsInstancesNsInstanceLinksSelf terminate = new NsInstancesNsInstanceLinksSelf();
		terminate.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdTerminatePost(id, null, null, null)).withSelfRel().getHref());
		nsInstanceLinks.setTerminate(terminate);

		final NsInstancesNsInstanceLinksSelf update = new NsInstancesNsInstanceLinksSelf();
		update.setHref(linkTo(methodOn(NsInstancesSol005.class).nsInstancesNsInstanceIdUpdatePost(id, null, null, null)).withSelfRel().getHref());
		nsInstanceLinks.setUpdate(update);
		return nsInstanceLinks;
	}

}
