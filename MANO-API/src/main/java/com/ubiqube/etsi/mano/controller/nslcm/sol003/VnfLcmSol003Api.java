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
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nslcm.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfVirtualLinkResourceInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfcResourceInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ChangeExtVnfConnectivityRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.OperateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ScaleVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.ScaleVnfToLevelRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol005.VnfExtCpInfo;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfPackageService;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!NFVO" })
@RestController
public class VnfLcmSol003Api implements VnfLcmSol003 {
	private static final String LOCATION = "Location";
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol003Api.class);
	@Nonnull
	private final LcmLinkable links = new Sol003LcmLinkable();
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfInstanceLcm vnfInstanceLcm;
	private final MapperFacade mapper;
	private final VnfInstanceService vnfInstanceService;
	private final VnfPackageService vnfPackageService;

	public VnfLcmSol003Api(final VnfInstancesRepository _vnfInstancesRepository, final VnfInstanceLcm _vnfInstanceLcm, final MapperFacade _mapper, final VnfInstanceService _vnfInstanceService, final VnfPackageService _vnfPackageService) {
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
		mapper = _mapper;
		vnfInstanceService = _vnfInstanceService;
		vnfPackageService = _vnfPackageService;
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
		return ResponseEntity.accepted().body(vnfInstance);
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
		vnfInstanceLcm.delete(UUID.fromString(vnfInstanceId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<com.ubiqube.etsi.mano.model.nslcm.VnfInstance> vnfInstancesVnfInstanceIdGet(final String vnfInstanceId) {
		final VnfInstance vnfInstanceDb = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		final com.ubiqube.etsi.mano.model.nslcm.VnfInstance vnfInstance = mapper.map(vnfInstanceDb, com.ubiqube.etsi.mano.model.nslcm.VnfInstance.class);

		final VnfPackage vnfPackage = vnfPackageService.findById(UUID.fromString(vnfInstance.getVnfPkgId()));
		mapper.map(vnfPackage, vnfInstance);
		final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = vnfInstance.getInstantiatedVnfInfo();

		final List<VnfInstantiatedCompute> liveCompute = vnfInstanceService.getLiveComputeInstanceOf(vnfInstanceDb);
		final List<VnfcResourceInfo> vnfcResourceInfo = mapper.mapAsList(liveCompute, VnfcResourceInfo.class);
		instantiatedVnfInfo.setVnfcResourceInfo(vnfcResourceInfo);

		final List<VnfInstantiatedExtCp> liveExtCp = vnfInstanceService.getLiveExtCpInstanceOf(vnfInstanceDb);
		final List<VnfExtCpInfo> extCpInfo = mapper.mapAsList(liveExtCp, VnfExtCpInfo.class);
		instantiatedVnfInfo.setExtCpInfo(extCpInfo);

		final List<VnfInstantiatedStorage> liveStorage = vnfInstanceService.getLiveStorageInstanceOf(vnfInstanceDb);
		final List<VirtualStorageResourceInfo> virtualStorageResourceInfo = mapper.mapAsList(liveStorage, VirtualStorageResourceInfo.class);
		instantiatedVnfInfo.setVirtualStorageResourceInfo(virtualStorageResourceInfo);

		final List<VnfInstantiatedVirtualLink> liveVirtualLink = vnfInstanceService.getLiveVirtualLinkInstanceOf(vnfInstanceDb);
		final List<VnfVirtualLinkResourceInfo> virtualLinkResourceInfo = mapper.mapAsList(liveVirtualLink, VnfVirtualLinkResourceInfo.class);
		instantiatedVnfInfo.setVirtualLinkResourceInfo(virtualLinkResourceInfo);

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
		final VnfLcmOpOccs lcm = vnfInstanceLcm.instantiate(UUID.fromString(vnfInstanceId), instantiateVnfRequest);
		final String link = VnfLcmOpOccsSol003Api.getSelfLink(lcm.getId().toString());
		return ResponseEntity.accepted().header(LOCATION, link).build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(final String vnfInstanceId, final OperateVnfRequest operateVnfRequest) {
		final VnfLcmOpOccs lcm = vnfInstanceLcm.operate(UUID.fromString(vnfInstanceId), operateVnfRequest);
		final String link = VnfLcmOpOccsSol003Api.getSelfLink(lcm.getId().toString());
		return ResponseEntity.accepted().header(LOCATION, link).build();
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
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(final String vnfInstanceId, final ScaleVnfRequest scaleVnfRequest) {
		final VnfLcmOpOccs lcm = vnfInstanceLcm.scale(UUID.fromString(vnfInstanceId), scaleVnfRequest);
		final String link = VnfLcmOpOccsSol003Api.getSelfLink(lcm.getId().toString());
		return ResponseEntity.noContent().header(LOCATION, link).build();
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(final String vnfInstanceId, final ScaleVnfToLevelRequest scaleVnfToLevelRequest) {
		final VnfLcmOpOccs lcm = vnfInstanceLcm.scaleToLevel(UUID.fromString(vnfInstanceId), scaleVnfToLevelRequest);
		final String link = VnfLcmOpOccsSol003Api.getSelfLink(lcm.getId().toString());
		return ResponseEntity.noContent().header(LOCATION, link).build();
		// after return.
		// VnfLcmOperationOccurenceNotification(STARTING) NFVO
		// VnfLcmOperationOccurenceNotification(PROCESSING) NFVO
		// VnfLcmOperationOccurenceNotification(COMPLETED) NFVO
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest) {
		final VnfLcmOpOccs lcm = vnfInstanceLcm.terminate(UUID.fromString(vnfInstanceId), terminateVnfRequest);
		final String link = VnfLcmOpOccsSol003Api.getSelfLink(lcm.getId().toString());
		return ResponseEntity.noContent().header(LOCATION, link).build();
	}

}
