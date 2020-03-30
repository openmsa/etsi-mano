package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.LcmGrants;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantedLcmOperationType;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationType;
import com.ubiqube.etsi.mano.model.nslcm.VnfInstanceInstantiatedVnfInfo;
import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.service.Vim;

@Service
public class VnfmActions {
	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VnfInstancesRepository vnfInstancesRepository;

	private final Vim msaExecutor;

	private final VnfPackageJpa vnfPackageRepository;

	private final EventManager eventManager;

	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	private final LcmGrants lcmGrantsSol003;

	public VnfmActions(final VnfInstancesRepository _vnfInstancesRepository, final Vim _vim, final VnfPackageJpa _vnfPackageRepository, final EventManager _eventManager, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final LcmGrants _lcmGrantsSol003) {
		super();
		vnfInstancesRepository = _vnfInstancesRepository;
		msaExecutor = _vim;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		lcmGrantsSol003 = _lcmGrantsSol003;
	}

	public void vnfInstantiate(final String vnfInstanceId) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		// Maybe e need additional parameters to say STARTING / PROCESSING ...
		final VnfLcmOpOcc lcmOpOccs = vnfLcmOpOccsRepository.createLcmOpOccs(vnfInstanceId, LcmOperationType.INSTANTIATE);
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId().toString());
		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final Optional<VnfPackage> vnfPkgOpt = vnfPackageRepository.findById(vnfPkgId);
		final VnfPackage vnfPkg = vnfPkgOpt.orElseThrow(() -> new NotFoundException("Vnf " + vnfPkgId + " not Found."));
		// Send processing notification.
		vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.PROCESSING);
		// Send Grant.
		final GrantRequest grantRequest = createGrant(vnfInstance, lcmOpOccs, vnfPkg);

		// XXX: vimId came from Grant.
		final Map<String, Object> userData = new HashMap<>();
		if (null == userData.get("vimId")) {
			throw new GenericException("No vim information for VNF Instance: " + vnfInstanceId);
		}

		final String processId = msaExecutor.onVnfInstantiate(vnfPkgId.toString(), userData);
		LOG.info("New MSA VNF Create job: {}", processId);
		vnfInstance.setProcessId(processId);
		vnfInstancesRepository.save(vnfInstance);
		final LcmOperationStateType status = msaExecutor.waitForCompletion(processId, 1 * 60);
		vnfLcmOpOccsRepository.updateState(lcmOpOccs, status);

		if (status == LcmOperationStateType.COMPLETED) {
			vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
			vnfPkg.setUsageState(PackageUsageStateType.IN_USE);
			final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = new VnfInstanceInstantiatedVnfInfo();
			instantiatedVnfInfo.setVnfState(VnfOperationalStateType.STARTED);
			vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
		} else {
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
		}
		vnfInstancesRepository.save(vnfInstance);
		vnfPackageRepository.save(vnfPkg);
	}

	private static GrantRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOcc lcmOpOccs, final VnfPackage vnfPackage) {
		final GrantRequest grant = new GrantRequest();
		grant.setVnfInstanceId(vnfInstance.getId().toString());
		grant.setVnfLcmOpOccId(lcmOpOccs.getId().toString());
		grant.setVnfdId(vnfInstance.getVnfdId());
		grant.setFlavourId(vnfPackage.getFlavorId());
		grant.setIsAutomaticInvocation(false);
		grant.setOperation(GrantedLcmOperationType.INSTANTIATE);
		/// XXX: Have a closer look on lcm_operations_configuration or vnf_profile.
		grant.setInstantiationLevelId("0");
		final List<ResourceDefinition> addResources = new ArrayList<>();
		final Set<VnfCompute> compute = vnfPackage.getVnfCompute();
		final List<ResourceDefinition> listCompute = compute.stream().map(VnfmActions::mapCompute).collect(Collectors.toList());
		addResources.addAll(listCompute);

		final Set<VnfVl> vl = vnfPackage.getVnfVl();
		final List<ResourceDefinition> listVl = vl.stream().map(VnfmActions::mapVl).collect(Collectors.toList());
		addResources.addAll(listVl);

		final Set<VnfLinkPort> linkPort = vnfPackage.getVnfLinkPort();
		final List<ResourceDefinition> listLinkPort = linkPort.stream().map(VnfmActions::mapLinkPort).collect(Collectors.toList());
		addResources.addAll(listLinkPort);

		final Set<VnfStorage> storage = vnfPackage.getVnfStorage();
		final List<ResourceDefinition> listStorage = storage.stream().map(VnfmActions::mapStorage).collect(Collectors.toList());
		addResources.addAll(listStorage);
		grant.setAddResources(addResources);
		return grant;
	}

	private static ResourceDefinition mapStorage(final VnfStorage vnfStorage) {
		final ResourceDefinition resourceDefinition = new ResourceDefinition();
		resourceDefinition.setType(TypeEnum.COMPUTE);
		resourceDefinition.setVduId(vnfStorage.getId().toString());
		return resourceDefinition;
	}

	private static ResourceDefinition mapLinkPort(final VnfLinkPort vnfLinkPort) {
		final ResourceDefinition resourceDefinition = new ResourceDefinition();
		resourceDefinition.setType(TypeEnum.COMPUTE);
		resourceDefinition.setVduId(vnfLinkPort.getId().toString());
		return resourceDefinition;
	}

	private static ResourceDefinition mapVl(final VnfVl vnfVl) {
		final ResourceDefinition resourceDefinition = new ResourceDefinition();
		resourceDefinition.setType(TypeEnum.COMPUTE);
		resourceDefinition.setVduId(vnfVl.getId().toString());
		return resourceDefinition;
	}

	private static ResourceDefinition mapCompute(final VnfCompute vnfCompute) {
		final ResourceDefinition resourceDefinition = new ResourceDefinition();
		resourceDefinition.setType(TypeEnum.COMPUTE);
		resourceDefinition.setVduId(vnfCompute.getId().toString());
		return resourceDefinition;
	}
}
