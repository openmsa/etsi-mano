package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.controller.lcmgrant.GrantManagement;
import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;
import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.LcmFactory;
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
import com.ubiqube.etsi.mano.model.vnf.PackageUsageStateType;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.UnitOfWork;
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

	private final ExecutionPlanner executionPlanner;
	private final PlanExecutor executor;

	public VnfmActions(final VnfInstancesRepository _vnfInstancesRepository, final VimManager _vimManager, final VnfPackageJpa _vnfPackageRepository, final EventManager _eventManager, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final GrantManagement _grantManagement, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor) {
		super();
		vnfInstancesRepository = _vnfInstancesRepository;
		vimManager = _vimManager;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		grantManagement = _grantManagement;
		executionPlanner = _executionPlanner;
		executor = _executor;
	}

	public void vnfInstantiate(@Nonnull final String vnfInstanceId) {
		@SuppressWarnings("null")
		VnfInstance vnfInstance = vnfInstancesRepository.get(UUID.fromString(vnfInstanceId));
		// Maybe we need additional parameters to say STARTING / PROCESSING ...
		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("Vnf " + vnfPkgId + " not Found."));
		copyVnfPkgToInstance(vnfPkg, vnfInstance);
		vnfInstance = vnfInstancesRepository.save(vnfInstance);

		final VnfLcmOpOccs lcmOpOccs = LcmFactory.createVnfLcmOpOccs(LcmOperationType.INSTANTIATE, UUID.fromString(vnfInstanceId));
		copyVnfPkgToLcm(vnfPkg, lcmOpOccs);
		copyVnfInstanceToLcmOpOccs(vnfInstance, lcmOpOccs);
		vnfLcmOpOccsRepository.save(lcmOpOccs);

		// XXX Do it for VnfInfoModifications
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId().toString());

		// Send processing notification.
		vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.PROCESSING);

		// Send Grant.
		final Grants grants = getGrants(vnfInstance, lcmOpOccs, vnfPkg);
		vnfInstance.setVimConnectionInfo(grants.getVimConnections());
		lcmOpOccs.setGrantId(grants.getId().toString());
		// Save LCM
		mergeInstanceGrants(vnfInstance, grants);
		vnfInstancesRepository.save(vnfInstance);
		final ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(vnfInstance, vnfPkg);
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = grants.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		executionPlanner.exportGraph(plan, vnfPkgId, vnfInstance);
		final ExecutionResults<UnitOfWork, String> results = executor.exec(plan, vimConnection, vim);
		if (results.getErrored().isEmpty()) {
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
			vnfInstance.setInstantiationState(InstantiationStateEnum.INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
			vnfInstancesRepository.save(vnfInstance);
		} else {
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			vnfInstancesRepository.save(vnfInstance);
		}
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstanceId, lcmOpOccs.getId());
	}

	private static void copyVnfInstanceToLcmOpOccs(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs) {
		final VnfInstantiatedInfo inst = vnfInstance.getInstantiatedVnfInfo();
		inst.getVnfcResourceInfo().forEach(x -> {
			final AffectedCompute affected = findLcmOpOccsCompute(lcmOpOccs.getResourceChanges().getAffectedVnfcs(), x.getVduId());
			affected.setComputeResource(x.getCompResource());
		});

		inst.getVirtualLinkResourceInfo().forEach(x -> {
			final AffectedVl affected = findLcmOpOccsVl(lcmOpOccs.getResourceChanges().getAffectedVirtualLinks(), x.getVnfVirtualLinkDescId());
			affected.setNetworkResource(x.getNetworkResource());
		});

		inst.getVirtualStorageResourceInfo().forEach(x -> {
			final AffectedVs affected = findLcmOpOccsStorage(lcmOpOccs.getResourceChanges().getAffectedVirtualStorages(), x.getVirtualStorageDescId());
			affected.setStorageResource(x.getStorageResource());
		});
	}

	private static AffectedVs findLcmOpOccsStorage(final Set<AffectedVs> affectedVirtualStorages, final UUID id) {
		return affectedVirtualStorages.stream()
				.filter(x -> x.getVirtualStorageDescId().compareTo(id) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find compute resource: " + id));
	}

	private static AffectedVl findLcmOpOccsVl(final Set<AffectedVl> affectedVirtualLinks, final UUID id) {
		return affectedVirtualLinks.stream()
				.filter(x -> x.getVirtualLinkDescId().compareTo(id) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find compute resource: " + id));
	}

	private static AffectedCompute findLcmOpOccsCompute(final Set<AffectedCompute> affectedVnfcs, final UUID id) {
		return affectedVnfcs.stream()
				.filter(x -> x.getVduId().compareTo(id) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find compute resource: " + id));
	}

	private static void copyVnfPkgToInstance(final VnfPackage vnfPkg, final VnfInstance vnfInstance) {
		final VnfInstantiatedInfo instantiedVnfInfo = vnfInstance.getInstantiatedVnfInfo();
		vnfPkg.getVnfCompute().forEach(x -> {
			final VnfInstantiedCompute instatiedCompute = new VnfInstantiedCompute();
			instatiedCompute.setVduId(x.getId());
			instatiedCompute.setStorageResourceIds(new ArrayList<>(x.getStorages()));
			final ResourceHandleEntity resource = new ResourceHandleEntity();
			resource.setVduId(x.getId());
			instatiedCompute.setCompResource(resource);
			instantiedVnfInfo.addVnfcResourceInfoItem(instatiedCompute);
		});

		vnfPkg.getVnfVl().forEach(x -> {
			final VirtualLinkInfo virtualLink = new VirtualLinkInfo();
			virtualLink.setVnfVirtualLinkDescId(x.getId());
			final ResourceHandleEntity resource = new ResourceHandleEntity();
			resource.setVduId(x.getId());
			virtualLink.setNetworkResource(resource);
			instantiedVnfInfo.addVirtualLinkResourceInfoItem(virtualLink);
		});

		vnfPkg.getVnfStorage().forEach(x -> {
			final VirtualStorageInfo vStorage = new VirtualStorageInfo();
			vStorage.setVirtualStorageDescId(x.getId());
			final ResourceHandleEntity resource = new ResourceHandleEntity();
			resource.setVduId(x.getId());
			vStorage.setStorageResource(resource);
			instantiedVnfInfo.addVirtualStorageResourceInfoItem(vStorage);
		});
	}

	private static void copyVnfPkgToLcm(final VnfPackage vnfPkg, final VnfLcmOpOccs lcmOpOccs) {
		final VnfLcmResourceChanges changedResources = lcmOpOccs.getResourceChanges();

		vnfPkg.getVnfCompute().forEach(x -> {
			final AffectedCompute affectedCompute = new AffectedCompute();
			affectedCompute.setAddedStorageResourceIds(x.getStorages());
			// XXX affectedCompute.setAffectedVnfcCpIds(affectedVnfcCpIds);
			affectedCompute.setChangeType(ChangeType.ADDED);
			affectedCompute.setVduId(x.getId());
			changedResources.addAffectedVnfcs(affectedCompute);
		});
		vnfPkg.getVnfVl().forEach(x -> {
			final AffectedVl affectedVirtualLink = new AffectedVl();
			affectedVirtualLink.setChangeType(ChangeType.ADDED);
			affectedVirtualLink.setVirtualLinkDescId(x.getId());
			changedResources.addAffectedVirtualLink(affectedVirtualLink);
		});
		vnfPkg.getVnfStorage().forEach(x -> {
			final AffectedVs affectedVs = new AffectedVs();
			affectedVs.setChangeType(ChangeType.ADDED);
			affectedVs.setVirtualStorageDescId(x.getId());
			changedResources.addAffectedVirtualStorage(affectedVs);
		});
	}

	private static void mergeInstanceGrants(final VnfInstance vnfInstance, final Grants grants) {
		grants.getAddResources().forEach(x -> setGrantResource(x, vnfInstance));
		// Map flavor.
		vnfInstance.getInstantiatedVnfInfo().getVnfcResourceInfo().forEach(x -> {
			final String flavorId = findFlavor(grants, x.getVduId());
			x.setFlavorId(flavorId);
		});
	}

	private static String findFlavor(final Grants grants, final UUID vduId) {
		grants.getVimAssets().getComputeResourceFlavours().forEach(x -> {
			x.getVnfdVirtualComputeDescId();
		});
		return null;
	}

	private static void setGrantResource(final GrantInformation grantInformation, final VnfInstance vnfInstance) {
		final Optional<VirtualLinkInfo> resVlr = vnfInstance.getInstantiatedVnfInfo().getVirtualLinkResourceInfo().stream().filter(x -> 0 == x.getVnfVirtualLinkDescId().compareTo(grantInformation.getVduId())).findFirst();
		if (resVlr.isPresent()) {
			resVlr.get().setGrantInformation(grantInformation);
			return;
		}

		final Optional<VirtualStorageInfo> resVsr = vnfInstance.getInstantiatedVnfInfo().getVirtualStorageResourceInfo().stream().filter(x -> 0 == x.getVirtualStorageDescId().compareTo(grantInformation.getVduId())).findFirst();
		if (resVsr.isPresent()) {
			resVsr.get().setReservationId(grantInformation.getReservationId());
			return;
		}

		final Optional<VnfInstantiedCompute> resCompute = vnfInstance.getInstantiatedVnfInfo().getVnfcResourceInfo().stream().filter(x -> 0 == x.getVduId().compareTo(grantInformation.getVduId())).findFirst();
		if (resCompute.isPresent()) {
			resCompute.get().setComputeResource(grantInformation);
			return;
		}
		LOG.warn("Unable to find resource: {}", grantInformation.getVduId());
	}

	private Grants pollGrants(final Grants grants) {
		int counter = 50;
		while (counter > 0) {
			final Grants grantOpt = grantManagement.get(grants.getId());
			if (Boolean.TRUE.equals(grantOpt.getAvailable())) {
				return grantOpt;
			}
			LOG.debug("Grant ID {} not ready.", grants.getId());
			counter--;
			try {
				Thread.sleep(5 * 1000L);
			} catch (final InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new GenericException(e);
			}
		}
		throw new GenericException("Unable to get grant ID " + grants.getId());
	}

	private Grants getGrants(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPkg) {
		final GrantRequest grantRequest = createGrant(vnfInstance, lcmOpOccs, vnfPkg);
		Grants grants = grantManagement.post(grantRequest);
		lcmOpOccs.setGrantId(grants.getId().toString());
		vnfLcmOpOccsRepository.save(lcmOpOccs);
		grants = pollGrants(grants);
		return grants;
	}

	private VimStatus spawnVdu(final GrantInformation grantInformation, final VnfPackage vnfPackage, final VnfInstance vnfInstance, @NotNull final VnfLcmOpOccs lcmOpOccs) {
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
			// XXX vnfInstance.setInstantiatedVnfInfo(instantiatedVnfInfo);
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
		} else {
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			lcmOpOccs.setError(status.getProblemDetails());
			vnfLcmOpOccsRepository.updateState(lcmOpOccs, LcmOperationStateType.FAILED);

		}
		vnfInstancesRepository.save(vnfInstance);
		vnfPackageRepository.save(vnfPackage);
		LOG.debug("VDU Done {}", status.getLcmOperationStateType());
		return status;
	}

	private static GrantRequest createGrant(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPackage) {
		final GrantRequest grant = new GrantRequest();
		grant.setVnfInstanceId(vnfInstance.getId().toString());
		grant.setVnfLcmOpOccId(lcmOpOccs.getId().toString());
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

		/*
		 * final Set<VnfLinkPort> linkPort = vnfPackage.getVnfLinkPort(); final
		 * List<ResourceDefinition> listLinkPort =
		 * linkPort.stream().map(VnfmActions::mapLinkPort).collect(Collectors.toList());
		 * addResources.addAll(listLinkPort);
		 */
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
