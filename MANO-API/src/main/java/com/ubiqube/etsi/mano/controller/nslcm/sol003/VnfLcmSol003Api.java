package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import static com.ubiqube.etsi.mano.Constants.ensureInstantiated;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.controller.nslcm.LcmLinkable;
import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.OperateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!NFVO" })
@RestController
public class VnfLcmSol003Api implements VnfLcmSol003 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol003Api.class);
	@Nonnull
	private final LcmLinkable links = new Sol003LcmLinkable();
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfInstanceLcm vnfInstanceLcm;
	private final EventManager eventManager;
	private final MapperFacade mapper;

	public VnfLcmSol003Api(final VnfInstancesRepository _vnfInstancesRepository, final VnfInstanceLcm _vnfInstanceLcm, final EventManager _eventManager, final MapperFacade _mapper) {
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
		eventManager = _eventManager;
		mapper = _mapper;
		LOG.debug("Starting Ns Instance SOL003 Controller.");
	}

	@Override
	public ResponseEntity<String> vnfInstancesGet(final Map<String, String> queryParameters) {
		final List<com.ubiqube.etsi.mano.model.nslcm.VnfInstance> result = vnfInstanceLcm.get(queryParameters, links);

		final String exclude = queryParameters.get("exclude_fields");
		final String fields = queryParameters.get("fields");

		final ObjectMapper mapperForView = MapperForView.getMapperForView(exclude, fields, null, null);
		try {
			return new ResponseEntity<>(mapperForView.writeValueAsString(result), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.model.nslcm.VnfInstance> vnfInstancesPost(final CreateVnfRequest createVnfRequest) {
		final com.ubiqube.etsi.mano.model.nslcm.VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest);
		vnfInstance.setLinks(links.getLinks(vnfInstance.getId()));
		return ResponseEntity.accepted().header("Location", vnfInstance.getLinks().getSelf().getHref()).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(final String vnfInstanceId, final ChangeExtVnfConnectivityRequest changeExtVnfConnectivityRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(final String vnfInstanceId) {
		vnfInstanceLcm.delete(vnfInstanceId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.model.nslcm.VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		final VnfInstance vnfInstanceDb = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		final com.ubiqube.etsi.mano.model.nslcm.VnfInstance vnfInstance = mapper.map(vnfInstanceDb, com.ubiqube.etsi.mano.model.nslcm.VnfInstance.class);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);

		throw new GenericException("TODO");

		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest) {
		vnfInstanceLcm.instantiate(vnfInstanceId, instantiateVnfRequest, links);
		return ResponseEntity.accepted().build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId, final OperateVnfRequest operateVnfRequest) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdPatch(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		ensureInstantiated(vnfInstance);
		throw new GenericException("TODO");
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		vnfInstanceLcm.terminate(vnfInstanceId, terminateVnfRequest);

		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, vnfInstanceId);
		return ResponseEntity.noContent().build();
	}

}
