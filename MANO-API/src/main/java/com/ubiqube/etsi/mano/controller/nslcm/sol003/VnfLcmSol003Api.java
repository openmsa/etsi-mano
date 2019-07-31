package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.controller.nslcm.VnfInstanceLcm;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;

@RestController
@RequestMapping("/sol003/vnflcm/v1/vnf_instances")
public class VnfLcmSol003Api implements VnfLcmSol003 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol003Api.class);
	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfInstanceLcm vnfInstanceLcm;

	@Autowired
	public VnfLcmSol003Api(VnfInstancesRepository _vnfInstancesRepository, VnfInstanceLcm _vnfInstanceLcm) {
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
		final String hrefScaleToLevel = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdScaleToLevelPost(id)).withSelfRel().getHref();
		final String hrefScale = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdScalePost(id)).withSelfRel().getHref();
		final String hrefOperate = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdOperatePost(id)).withSelfRel().getHref();
		final String hrefInstanciate = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdInstantiatePost(id, null)).withSelfRel().getHref();
		final String hrefIndicators = "";
		final String hrefHeal = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdHealPost(id)).withSelfRel().getHref();
		final String hrefChangeFlavor = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdChangeFlavourPost(id)).withSelfRel().getHref();
		final String hrefChangeExtConn = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdChangeExtConnPost(id)).withSelfRel().getHref();
		final String hrefSelf = linkTo(methodOn(getClass()).vnfInstancesVnfInstanceIdGet(id)).withSelfRel().getHref();
		final VnfInstanceLinks vnfInstanceLinks = LcmFactory.createVnfInstancesLink(hrefSelf, hrefChangeExtConn, hrefChangeFlavor, hrefHeal, hrefIndicators, hrefInstanciate, hrefOperate, hrefScale, hrefScaleToLevel);
		final VnfInstance vnfInstance = vnfInstanceLcm.post(createVnfRequest, id, vnfInstanceLinks);
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
		return new ResponseEntity<>(vnfInstance, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdHealPost(String vnfInstanceId) {
		throw new GenericException("TODO");
	}

	@Override
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdInstantiatePost(String vnfInstanceId, InstantiateVnfRequest instantiateVnfRequest) {
		vnfInstanceLcm.instantiate(vnfInstanceId, instantiateVnfRequest);
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
		vnfInstanceLcm.terminate(vnfInstanceId, terminateVnfRequest);
		return ResponseEntity.noContent().build();
	}
}
