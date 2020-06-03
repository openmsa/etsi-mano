package com.ubiqube.etsi.mano.service.event;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.dexecutor.core.task.ExecutionResults;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
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
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.vnfm.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
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

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	public VnfmActions(final VimManager _vimManager, final VnfPackageService _vnfPackageService, final EventManager _eventManager, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor, final VnfLcmService _vnfLcmService, final GrantService _grantService, final VnfInstanceService _vnfInstancesService, final VnfLiveInstanceJpa _vnfLiveInstanceJpa) {
		super();
		vimManager = _vimManager;
		vnfPackageService = _vnfPackageService;
		eventManager = _eventManager;
		executionPlanner = _executionPlanner;
		executor = _executor;
		vnfLcmService = _vnfLcmService;
		grantService = _grantService;
		vnfInstancesService = _vnfInstancesService;
		vnfLiveInstanceJpa = _vnfLiveInstanceJpa;
	}

	public void vnfInstantiate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-VI");
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		try {
			vnfInstantiateInner(lcmOpOccs, vnfInstance);
			LOG.info("Instantiate {} Success...", lcmOpOccsId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			vnfLcmService.save(lcmOpOccs);
			vnfInstancesService.save(vnfInstance);
		}
	}

	private void vnfInstantiateInner(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		// Parameters are in the lcmOpOccs.
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg());
		executionPlanner.makePrePlan(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId(), vnfPkg, vnfInstance, lcmOpOccs);
		VnfLcmOpOccs localLcmOpOccs = vnfLcmService.save(lcmOpOccs);

		// XXX Do it for VnfInfoModifications
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());

		// Send Grant.
		final GrantRequest req = grantService.createInstantiateGrantRequest(vnfPkg, vnfInstance, localLcmOpOccs);
		final GrantResponse grantsResp = grantService.sendAndWaitGrantRequest(req);
		// Send processing notification.
		vnfLcmService.updateState(localLcmOpOccs, LcmOperationStateType.PROCESSING);
		// XXX Send processing event.

		copyGrantResourcesToInstantiated(localLcmOpOccs, grantsResp);
		vnfLcmService.setGrant(localLcmOpOccs, grantsResp.getId());
		vnfInstance.setVimConnectionInfo(grantsResp.getVimConnections());
		// extract Ext VL
		final Map<String, String> extVl = grantsResp.getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		final VnfInstance localVnfInstance = vnfInstancesService.save(vnfInstance);
		localLcmOpOccs = vnfLcmService.save(localLcmOpOccs);
		final ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(localLcmOpOccs, vnfPkg);
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = grantsResp.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		executionPlanner.exportGraph(plan, vnfPkg.getId(), localVnfInstance, "create");

		final ExecutionResults<UnitOfWork, String> results = executor.execCreate(plan, vimConnection, vim, extVl);
		setResultLcmInstance(localLcmOpOccs, localVnfInstance.getId(), results, InstantiationStateEnum.INSTANTIATED);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", localVnfInstance.getId(), localLcmOpOccs.getId());
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
					y.setResourceProviderId(x.getResourceProviderId());
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
			lcmOpOccs.setOperationState(LcmOperationStateType.COMPLETED);
			lcmOpOccs.setStateEnteredTime(new Date());
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.INSTANTIATED : InstantiationStateEnum.NOT_INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
		} else {
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			lcmOpOccs.setStateEnteredTime(new Date());
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.NOT_INSTANTIATED : InstantiationStateEnum.INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STOPPED);
		}
		LOG.info("Saving VNF Instance.");
		final VnfInstance localVnfInstance = vnfInstancesService.save(vnfInstance);
		LOG.info("Saving VNF LCM OP OCCS.");
		final VnfLcmOpOccs localLcm = vnfLcmService.save(lcmOpOccs);
		LOG.info("Creating / deleting live instances.");
		//
		results.getSuccess().forEach(x -> {
			final VnfInstantiatedBase rhe = x.getId().getResourceHandleEntity();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				String il = null;
				if (rhe.getInstantiationLevel() != null) {
					il = rhe.getInstantiationLevel().getLevelName();
				}
				if (null != rhe.getId()) {
					final VnfLiveInstance vli = new VnfLiveInstance(localVnfInstance, il, rhe, localLcm, rhe.getVduId());
					vnfLiveInstanceJpa.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final VnfLiveInstance vli = vnfLiveInstanceJpa.findByVnfInstantiatedBaseId(rhe.getRemovedInstantiated());
				vnfLiveInstanceJpa.deleteById(vli.getId());
			}
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

	public void vnfTerminate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-VT");
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		try {
			vnfTerminateInner(lcmOpOccs, vnfInstance);
			LOG.info("Terminate {} Success...", lcmOpOccsId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Instantiate Failed", e);
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			vnfInstance.setInstantiationState(InstantiationStateEnum.NOT_INSTANTIATED);
			vnfLcmService.save(lcmOpOccs);
			vnfInstancesService.save(vnfInstance);
		}
	}

	private void vnfTerminateInner(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		LOG.info("Executing Terminate on lcmOpOccs {}", lcmOpOccs.getId());
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg().getId());
		executionPlanner.terminatePlan(lcmOpOccs);

		final VnfLcmOpOccs localLcmOpOccs = vnfLcmService.save(lcmOpOccs);
		// XXX Do it for VnfInfoModifications
		final GrantResponse grant = getTerminateGrants(vnfInstance, localLcmOpOccs, vnfPkg);
		vnfLcmService.setGrant(localLcmOpOccs, grant.getId());
		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, vnfInstance.getId());
		// Make plan
		ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(localLcmOpOccs, vnfPkg);
		final VimConnectionInformation vimConnection = grant.getVimConnections().iterator().next();
		// XXX Multiple Vim ?
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		plan = executionPlanner.revert(plan);
		executionPlanner.exportGraph(plan, vnfPkg.getId(), vnfInstance, "delete");

		final ExecutionResults<UnitOfWork, String> results = executor.execDelete(plan, vimConnection, vim);
		setResultLcmInstance(localLcmOpOccs, vnfInstance.getId(), results, InstantiationStateEnum.NOT_INSTANTIATED);
		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, localLcmOpOccs.getId());
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), localLcmOpOccs.getId());
	}

	private GrantResponse getTerminateGrants(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPkg) {
		final GrantRequest req = grantService.createTerminateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		return grantService.sendAndWaitGrantRequest(req);
	}

}
