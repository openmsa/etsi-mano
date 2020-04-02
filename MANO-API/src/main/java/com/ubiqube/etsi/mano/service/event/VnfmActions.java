package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.Grants;
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
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.service.vim.VimStatus;

@Service
public class VnfmActions {
	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VnfInstancesRepository vnfInstancesRepository;

	private final VimManager vimManager;

	private final VnfPackageJpa vnfPackageRepository;

	private final EventManager eventManager;

	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;

	private final GrantManagement grantManagement;

	public VnfmActions(final VnfInstancesRepository _vnfInstancesRepository, final VimManager _vimManager, final VnfPackageJpa _vnfPackageRepository, final EventManager _eventManager, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final GrantManagement _grantManagement) {
		super();
		vnfInstancesRepository = _vnfInstancesRepository;
		vimManager = _vimManager;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		grantManagement = _grantManagement;
	}

	public void vnfInstantiate(@Nonnull final String vnfInstanceId) {
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
		Grants grants = grantManagement.post(grantRequest);
		lcmOpOccs.setGrantId(grants.getId().toString());
		vnfLcmOpOccsRepository.save(lcmOpOccs);
		grants = pollGrants(grants);

		// Instantiate VDU.
		final Set<GrantInformation> addVdu = grants.getAddResources();
		for (final GrantInformation grantInformation : addVdu) {
			final VimStatus status = spawnVdu(grantInformation, vnfPkg, vnfInstance, lcmOpOccs);
			if (status.getLcmOperationStateType() == LcmOperationStateType.FAILED) {
				lcmOpOccs.setError(status.getProblemDetails());
				lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
				vnfLcmOpOccsRepository.save(lcmOpOccs);
				break;
			}
		}
		LOG.info("VNF instance {} Finished.", vnfInstanceId);
	}

	private Grants pollGrants(final Grants grants) {
		int counter = 50;
		while (counter > 0) {
			final Grants grantOpt = grantManagement.get(grants.getId());
			if (grantOpt.getAvailable()) {
				return grantOpt;
			}
			LOG.debug("Grant ID {} not ready.", grants.getId());
			counter--;
			try {
				Thread.sleep(5 * 1000);
			} catch (final InterruptedException e) {
				throw new GenericException(e);
			}
		}
		throw new GenericException("Unable to get grant ID " + grants.getId());
	}

	private VimStatus spawnVdu(final GrantInformation grantInformation, final VnfPackage vnfPackage, final VnfInstance vnfInstance, @Nonnull final VnfLcmOpOcc lcmOpOccs) {
		final Vim vim = vimManager.getVimById(UUID.fromString(grantInformation.getVimConnectionId()));
		final String processId = vim.onVnfInstantiate(grantInformation, vnfPackage);
		LOG.info("New VDU VNF Create job: {}", processId);
		vnfInstance.setProcessId(processId);
		vnfInstancesRepository.save(vnfInstance);
		final VimStatus status = vim.waitForCompletion(processId, 1 * 60);
		vnfLcmOpOccsRepository.updateState(lcmOpOccs, status.getLcmOperationStateType());

		if (status.getLcmOperationStateType() == LcmOperationStateType.COMPLETED) {
			vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
			vnfPackage.setUsageState(PackageUsageStateType.IN_USE);
			final VnfInstanceInstantiatedVnfInfo instantiatedVnfInfo = new VnfInstanceInstantiatedVnfInfo();
			instantiatedVnfInfo.setVnfState(VnfOperationalStateType.STARTED);
			vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
		} else {
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			lcmOpOccs.setError(status.getProblemDetails());
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.FAILED);

		}
		vnfInstancesRepository.save(vnfInstance);
		vnfPackageRepository.save(vnfPackage);
		return status;
	}

	private static GrantRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOcc lcmOpOccs, final VnfPackage vnfPackage) {
		final GrantRequest grant = new GrantRequest();
		grant.setVnfInstanceId(vnfInstance.getId().toString());
		grant.setVnfLcmOpOccId(lcmOpOccs.getId());
		grant.setVnfdId(vnfInstance.getVnfdId());
		grant.setFlavourId(vnfPackage.getFlavorId());
		grant.setIsAutomaticInvocation(Boolean.FALSE);
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
		resourceDefinition.setType(TypeEnum.STORAGE);
		resourceDefinition.setVduId(vnfStorage.getId().toString());
		return resourceDefinition;
	}

	private static ResourceDefinition mapLinkPort(final VnfLinkPort vnfLinkPort) {
		final ResourceDefinition resourceDefinition = new ResourceDefinition();
		resourceDefinition.setType(TypeEnum.LINKPORT);
		resourceDefinition.setVduId(vnfLinkPort.getId().toString());
		return resourceDefinition;
	}

	private static ResourceDefinition mapVl(final VnfVl vnfVl) {
		final ResourceDefinition resourceDefinition = new ResourceDefinition();
		resourceDefinition.setType(TypeEnum.VL);
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
