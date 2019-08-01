package com.ubiqube.etsi.mano.controller.nslcm.sol002;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.LcmLinkable;
import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@RestController
@RequestMapping("/sol002/vnflcm/v1/vnf_instances")
public class VnfLcmSol002Api implements VnfLcmSol002 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol002Api.class);
	@Nonnull
	private final LcmLinkable links = new Sol002LcmLinkable();
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfInstanceLcm vnfInstanceLcm;

	@Autowired
	public VnfLcmSol002Api(VnfInstancesRepository _vnfInstancesRepository, VnfInstanceLcm _vnfInstanceLcm) {
		vnfInstancesRepository = _vnfInstancesRepository;
		vnfInstanceLcm = _vnfInstanceLcm;
		LOG.debug("Registrating VnfInstanceApi");
	}

	@Override
	public ResponseEntity<List<VnfInstance>> vnfInstancesGet(Map<String, String> queryParameters) {
		final List<VnfInstance> result = vnfInstanceLcm.get(queryParameters);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesPost(CreateVnfRequest createVnfRequest) {

		final String id = UUID.randomUUID().toString();

		final VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest, id);
		final VnfInstanceLinks vnfInstanceLinks = links.getLinks(id);
		vnfInstance.setLinks(vnfInstanceLinks);

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
