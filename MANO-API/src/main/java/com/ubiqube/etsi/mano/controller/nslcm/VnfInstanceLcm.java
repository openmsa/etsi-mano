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
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
import com.ubiqube.etsi.mano.model.nslcm.sol003.CreateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.InstantiateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest;
import com.ubiqube.etsi.mano.model.nslcm.sol003.TerminateVnfRequest.TerminationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfInstance.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.LcmOperationTypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc.OperationParamsEnum;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstances;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgOperation;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.UsageStateEnum;
import com.ubiqube.etsi.mano.repository.LcmOpOccsRepository;
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
	private final LcmOpOccsRepository lcmOpOccsMsa;

	public VnfInstanceLcm(final VnfInstancesRepository vnfInstancesRepository, final VnfPackageRepository vnfPackageRepository, final MsaExecutor _msaExecutor, final LcmOpOccsRepository _lcmOpOccsMsa) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		msaExecutor = _msaExecutor;
		lcmOpOccsMsa = _lcmOpOccsMsa;
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
		if (!vnfPkgInfo.getOnboardingState().equals(OnboardingStateEnum.ONBOARDED.value())) {
			throw new ConflictException("VNF Package " + vnfPkgInfo.getId() + " is not ONBOARDED.");
		}
		final VnfInstance vnfInstance = LcmFactory.createVnfInstance(createVnfRequest);

		vnfInstance.setId(id);
		// VnfIdentifierCreationNotification NFVO + EM
		vnfInstancesRepository.save(vnfInstance);
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfId, VnfPkgIndex.class, "indexes.json");
		final List<VnfPkgInstances> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstances instance = new VnfPkgInstances(vnfInstance.getId());
		instances.add(instance);
		vnfPackageRepository.storeObject(vnfId, vnfPkgIndex, "indexes.json");

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
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfInstance.getVnfPkgId(), VnfPkgIndex.class, "indexes.json");
		final List<VnfPkgInstances> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstances instance = instances.stream()
				.filter(x -> x.getInstanceId().contentEquals(vnfInstanceId))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find indexes for Instance " + vnfInstanceId));

		if (vnfInstance.getInstantiationState() == InstantiationStateEnum.INSTANTIATED) {
			throw new GenericException("Instance " + vnfInstanceId + " is already instantiated.");
		}
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(vnfInstanceId, LcmOperationTypeEnum.INSTANTIATE, OperationParamsEnum.INSTANTIATE);
		lcmOpOccsMsa.save(lcmOpOccs);

		final String vnfPkgId = vnfInstance.getVnfPkgId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		vnfPkg.setUsageState(UsageStateEnum.IN_USE);
		final Map<String, Object> userData = (Map<String, Object>) vnfPkg.getUserDefinedData();

		final String vimConnection = (String) userData.get("vimId");
		if (null == vimConnection) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String ret = msaExecutor.onVnfInstantiate(vnfPkgId, userData);
		LOG.info("New MSA VNF Create job: {}", ret);
		userData.put("msaServiceId", ret);
		final VnfPkgOperation VnfPkgOperation = new VnfPkgOperation(lcmOpOccs.getId(), ret);
		instance.getOperations().add(VnfPkgOperation);

		vnfPackageRepository.storeObject(vnfPkgId, vnfPkgIndex, "indexes.json");
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
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(vnfInstanceId, LcmOperationTypeEnum.INSTANTIATE, OperationParamsEnum.INSTANTIATE);
		lcmOpOccsMsa.save(lcmOpOccs);

		final String vnfPkgId = vnfInstance.getVnfPkgId();
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);

		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfInstance.getVnfPkgId(), VnfPkgIndex.class, "indexes.json");
		final List<VnfPkgInstances> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstances instance = instances.stream()
				.filter(x -> x.getInstanceId().contentEquals(vnfInstanceId))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find indexes for Instance " + vnfInstanceId));

		instance.getOperations().forEach(x -> lcmOpOccsMsa.delete(x.getId()));
		instance.getOperations().clear();

		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, String> userData = (Map<String, String>) vnfPkg.getUserDefinedData();
		final String ret = msaExecutor.onVnfInstanceTerminate(userData);
		userData.put("msaTerminateServiceId", ret);
		instance.getOperations().add(new VnfPkgOperation(lcmOpOccs.getId(), ret));
		if (instances.size() <= 1) {
			vnfPkg.setUsageState(UsageStateEnum.NOT_IN_USE);
		}

		vnfPackageRepository.save(vnfPkg);
		vnfPackageRepository.storeObject(vnfPkg.getId(), vnfPkgIndex, "indexes.json");
		vnfInstancesRepository.save(vnfInstance);
		vnfInstance.setLinks(links.getLinks(vnfInstanceId));
	}

}
