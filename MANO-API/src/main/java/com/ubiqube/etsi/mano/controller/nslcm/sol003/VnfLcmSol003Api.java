package com.ubiqube.etsi.mano.controller.nslcm.sol003;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.HashMap;
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

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstanceLinks;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

//@Path("/sol003/vnflcm/v1/vnf_instances")
@RestController
@RequestMapping("/sol003/vnflcm/v1/vnf_instances")
public class VnfLcmSol003Api implements VnfLcmSol003 {
	private static final Logger LOG = LoggerFactory.getLogger(VnfLcmSol003Api.class);
	private final VnfInstancesRepository vnfInstancesRepository;
	private final OrchestrationService orchestrationService;
	private final VnfPackageRepository vnfPackageRepository;

	@Autowired
	public VnfLcmSol003Api(OrchestrationService _orchestrationService, VnfInstancesRepository _vnfInstancesRepository, VnfPackageRepository _vnfPackageRepository) {
		vnfInstancesRepository = _vnfInstancesRepository;
		orchestrationService = _orchestrationService;
		vnfPackageRepository = _vnfPackageRepository;
		LOG.debug("Registrating VnfInstanceApi");
	}

	@Override
	public ResponseEntity<List<VnfInstance>> vnfInstancesGet(String accept, String authorization) {
		return new ResponseEntity<>(vnfInstancesRepository.query(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<VnfInstance> vnfInstancesPost(CreateVnfRequest createVnfRequest) {
		final String vnfId = createVnfRequest.getVnfdId();
		vnfPackageRepository.get(vnfId);
		final VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest);
		final String id = UUID.randomUUID().toString();
		vnfInstance.setId(id);

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
		vnfInstance.setLinks(vnfInstanceLinks);
		vnfInstancesRepository.save(vnfInstance);
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

		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState().equals(InstantiationStateEnum.NOT_INSTANTIATED)) {
			vnfInstancesRepository.delete(vnfInstanceId);
		} else {
			throw new ConflictException("VNF final Instance is instantiated.");
		}
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
		final Map<String, String> varsMap = new HashMap<>();
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final String vnfPkgId = vnfInstance.getVnfdId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String vimConnection = userData.get("vimId");
		if (null == vimConnection) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}
		final String customerId = userData.get("customerId");
		varsMap.put("vnfPkgId", vnfPkgId);
		varsMap.put("customerId", customerId);

		final String processName = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String serviceName = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
		final long serviceId = 0;

		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, serviceName, processName, varsMap);
			userData.put("msaServiceId", String.valueOf(resp.serviceId.id));
			vnfPkg.setOnboardingState(OnboardingStateEnum.ONBOARDED);
			vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
			vnfPackageRepository.save(vnfPkg);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.noContent().build();
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
	public ResponseEntity<Void> vnfInstancesVnfInstanceIdTerminatePost(String vnfInstanceId,
			TerminateVnfRequest terminateVnfRequest) {
		if (terminateVnfRequest.getTerminationType() != TerminationTypeEnum.FORCEFUL) {
			LOG.warn("Terminaison should be set to FORCEFULL.");
		}
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final String vnfPkgId = vnfInstance.getVnfdId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String msaServiceId = userData.get("msaServiceId");

		final String processName = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String serviceName = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = userData.get("customerId");
		final Map<String, String> varsMap = new HashMap<>();

		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, serviceName, processName, varsMap);
			userData.put("msaServiceId", String.valueOf(resp.serviceId.id));
			vnfPkg.setOnboardingState(OnboardingStateEnum.ONBOARDED);
			vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
			vnfPackageRepository.save(vnfPkg);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		return ResponseEntity.noContent().build();
	}
}
