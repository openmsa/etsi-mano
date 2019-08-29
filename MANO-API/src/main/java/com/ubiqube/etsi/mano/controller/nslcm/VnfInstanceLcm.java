package com.ubiqube.etsi.mano.controller.nslcm;

import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
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

/**
 * NFVO+VNFM & VNFM Implementation.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Profile({ "default", "VNFM" })
@Service
public class VnfInstanceLcm {

	private static final Logger LOG = LoggerFactory.getLogger(VnfInstanceLcm.class);

	private final VnfInstancesRepository vnfInstancesRepository;
	private final VnfPackageRepository vnfPackageRepository;
	private final MsaExecutor msaExecutor;

	public VnfInstanceLcm(final VnfInstancesRepository vnfInstancesRepository, final VnfPackageRepository vnfPackageRepository, final MsaExecutor _msaExecutor) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		msaExecutor = _msaExecutor;
	}

	public List<VnfInstance> get(final Map<String, String> queryParameters, final LcmLinkable links) {
		final String filter = queryParameters.get("filter");
		final List<VnfInstance> result = vnfInstancesRepository.query(filter);
		result.stream().forEach(x -> x.setLinks(links.getLinks(x.getId())));
		return result;
	}

	public VnfInstance post(final CreateVnfRequest createVnfRequest, final String id, final LcmLinkable links) {
		final String vnfId = createVnfRequest.getVnfdId();
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfId);
		if (vnfPkgInfo.getOnboardingState().equals(OnboardingStateEnum.ONBOARDED.value())) {
			throw new ConflictException("VNF Package " + vnfPkgInfo.getId() + " is not ONBOARDED.");
		}
		final VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest);

		vnfInstance.setId(id);
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstancesRepository.save(vnfInstance);
		vnfInstance.setLinks(links.getLinks(id));
		return vnfInstance;
	}

	public void delete(@Nonnull final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState() != (InstantiationStateEnum.INSTANTIATED)) {
			vnfInstancesRepository.delete(vnfInstanceId);
		} else {
			throw new ConflictException("VNF final Instance is instantiated.");
		}
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	public void instantiate(@Nonnull final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest, @Nonnull final LcmLinkable links) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState() == InstantiationStateEnum.INSTANTIATED) {
			throw new GenericException("Instance " + vnfInstanceId + " is already instantiated.");
		}
		final String vnfPkgId = vnfInstance.getVnfdId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String vimConnection = userData.get("vimId");
		if (null == vimConnection) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String ret = msaExecutor.onVnfInstantiate(vnfPkgId, userData);

		userData.put("msaServiceId", ret);
		vnfPackageRepository.save(vnfPkg);
		vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
		vnfInstancesRepository.save(vnfInstance);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
	}

	public void terminate(@Nonnull final String vnfInstanceId, final TerminateVnfRequest terminateVnfRequest, @Nonnull final LcmLinkable links) {
		if (terminateVnfRequest.getTerminationType() != TerminationTypeEnum.FORCEFUL) {
			LOG.warn("Terminaison should be set to FORCEFULL.");
		}
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState() != InstantiationStateEnum.INSTANTIATED) {
			throw new GenericException("Instance " + vnfInstanceId + " is not instantiated.");
		}
		final String vnfPkgId = vnfInstance.getVnfdId();
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);

		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String ret = msaExecutor.onVnfInstanceTerminate(userData);
		userData.put("msaTerminateServiceId", ret);
		vnfPkg.setOperationalState(com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OperationalStateEnum.ENABLED);
		vnfPackageRepository.save(vnfPkg);
		vnfInstancesRepository.save(vnfInstance);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
	}

}
