package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.MsaExecutor;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

@Service
public class VnfInstanceLcm {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceLcm.class);

	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageRepository vnfPackageRepository;
	private final MsaExecutor msaExecutor;

	public VnfInstanceLcm(VnfInstancesRepository vnfInstancesRepository, VnfPackageRepository vnfPackageRepository, MsaExecutor _msaExecutor) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		msaExecutor = _msaExecutor;
	}

	public List<VnfInstance> get(Map<String, String> queryParameters, LcmLinkable links) {
		final String filter = queryParameters.get("filter");
		final List<VnfInstance> result = vnfInstancesRepository.query(filter);
		for (final VnfInstance vnfInstance : result) {
			vnfInstance.setLinks(links.getLinks(vnfInstance.getId()));
		}
		return result;
	}

	public VnfInstance post(CreateVnfRequest createVnfRequest, String id, LcmLinkable links) {
		final String vnfId = createVnfRequest.getVnfdId();
		vnfPackageRepository.get(vnfId);
		final VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest);

		vnfInstance.setId(id);

		vnfInstancesRepository.save(vnfInstance);
		vnfInstance.setLinks(links.getLinks(id));
		return vnfInstance;
	}

	public void delete(@Nonnull String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState().equals(InstantiationStateEnum.NOT_INSTANTIATED)) {
			vnfInstancesRepository.delete(vnfInstanceId);
		} else {
			throw new ConflictException("VNF final Instance is instantiated.");
		}
	}

	public void instantiate(@Nonnull String vnfInstanceId, InstantiateVnfRequest instantiateVnfRequest, @Nonnull LcmLinkable links) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final String vnfPkgId = vnfInstance.getVnfdId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String vimConnection = userData.get("vimId");
		if (null == vimConnection) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String ret = msaExecutor.onInstantiate(vnfPkgId, userData);

		userData.put("msaServiceId", ret);
		vnfPkg.setOnboardingState(OnboardingStateEnum.ONBOARDED);
		vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
		vnfPackageRepository.save(vnfPkg);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
	}

	public void terminate(@Nonnull String vnfInstanceId, TerminateVnfRequest terminateVnfRequest, @Nonnull LcmLinkable links) {
		if (terminateVnfRequest.getTerminationType() != TerminationTypeEnum.FORCEFUL) {
			LOG.warn("Terminaison should be set to FORCEFULL.");
		}
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final String vnfPkgId = vnfInstance.getVnfdId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String ret = msaExecutor.onInstanceTerminate(userData);
		userData.put("msaTerminateServiceId", ret);
		vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
		vnfPackageRepository.save(vnfPkg);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
	}

}
