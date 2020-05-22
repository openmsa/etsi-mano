package com.ubiqube.etsi.mano.service.event;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

import ma.glasnost.orika.MapperFacade;

@Service
public class VnfmActions {
	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VnfInstancesRepository vnfInstancesRepository;

	private final VimManager vimManager;

	private final VnfPackageJpa vnfPackageRepository;

	private final EventManager eventManager;

	private final ExecutionPlanner executionPlanner;

	private final PlanExecutor executor;

	private final MapperFacade mapper;

	private final VnfLcmService vnfLcmService;

	private final GrantService grantService;

	private final VnfInstanceService vnfInstancesService;

	public VnfmActions(final VnfInstancesRepository _vnfInstancesRepository, final VimManager _vimManager, final VnfPackageJpa _vnfPackageRepository, final EventManager _eventManager, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor, final MapperFacade _mapper, final VnfLcmService _vnfLcmService, final GrantService _grantService, final VnfInstanceService _vnfInstancesService) {
		super();
		vnfInstancesRepository = _vnfInstancesRepository;
		vimManager = _vimManager;
		vnfPackageRepository = _vnfPackageRepository;
		eventManager = _eventManager;
		executionPlanner = _executionPlanner;
		executor = _executor;
		mapper = _mapper;
		vnfLcmService = _vnfLcmService;
		grantService = _grantService;
		vnfInstancesService = _vnfInstancesService;
	}

	public void vnfInstantiate(@Nonnull final UUID vnfInstanceId) {
		VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);

