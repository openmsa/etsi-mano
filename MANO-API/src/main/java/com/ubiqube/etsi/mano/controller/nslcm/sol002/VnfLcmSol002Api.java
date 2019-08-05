package com.ubiqube.etsi.mano.controller.nslcm.sol002;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.controller.nslcm.LcmLinkable;
import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@RestController
public class VnfLcmSol002Api implements VnfLcmSol002 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol002Api.class);
	@Nonnull
	private final LcmLinkable links = new Sol002LcmLinkable();
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfInstanceLcm vnfInstanceLcm;

	public VnfLcmSol002Api(VnfInstancesRepository _vnfInstancesRepository, VnfInstanceLcm _vnfInstanceLcm) {
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
		LOG.debug("Registrating VnfInstanceApi");
	}

	@Override
	public ResponseEntity<String> vnfInstancesGet(Map<String, String> queryParameters) {
		final List<VnfInstance> result = vnfInstanceLcm.get(queryParameters, links);
		final String exclude = queryParameters.get("exclude_fields");
		final String fields = queryParameters.get("fields");

		final ObjectMapper mapper = MapperForView.getMapperForView(exclude, fields, null, null);
		try {
			return new ResponseEntity<>(mapper.writeValueAsString(result), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesPost(CreateVnfRequest createVnfRequest) {

		final String id = UUID.randomUUID().toString();

		final VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest, id, links);
		vnfInstance.setLinks(links.getLinks(id));

		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeExtConnPost(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdChangeFlavourPost(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdDelete(String vnfInstanceId) {
		vnfInstanceLcm.delete(vnfInstanceId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesVnfInstanceIdGet(String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(String vnfInstanceId, InstantiateVnfRequest instantiateVnfRequest) {
		vnfInstanceLcm.instantiate(vnfInstanceId, instantiateVnfRequest, links);
		return ResponseEntity.accepted().build();
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdOperatePost(String vnfInstanceId) {

		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdPatch(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScalePost(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdScaleToLevelPost(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(String vnfInstanceId, TerminateVnfRequest terminateVnfRequest) {
		vnfInstanceLcm.terminate(vnfInstanceId, terminateVnfRequest, links);
		return ResponseEntity.noContent().build();
	}
}
