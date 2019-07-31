package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.api.entities.orchestration.ProcessInstance;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.orchestration.OrchestrationService;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
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

public class VnfInstanceLcm {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceLcm.class);

	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageRepository vnfPackageRepository;
	private final OrchestrationService orchestrationService;

	public VnfInstanceLcm(VnfInstancesRepository vnfInstancesRepository, VnfPackageRepository vnfPackageRepository, OrchestrationService orchestrationService) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		this.orchestrationService = orchestrationService;
	}

	public List<VnfInstance> get(Map<String, String> queryParameters) {
		final String filter = queryParameters.get("filter");
		final AstBuilder astBuilder = new AstBuilder(filter);
		return vnfInstancesRepository.query();
	}

	public VnfInstance post(CreateVnfRequest createVnfRequest, String id, VnfInstanceLinks vnfInstanceLinks) {
		final String vnfId = createVnfRequest.getVnfdId();
		vnfPackageRepository.get(vnfId);
		final VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest);

		vnfInstance.setId(id);

		vnfInstance.setLinks(vnfInstanceLinks);
		vnfInstancesRepository.save(vnfInstance);
		return null;
	}

	public void delete(@Nonnull String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState().equals(InstantiationStateEnum.NOT_INSTANTIATED)) {
			vnfInstancesRepository.delete(vnfInstanceId);
		} else {
			throw new ConflictException("VNF final Instance is instantiated.");
		}
	}

	public void instantiate(@Nonnull String vnfInstanceId, InstantiateVnfRequest instantiateVnfRequest) {
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

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
		final long serviceId = 0;

		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, SERVICE_NAME, PROCESS_NAME, varsMap);
			userData.put("msaServiceId", String.valueOf(resp.serviceId.id));
			vnfPkg.setOnboardingState(OnboardingStateEnum.ONBOARDED);
			vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
			vnfPackageRepository.save(vnfPkg);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

	public void terminate(@Nonnull String vnfInstanceId, TerminateVnfRequest terminateVnfRequest) {
		if (terminateVnfRequest.getTerminationType() != TerminationTypeEnum.FORCEFUL) {
			LOG.warn("Terminaison should be set to FORCEFULL.");
		}
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final String vnfPkgId = vnfInstance.getVnfdId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String msaServiceId = userData.get("msaServiceId");

		final String PROCESS_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
		final String SERVICE_NAME = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
		final long serviceId = Long.parseLong(msaServiceId);
		final String customerId = userData.get("customerId");
		final Map<String, String> varsMap = new HashMap<>();

		try {
			final ProcessInstance resp = orchestrationService.scheduleServiceImmediateMode(customerId, serviceId, SERVICE_NAME, PROCESS_NAME, varsMap);
			userData.put("msaServiceId", String.valueOf(resp.serviceId.id));
			vnfPkg.setOnboardingState(OnboardingStateEnum.ONBOARDED);
			vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
			vnfPackageRepository.save(vnfPkg);
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
	}

}