		// Maybe we need additional parameters to say STARTING / PROCESSING ...
		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("Vnf " + vnfPkgId + " not Found."));
		copyVnfPkgToInstance(vnfPkg, vnfInstance);
		vnfInstance = vnfInstancesRepository.save(vnfInstance);

		VnfLcmOpOccs lcmOpOccs = vnfLcmService.createIntatiateOpOcc(vnfPkg, vnfInstance);
		executionPlanner.makePrePlan(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId(), vnfPkg, vnfInstance, lcmOpOccs);
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);

		// XXX Do it for VnfInfoModifications
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());
		final String instantiationLevelId = vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId();

		// Send processing notification.
		vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.PROCESSING);

		// Send Grant.
		final GrantRequest req = grantService.createInstantiateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		final GrantResponse grantsResp = grantService.sendAndWaitGrantRequest(req);

		createInstanceResources(lcmOpOccs, grantsResp, vnfInstance);
		vnfLcmService.setGrant(lcmOpOccs, grantsResp.getId());
		vnfInstance.setVimConnectionInfo(grantsResp.getVimConnections());
		// Save LCM
		mergeInstanceGrants(vnfInstance, grantsResp);
		vnfInstance = vnfInstancesRepository.save(vnfInstance);
		lcmOpOccs = vnfLcmService.findById(lcmOpOccs.getId());
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		final ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(lcmOpOccs, vnfPkg);
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = grantsResp.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		executionPlanner.exportGraph(plan, vnfPkgId, vnfInstance, "create");

		final ExecutionResults<UnitOfWork, String> results = executor.execCreate(plan, vimConnection, vim);
		setResultLcmInstance(lcmOpOccs, vnfInstance.getId(), results, InstantiationStateEnum.INSTANTIATED);

		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstanceId, lcmOpOccs.getId());
	}

	private void createInstanceResources(final VnfLcmOpOccs lcmOpOccs, final GrantResponse grantsResp, final VnfInstance vnfInstance) {
		// XXX need to remap the vim inside our vim.
		final VimConnectionInformation vimConnectionInformation = vimManager.findVimById(grantsResp.getVimConnections().iterator().next().getId());
		// XXX instantiation level cannot be null
		final VduInstantiationLevel instantiationLevel = null;
		grantsResp.getAddResources().forEach(x -> {
			// Get VNFM Grant Resource information ID.
			final UUID grantUuid = UUID.fromString(x.getResourceDefinitionId());
			final GrantInformation grantInformation = grantService.getGrantInformation(grantUuid).orElseThrow(() -> new NotFoundException("Could not find Grant id: " + grantUuid));
			if (x.getType() == TypeEnum.COMPUTE) {
				vnfLcmService.getAffectedComputeById(UUID.fromString(grantInformation.getResourceDefinitionId())).ifPresent(y -> {
					final VnfInstantiedCompute vnfInstantiedCompute = new VnfInstantiedCompute();
					vnfInstantiedCompute.setResourceProviderId(x.getResourceProviderId());
					vnfInstantiedCompute.setStatus(InstantiationStatusType.NOT_STARTED);
					vnfInstantiedCompute.setVduId(y.getVduId());
					vnfInstantiedCompute.setVimConnectionInformation(vimConnectionInformation);
					vnfInstantiedCompute.setVnfLcmOpOccs(lcmOpOccs);
					vnfInstantiedCompute.setInstantiationLevel(instantiationLevel);
					vnfInstantiedCompute.setVnfCompute(y.getVnfCompute());
					vnfInstancesService.save(vnfInstantiedCompute);
				});
			} else if (x.getType() == TypeEnum.VL) {
				vnfLcmService.getAffectedVirtualLinkById(UUID.fromString(grantInformation.getResourceDefinitionId())).ifPresent(y -> {
					final VnfInstantiedVirtualLink vnfInstantiedVirtualLink = new VnfInstantiedVirtualLink();
					vnfInstantiedVirtualLink.setResourceProviderId(x.getResourceProviderId());
					vnfInstantiedVirtualLink.setStatus(InstantiationStatusType.NOT_STARTED);
					vnfInstantiedVirtualLink.setVduId(y.getVirtualLink().getId());
					vnfInstantiedVirtualLink.setVimConnectionInformation(vimConnectionInformation);
					vnfInstantiedVirtualLink.setVnfLcmOpOccs(lcmOpOccs);
					vnfInstantiedVirtualLink.setInstantiationLevel(instantiationLevel);
					vnfInstantiedVirtualLink.setVnfVirtualLink(y.getVirtualLink());
					vnfInstancesService.save(vnfInstantiedVirtualLink);
				});
			} else if (x.getType() == TypeEnum.LINKPORT) {
				vnfLcmService.getAffectedExtCpById(UUID.fromString(grantInformation.getResourceDefinitionId())).ifPresent(y -> {
					final VnfInstantiedExtCp vnfInstantiedExtCp = new VnfInstantiedExtCp();
					vnfInstantiedExtCp.setResourceProviderId(x.getResourceProviderId());
					vnfInstantiedExtCp.setStatus(InstantiationStatusType.NOT_STARTED);
					vnfInstantiedExtCp.setVduId(y.getExtCp().getId());
					vnfInstantiedExtCp.setVimConnectionInformation(vimConnectionInformation);
					vnfInstantiedExtCp.setVnfLcmOpOccs(lcmOpOccs);
					vnfInstantiedExtCp.setInstantiationLevel(instantiationLevel);
					vnfInstantiedExtCp.setVduId(y.getId());
					vnfInstantiedExtCp.setVnfExtCp(y.getExtCp());
					vnfInstancesService.save(vnfInstantiedExtCp);
				});
			}
			x.getResourceDefinitionId();
		});
	}

	private void setResultLcmInstance(@NotNull final VnfLcmOpOccs lcmOpOccs, @NotNull final UUID vnfInstanceId, final ExecutionResults<UnitOfWork, String> results, @Nonnull final InstantiationStateEnum eventType) {
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		if (results.getErrored().isEmpty()) {
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.INSTANTIATED : InstantiationStateEnum.NOT_INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
		} else {
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.NOT_INSTANTIATED : InstantiationStateEnum.INSTANTIATED);
		}
		vnfInstancesRepository.save(vnfInstance);
	}

	private void copyVnfPkgToInstance(final VnfPackage vnfPkg, final VnfInstance vnfInstance) {
		mapper.map(vnfPkg, vnfInstance);
	}

	private static void mergeInstanceGrants(final VnfInstance vnfInstance, final GrantResponse grants) {
		// XXX Normally we have to remap ExtCP VL but we have those since instantiate.
		grants.getAddResources().forEach(x -> setGrantResource(x, vnfInstance));
		// Map flavor & ImageId.
		vnfInstance.getInstantiatedVnfInfo().getVnfcResourceInfo().forEach(x -> {
			final String flavorId = findFlavor(grants, x.getVduId());
			x.setFlavorId(flavorId);
			final String imageId = findImage(grants, x.getVduId());
			x.setImageId(imageId);
		});
	}

	private static String findImage(final GrantResponse grants, final UUID vduId) {
		return grants.getVimAssets().getSoftwareImages().stream()
				.filter(x -> x.getVnfdSoftwareImageId().equals(vduId.toString()))
				.map(VimSoftwareImageEntity::getVimSoftwareImageId)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find ImageId: " + vduId));
	}

	private static String findFlavor(final GrantResponse grants, final UUID vduId) {
		return grants.getVimAssets().getComputeResourceFlavours().stream()
				.filter(x -> x.getVnfdVirtualComputeDescId().equals(vduId.toString()))
				.map(VimComputeResourceFlavourEntity::getVimFlavourId)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("Could not find flavor: " + vduId));

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
			final VnfInstantiedCompute res = resCompute.get();
			res.setReservationId(grantInformation.getReservationId());
			res.setResourceGroupId(grantInformation.getResourceGroupId());
			res.setZoneId(grantInformation.getZoneId());
			res.setResourceProviderId(grantInformation.getResourceProviderId());
			final VimConnectionInformation vimConnectionInformation = new VimConnectionInformation();
			vimConnectionInformation.setId(UUID.fromString(grantInformation.getVimConnectionId()));
			res.setVimConnectionInformation(vimConnectionInformation);
			return;
		}
		LOG.warn("Unable to find resource: {}", grantInformation.getVduId());
	}

	public void vnfTerminate(@Nonnull final UUID vnfInstanceId) {
		LOG.info("Eecuting Terminate on instance {}", vnfInstanceId);
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("Vnf " + vnfPkgId + " not Found."));

		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.createTerminateOpOcc(vnfPkg, vnfInstance);

		// XXX Do it for VnfInfoModifications
		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, vnfInstance.getId());
		final GrantResponse grant = getTerminateGrants(vnfInstance, lcmOpOccs, vnfPkg);
		vnfLcmService.setGrant(lcmOpOccs, grant.getId());
		// Make plan
		ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(lcmOpOccs, vnfPkg);
		final VimConnectionInformation vimConnection = grant.getVimConnections().iterator().next();
		// XXX Multiple Vim ?
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		plan = executionPlanner.revert(plan);
		executionPlanner.exportGraph(plan, vnfPkgId, vnfInstance, "delete");

		final ExecutionResults<UnitOfWork, String> results = executor.execDelete(plan, vimConnection, vim);
		setResultLcmInstance(lcmOpOccs, vnfInstance.getId(), results, InstantiationStateEnum.NOT_INSTANTIATED);

		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstanceId, lcmOpOccs.getId());
	}

	private GrantResponse getTerminateGrants(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPkg) {
		final GrantRequest req = grantService.createTerminateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		return grantService.sendAndWaitGrantRequest(req);
	}

}
