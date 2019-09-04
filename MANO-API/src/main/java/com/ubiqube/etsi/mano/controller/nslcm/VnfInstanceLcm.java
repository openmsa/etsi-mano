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
import com.ubiqube.etsi.mano.model.vnf.VnfPkgIndex;
import com.ubiqube.etsi.mano.model.vnf.VnfPkgInstance;
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

	public VnfInstanceLcm(final VnfInstancesRepository vnfInstancesRepository, final VnfPackageRepository vnfPackageRepository, final MsaExecutor _msaExecutor, final LcmOpOccsRepository _lcmOpOccsRepository) {
		super();
		this.vnfInstancesRepository = vnfInstancesRepository;
		this.vnfPackageRepository = vnfPackageRepository;
		msaExecutor = _msaExecutor;
		lcmOpOccsMsa = _lcmOpOccsRepository;
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
		final List<VnfPkgInstance> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstance instance = new VnfPkgInstance(vnfInstance.getId());
		instances.add(instance);
		vnfPackageRepository.storeObject(vnfId, vnfPkgIndex, "indexes.json");

		vnfInstance.setLinks(links.getLinks(id));
		return vnfInstance;
	}

	public void delete(@Nonnull final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (vnfInstance.getInstantiationState() == (InstantiationStateEnum.INSTANTIATED)) {
			throw new ConflictException("VNF final Instance is instantiated.");
		}
		// Clean LCM Repository.
		final String vnfPkgId = vnfInstance.getVnfPkgId();
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfPkgId, VnfPkgIndex.class, "indexes.json");
		final List<VnfPkgInstance> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstance instance = getLcmOpOccsInstance(instances, vnfInstanceId);
		instance.getOperations().stream().forEach(x -> lcmOpOccsMsa.delete(x.getId()));
		lcmOpOccsMsa.delete(vnfInstanceId);
		instances.remove(instance);
		vnfPackageRepository.storeObject(vnfPkgId, vnfPkgIndex, "indexes.json");

		if (instances.isEmpty()) {
			final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
			vnfPkg.setUsageState(UsageStateEnum.NOT_IN_USE);
			vnfPackageRepository.save(vnfPkg);
		}
		vnfInstancesRepository.delete(vnfInstanceId);
		// VnfIdentitifierDeletionNotification NFVO + EM
	}

	public void instantiate(@Nonnull final String vnfInstanceId, final InstantiateVnfRequest instantiateVnfRequest, @Nonnull final LcmLinkable links) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);

		if (vnfInstance.getInstantiationState() == InstantiationStateEnum.INSTANTIATED) {
			throw new GenericException("Instance " + vnfInstanceId + " is already instantiated.");
		}

		final String vnfPkgId = vnfInstance.getVnfPkgId();
		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		vnfPkg.setUsageState(UsageStateEnum.IN_USE);
		final Map<String, Object> userData = vnfPkg.getUserDefinedData();

		if (null == userData.get("vimId")) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String processId = msaExecutor.onVnfInstantiate(vnfPkgId, userData);
		LOG.info("New MSA VNF Create job: {}", processId);
		userData.put("msaServiceId", processId);
		addVnfOperation(vnfPkgId, processId, vnfInstanceId, LcmOperationTypeEnum.INSTANTIATE);

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

		final String vnfPkgId = vnfInstance.getVnfPkgId();
		vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);

		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(vnfInstance.getVnfPkgId(), VnfPkgIndex.class, "indexes.json");
		final List<VnfPkgInstance> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstance instance = getLcmOpOccsInstance(instances, vnfInstanceId);

		instance.getOperations().forEach(x -> lcmOpOccsMsa.delete(x.getId()));
		instance.getOperations().clear();

		final VnfPkgInfo vnfPkg = vnfPackageRepository.get(vnfPkgId);
		final Map<String, Object> userData = vnfPkg.getUserDefinedData();
		final String processId = msaExecutor.onVnfInstanceTerminate(userData);
		userData.put("msaTerminateServiceId", processId);
		vnfPackageRepository.storeObject(vnfPkg.getId(), vnfPkgIndex, "indexes.json");
		addVnfOperation(vnfPkgId, processId, vnfInstanceId, LcmOperationTypeEnum.TERMINATE);
		vnfPackageRepository.save(vnfPkg);
		vnfInstancesRepository.save(vnfInstance);
	}

	private static VnfPkgInstance getLcmOpOccsInstance(final List<VnfPkgInstance> _instances, final String _id) {
		return _instances.stream()
				.filter(x -> x.getInstanceId().contentEquals(_id))
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find indexes for Instance " + _id));
	}

	private NsLcmOpOccsNsLcmOpOcc addVnfOperation(final String _vnfPkgId, final String _processId, final String _vnfInstanceId, final LcmOperationTypeEnum _lcmOperationType) {
		final NsLcmOpOccsNsLcmOpOcc lcmOpOccs = LcmFactory.createNsLcmOpOccsNsLcmOpOcc(_vnfInstanceId, _lcmOperationType);
		lcmOpOccsMsa.save(lcmOpOccs);
		final VnfPkgIndex vnfPkgIndex = vnfPackageRepository.loadObject(_vnfPkgId, VnfPkgIndex.class, "indexes.json");
		final VnfPkgOperation VnfPkgOperation = new VnfPkgOperation(lcmOpOccs.getId(), _processId);
		final List<VnfPkgInstance> instances = vnfPkgIndex.getInstances();
		final VnfPkgInstance instance = getLcmOpOccsInstance(instances, _vnfInstanceId);
		instance.getOperations().add(VnfPkgOperation);

		vnfPackageRepository.storeObject(_vnfPkgId, vnfPkgIndex, "indexes.json");
		return lcmOpOccs;
	}

}
