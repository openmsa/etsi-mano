package com.ubiqube.etsi.mano.service.event;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

@Service
public class VnfmActions {
	private static final Logger LOG = LoggerFactory.getLogger(VnfmActions.class);

	private final VimManager vimManager;

	private final EventManager eventManager;

	private final ExecutionPlanner executionPlanner;

	private final PlanExecutor executor;

	private final VnfLcmService vnfLcmService;

	private final GrantService grantService;

	private final VnfInstanceService vnfInstancesService;

	private final VnfPackageService vnfPackageService;

	public VnfmActions(final VimManager _vimManager, final VnfPackageService _vnfPackageService, final EventManager _eventManager, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor, final VnfLcmService _vnfLcmService, final GrantService _grantService, final VnfInstanceService _vnfInstancesService) {
		super();
		vimManager = _vimManager;
		vnfPackageService = _vnfPackageService;
		eventManager = _eventManager;
		executionPlanner = _executionPlanner;
		executor = _executor;
		vnfLcmService = _vnfLcmService;
		grantService = _grantService;
		vnfInstancesService = _vnfInstancesService;
	}

	public void vnfInstantiate(@Nonnull final UUID lcmOpOccsId) {
		// Parameters are in the lcmOpOccs.
		VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg());
		executionPlanner.makePrePlan(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId(), vnfPkg, vnfInstance, lcmOpOccs);
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);

		// XXX Do it for VnfInfoModifications
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());

		// Send Grant.
		final GrantRequest req = grantService.createInstantiateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		final GrantResponse grantsResp = grantService.sendAndWaitGrantRequest(req);
		// Send processing notification.
		vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.PROCESSING);
		// XXX Send processing event.

		copyGrantResourcesToInstantiated(lcmOpOccs, grantsResp);
		// lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		vnfLcmService.setGrant(lcmOpOccs, grantsResp.getId());
		vnfInstance.setVimConnectionInfo(grantsResp.getVimConnections());
		// Save LCM

		vnfInstance = vnfInstancesService.save(vnfInstance);
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		final ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(lcmOpOccs, vnfPkg);
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = grantsResp.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		executionPlanner.exportGraph(plan, vnfPkg.getId(), vnfInstance, "create");

		final ExecutionResults<UnitOfWork, String> results = executor.execCreate(plan, vimConnection, vim);
		vnfLcmService.save(lcmOpOccs);
		setResultLcmInstance(lcmOpOccs, vnfInstance.getId(), results, InstantiationStateEnum.INSTANTIATED);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), lcmOpOccs.getId());
	}

	private void copyGrantResourcesToInstantiated(final VnfLcmOpOccs lcmOpOccs, final GrantResponse grantsResp) {
		// XXX need to remap the vim inside our vim.
		final VimConnectionInformation vimConnectionInformation = vimManager.findVimById(grantsResp.getVimConnections().iterator().next().getId());
		// XXX instantiation level cannot be null
		final VduInstantiationLevel instantiationLevel = null;
		grantsResp.getAddResources().forEach(x -> {
			// Get VNFM Grant Resource information ID.
			final UUID grantUuid = UUID.fromString(x.getResourceDefinitionId());
			final GrantInformation grantInformation = grantService.getGrantInformation(grantUuid).orElseThrow(() -> new NotFoundException("Could not find Grant id: " + grantUuid));
			if (x.getType() == TypeEnum.COMPUTE) {
				copyGrantToAffectedCompute(grantInformation, lcmOpOccs, vimConnectionInformation, instantiationLevel, grantsResp, x);
			} else if (x.getType() == TypeEnum.VL) {
				copyGrantToAffectedVirtualLink(grantInformation, lcmOpOccs, vimConnectionInformation, instantiationLevel, x);
			} else if (x.getType() == TypeEnum.LINKPORT) {
				copyGrantToAffectedLinkPort(grantInformation, lcmOpOccs, vimConnectionInformation, instantiationLevel, x);
			} else if (x.getType() == TypeEnum.STORAGE) {
				copyGrantToAffectedStorage(grantInformation, lcmOpOccs, vimConnectionInformation, instantiationLevel, x);
			}
		});
	}

	private void copyGrantToAffectedStorage(final GrantInformation grantInformation, final VnfLcmOpOccs lcmOpOccs, final VimConnectionInformation vimConnectionInformation, final VduInstantiationLevel instantiationLevel, final GrantInformationExt x) {
		lcmOpOccs.getResourceChanges().getAffectedVirtualStorages().stream()
				.filter(o -> o.getId().toString().equals(grantInformation.getResourceDefinitionId()))
				.findFirst()
				.ifPresent(y -> {
					y.setResourceProviderId(x.getResourceProviderId());
					y.setStatus(InstantiationStatusType.NOT_STARTED);
					y.setVimConnectionInformation(vimConnectionInformation);
					y.setVnfLcmOpOccs(lcmOpOccs);
					y.setInstantiationLevel(instantiationLevel);
					y.setZoneId(x.getZoneId());
					y.setResourceGroupId(x.getResourceGroupId());
					y.setReservationId(x.getReservationId());
					vnfInstancesService.save(y);
				});
	}

	private void copyGrantToAffectedLinkPort(final GrantInformation grantInformation, final VnfLcmOpOccs lcmOpOccs, final VimConnectionInformation vimConnectionInformation, final VduInstantiationLevel instantiationLevel, final GrantInformationExt x) {
		lcmOpOccs.getResourceChanges().getAffectedExtCp().stream()
				.filter(o -> o.getId().toString().equals(grantInformation.getResourceDefinitionId()))
				.findFirst()
				.ifPresent(y -> {
					y.setResourceProviderId(x.getResourceProviderId());
					y.setStatus(InstantiationStatusType.NOT_STARTED);
					y.setVimConnectionInformation(vimConnectionInformation);
					y.setVnfLcmOpOccs(lcmOpOccs);
					y.setInstantiationLevel(instantiationLevel);
					y.setZoneId(x.getZoneId());
					y.setResourceGroupId(x.getResourceGroupId());
					y.setReservationId(x.getReservationId());
					vnfInstancesService.save(y);
				});
	}

	private void copyGrantToAffectedVirtualLink(final GrantInformation grantInformation, final VnfLcmOpOccs lcmOpOccs, final VimConnectionInformation vimConnectionInformation, final VduInstantiationLevel instantiationLevel, final GrantInformationExt x) {
		lcmOpOccs.getResourceChanges().getAffectedVirtualLinks().stream()
				.filter(o -> o.getId().toString().equals(grantInformation.getResourceDefinitionId()))
				.findFirst()
				.ifPresent(y -> {
					y.setResourceProviderId(x.getResourceProviderId());
					y.setStatus(InstantiationStatusType.NOT_STARTED);
					y.setVimConnectionInformation(vimConnectionInformation);
					y.setVnfLcmOpOccs(lcmOpOccs);
					y.setInstantiationLevel(instantiationLevel);
					y.setZoneId(x.getZoneId());
					y.setResourceGroupId(x.getResourceGroupId());
					y.setReservationId(x.getReservationId());
					vnfInstancesService.save(y);
				});
	}

	private void copyGrantToAffectedCompute(final GrantInformation grantInformation, final VnfLcmOpOccs lcmOpOccs, final VimConnectionInformation vimConnectionInformation, final VduInstantiationLevel instantiationLevel, final GrantResponse grantsResp, final GrantInformationExt x) {
		lcmOpOccs.getResourceChanges().getAffectedVnfcs().stream()
				.filter(o -> o.getId().toString().equals(grantInformation.getResourceDefinitionId()))
				.findFirst()
				.ifPresent(y -> {
					y.setResourceProviderId(x.getResourceProviderId());
					y.setStatus(InstantiationStatusType.NOT_STARTED);
					y.setVimConnectionInformation(vimConnectionInformation);
					y.setVnfLcmOpOccs(lcmOpOccs);
					y.setInstantiationLevel(instantiationLevel);
					y.setZoneId(x.getZoneId());
					y.setResourceGroupId(x.getResourceGroupId());
					y.setReservationId(x.getReservationId());
					final String flavorId = findFlavor(grantsResp, x.getVduId());
					y.setFlavorId(flavorId);
					final String imageId = findImage(grantsResp, x.getVduId());
					y.setImageId(imageId);
					vnfInstancesService.save(y);
				});
	}

	private void setResultLcmInstance(@NotNull final VnfLcmOpOccs lcmOpOccs, @NotNull final UUID vnfInstanceId, final ExecutionResults<UnitOfWork, String> results, @Nonnull final InstantiationStateEnum eventType) {
		final VnfInstance vnfInstance = vnfInstancesService.findById(vnfInstanceId);
		if (results.getErrored().isEmpty()) {
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.COMPLETED);
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.INSTANTIATED : InstantiationStateEnum.NOT_INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
		} else {
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.NOT_INSTANTIATED : InstantiationStateEnum.INSTANTIATED);
		}
		vnfInstancesService.save(vnfInstance);
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

	public void vnfTerminate(@Nonnull final UUID lcmOpOccsId) {
		LOG.info("Eecuting Terminate on lcmOpOccs {}", lcmOpOccsId);
		// final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		// final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		// final VnfPackage vnfPkg = vnfPackageService.findById(vnfPkgId);

		VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getId());
		executionPlanner.terminatePlan(lcmOpOccs);

		lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		// XXX Do it for VnfInfoModifications
		final GrantResponse grant = getTerminateGrants(vnfInstance, lcmOpOccs, vnfPkg);
		vnfLcmService.setGrant(lcmOpOccs, grant.getId());
		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, vnfInstance.getId());
		// Make plan
		ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(lcmOpOccs, vnfPkg);
		final VimConnectionInformation vimConnection = grant.getVimConnections().iterator().next();
		// XXX Multiple Vim ?
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		plan = executionPlanner.revert(plan);
		executionPlanner.exportGraph(plan, vnfPkg.getId(), vnfInstance, "delete");

		final ExecutionResults<UnitOfWork, String> results = executor.execDelete(plan, vimConnection, vim);
		setResultLcmInstance(lcmOpOccs, vnfInstance.getId(), results, InstantiationStateEnum.NOT_INSTANTIATED);

		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), lcmOpOccs.getId());
	}

	private GrantResponse getTerminateGrants(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPkg) {
		final GrantRequest req = grantService.createTerminateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		return grantService.sendAndWaitGrantRequest(req);
	}

}
