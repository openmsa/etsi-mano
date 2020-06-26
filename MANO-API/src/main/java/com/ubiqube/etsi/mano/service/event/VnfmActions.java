package com.ubiqube.etsi.mano.service.event;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstanceScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.VnfOperationalStateType;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
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

	private final VnfPackageRepository vnfPackageRepository;

	public VnfmActions(final VimManager _vimManager, final VnfPackageService _vnfPackageService, final EventManager _eventManager, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor, final VnfLcmService _vnfLcmService, final GrantService _grantService, final VnfInstanceService _vnfInstancesService, final VnfLiveInstanceJpa _vnfLiveInstanceJpa, final VnfPackageRepository _vnfPackageRepository) {
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
		vnfPackageRepository = _vnfPackageRepository;
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
		if (null == lcmOpOccs.getVnfInstantiatedInfo().getInstantiationLevelId()) {
			lcmOpOccs.getVnfInstantiatedInfo().setInstantiationLevelId(vnfInstance.getInstantiatedVnfInfo().getInstantiationLevelId());
		}
		// Parameters are in the lcmOpOccs.
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg());
		final Set<ScaleInfo> newScale = merge(lcmOpOccs, vnfInstance);
		executionPlanner.makePrePlan(lcmOpOccs.getVnfInstantiatedInfo().getInstantiationLevelId(), vnfPkg, vnfInstance, lcmOpOccs, newScale);
		VnfLcmOpOccs localLcmOpOccs = vnfLcmService.save(lcmOpOccs);

		// XXX Do it for VnfInfoModifications
		eventManager.sendNotification(NotificationEvent.VNF_INSTANTIATE, vnfInstance.getId());

		// Send Grant.
		final GrantRequest req = grantService.createInstantiateGrantRequest(vnfPkg, vnfInstance, localLcmOpOccs);
		final GrantResponse grantsResp = grantService.sendAndWaitGrantRequest(req);
		// Send processing notification.
		vnfLcmService.updateState(localLcmOpOccs, LcmOperationStateType.PROCESSING);

		copyGrantResourcesToInstantiated(localLcmOpOccs, grantsResp);
		vnfLcmService.setGrant(localLcmOpOccs, grantsResp.getId());
		// vnfInstance.setVimConnectionInfo(grantsResp.getVimConnections());
		// extract Ext VL
		final Map<String, String> context = grantsResp.getExtManagedVirtualLinks().stream()
				.collect(Collectors.toMap(ExtManagedVirtualLinkDataEntity::getVnfVirtualLinkDescId, ExtManagedVirtualLinkDataEntity::getResourceId));
		// Add all present VL if any.
		context.putAll(getLiveVl(vnfInstance));
		final VnfInstance localVnfInstance = vnfInstancesService.save(vnfInstance);
		localLcmOpOccs = vnfLcmService.save(localLcmOpOccs);
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> removePlan = executionPlanner.planForRemoval(localLcmOpOccs, vnfPkg);
		// XXX We can't refine a removal plan, because it has already been reverted.
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = grantsResp.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		localVnfInstance.setVimConnectionInfo(Collections.singleton(vimConnection));
		//
		executionPlanner.exportGraph(removePlan, vnfPkg.getId(), localVnfInstance, "remove", vnfPackageRepository);

		final ExecutionResults<UnitOfWork, String> removeResults = executor.execDelete(removePlan, vimConnection, vim);
		/// XXX split this function for adding / removing live instances.
		setLiveSatus(localLcmOpOccs, vnfInstance, removeResults);

		// Create plan
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> createPlan = executionPlanner.planForCreation(localLcmOpOccs, vnfPkg);
		vim.refineExecutionPlan(createPlan);
		executionPlanner.exportGraph(createPlan, vnfPkg.getId(), localVnfInstance, "create", vnfPackageRepository);

		final ExecutionResults<UnitOfWork, String> createResults = executor.execCreate(createPlan, vimConnection, vim, context);
		setResultLcmInstance(localLcmOpOccs, localVnfInstance, createResults, InstantiationStateEnum.INSTANTIATED);
		if (localLcmOpOccs.getVnfInstantiatedInfo().getScaleStatus() != null) {
			final Set<VnfInstanceScaleInfo> scaleInfos = localLcmOpOccs.getVnfInstantiatedInfo().getScaleStatus().stream()
					.map(x -> new VnfInstanceScaleInfo(x.getAspectId(), x.getScaleLevel()))
					.collect(Collectors.toSet());
			localVnfInstance.getInstantiatedVnfInfo().setScaleStatus(scaleInfos);
		}
		setLiveSatus(localLcmOpOccs, localVnfInstance, createResults);
		LOG.info("Saving VNF Instance.");
		localVnfInstance.getInstantiatedVnfInfo().setInstantiationLevelId(lcmOpOccs.getVnfInstantiatedInfo().getInstantiationLevelId());
		if (null != lcmOpOccs.getVnfInstantiatedInfo().getFlavourId()) {
			localVnfInstance.getInstantiatedVnfInfo().setFlavourId(lcmOpOccs.getVnfInstantiatedInfo().getFlavourId());
		}
		// XXX Copy new ScaleInfo.
		removeScaleScatus(localVnfInstance, newScale);
		// XXX ??? error duplicate key in NSD.
		localVnfInstance.setVimConnectionInfo(null);
		vnfInstancesService.save(localVnfInstance);
		LOG.info("Saving VNF LCM OP OCCS.");
		localLcmOpOccs = vnfLcmService.save(localLcmOpOccs);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", localVnfInstance.getId(), localLcmOpOccs.getId());
	}

	private static void removeScaleScatus(final VnfInstance localVnfInstance, final Set<ScaleInfo> newScale) {
		final Set<VnfInstanceScaleInfo> scales = localVnfInstance.getInstantiatedVnfInfo().getScaleStatus();
		newScale.stream()
				.forEach(x -> find(scales, x.getAspectId()).ifPresent(scales::remove));
		final Set<VnfInstanceScaleInfo> newScalings = newScale.stream()
				.map(x -> new VnfInstanceScaleInfo(x.getAspectId(), x.getScaleLevel()))
				.collect(Collectors.toSet());
		scales.addAll(newScalings);
	}

	private static Optional<VnfInstanceScaleInfo> find(final Set<VnfInstanceScaleInfo> scales, final String aspectId) {
		return scales.stream()
				.filter(x -> x.getAspectId().equals(aspectId))
				.findFirst();
	}

	private static Set<ScaleInfo> merge(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		final Set<ScaleInfo> tmp = vnfInstance.getInstantiatedVnfInfo().getScaleStatus().stream()
				.filter(x -> notIn(x.getAspectId(), lcmOpOccs.getVnfInstantiatedInfo().getScaleStatus()))
				.map(x -> new ScaleInfo(x.getAspectId(), x.getScaleLevel()))
				.collect(Collectors.toSet());
		tmp.addAll(lcmOpOccs.getVnfInstantiatedInfo().getScaleStatus());
		return tmp;
	}

	private static boolean notIn(final String aspectId, final Set<ScaleInfo> scaleInfos) {
		return scaleInfos.stream()
				.noneMatch(x -> x.getAspectId().equals(aspectId));
	}

	private Map<String, String> getLiveVl(final VnfInstance vnfInstance) {
		final List<VnfInstantiatedVirtualLink> res = vnfInstancesService.getLiveVirtualLinkInstanceOf(vnfInstance);
		return res.stream().collect(Collectors.toMap(y -> y.getVnfVirtualLink().getToscaName(), VnfInstantiatedBase::getResourceId));
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

	private void setResultLcmInstance(@NotNull final VnfLcmOpOccs lcmOpOccs, @NotNull final VnfInstance vnfInstance, final ExecutionResults<UnitOfWork, String> results, @Nonnull final InstantiationStateEnum eventType) {
		if (results.getErrored().isEmpty()) {
			lcmOpOccs.setOperationState(LcmOperationStateType.COMPLETED);
			lcmOpOccs.setStateEnteredTime(new Date());
			vnfInstance.setInstantiationState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.INSTANTIATED : InstantiationStateEnum.NOT_INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
		} else {
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			lcmOpOccs.setStateEnteredTime(new Date());
		}
	}

	private void setLiveSatus(@NotNull final VnfLcmOpOccs lcmOpOccs, @NotNull final VnfInstance vnfInstance, final ExecutionResults<UnitOfWork, String> results) {
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
					final VnfLiveInstance vli = new VnfLiveInstance(vnfInstance, il, rhe, lcmOpOccs, rhe.getResourceId(), rhe.getVduId());
					vnfLiveInstanceJpa.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final VnfLiveInstance vli = vnfLiveInstanceJpa.findById(rhe.getRemovedInstantiated()).orElseThrow(() -> new NotFoundException("" + rhe.getId()));
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
			vnfLcmService.save(lcmOpOccs);
			vnfInstancesService.save(vnfInstance);
		}
	}

	private void vnfTerminateInner(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		LOG.info("Executing Terminate on lcmOpOccs {}", lcmOpOccs.getId());
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg().getId());
		executionPlanner.terminatePlan(lcmOpOccs);

		VnfLcmOpOccs localLcmOpOccs = vnfLcmService.save(lcmOpOccs);
		// XXX Do it for VnfInfoModifications
		final GrantResponse grant = getTerminateGrants(vnfInstance, localLcmOpOccs, vnfPkg);
		vnfLcmService.setGrant(localLcmOpOccs, grant.getId());
		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, vnfInstance.getId());
		// Make plan
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> plan = executionPlanner.planForRemoval(localLcmOpOccs, vnfPkg);
		final VimConnectionInformation vimConnection = grant.getVimConnections().iterator().next();
		// XXX Multiple Vim ?
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		// vim.refineExecutionPlan(plan);
		// plan = executionPlanner.revert(plan);
		executionPlanner.exportGraph(plan, vnfPkg.getId(), vnfInstance, "delete", vnfPackageRepository);

		final ExecutionResults<UnitOfWork, String> results = executor.execDelete(plan, vimConnection, vim);
		setResultLcmInstance(localLcmOpOccs, vnfInstance, results, InstantiationStateEnum.NOT_INSTANTIATED);
		localLcmOpOccs = vnfLcmService.save(localLcmOpOccs);
		setLiveSatus(localLcmOpOccs, vnfInstance, results);
		LOG.info("Saving VNF Instance.");
		vnfInstance.getInstantiatedVnfInfo().getScaleStatus().clear();
		vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STOPPED);

		vnfInstancesService.save(vnfInstance);

		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, localLcmOpOccs.getId());
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstance.getId(), localLcmOpOccs.getId());
	}

	private GrantResponse getTerminateGrants(final VnfInstance vnfInstance, final VnfLcmOpOccs lcmOpOccs, final VnfPackage vnfPkg) {
		final GrantRequest req = grantService.createTerminateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		return grantService.sendAndWaitGrantRequest(req);
	}

	public void scaleToLevel(@NotNull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-SL");
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		try {
			vnfInstantiateInner(lcmOpOccs, vnfInstance);
			LOG.info("Scale to level {} Success...", lcmOpOccsId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Scale to level Failed", e);
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
			vnfLcmService.save(lcmOpOccs);
		}
		eventManager.sendNotification(NotificationEvent.VNF_SCALE, lcmOpOccsId);
	}

	public void scale(@NotNull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-SS");
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		try {
			vnfInstantiateInner(lcmOpOccs, vnfInstance);
			LOG.info("Scale to level {} Success...", lcmOpOccsId);
		} catch (final RuntimeException e) {
			LOG.error("VNF Scale to level Failed", e);
			vnfLcmService.updateState(lcmOpOccs, LcmOperationStateType.FAILED);
			vnfLcmService.save(lcmOpOccs);
		}
		eventManager.sendNotification(NotificationEvent.VNF_SCALE, lcmOpOccsId);
	}

	public void vnfOperate(@NotNull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-SS");
		final VnfLcmOpOccs lcmOpOccs = vnfLcmService.findById(lcmOpOccsId);
		final VnfInstance vnfInstance = vnfInstancesService.findById(lcmOpOccs.getVnfInstance().getId());
		// XXX duplicate of planner
		final List<VnfInstantiatedCompute> instantiatedCompute = vnfInstancesService.getLiveComputeInstanceOf(vnfInstance);
		instantiatedCompute.forEach(x -> {
			final VnfInstantiatedCompute affectedCompute = copyInstantiedResource(x, new VnfInstantiatedCompute(), lcmOpOccs);
			affectedCompute.setVnfCompute(x.getVnfCompute());
			final VnfLiveInstance vnfLiveInstance = vnfInstancesService.findLiveInstanceByInstantiated(x.getId());
			affectedCompute.setRemovedInstantiated(vnfLiveInstance.getId());
			lcmOpOccs.getResourceChanges().addAffectedVnfcs(affectedCompute);
		});
		final VimConnectionInformation vimConnection = vnfInstance.getVimConnectionInfo().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		instantiatedCompute.forEach(x -> {
			if (lcmOpOccs.getOperateChanges().getTerminationType() == VnfOperationalStateType.STARTED) {
				vim.startServer(vimConnection, x.getResourceId());
				vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
			} else {
				vim.stopServer(vimConnection, x.getResourceId());
				vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STOPPED);
			}
		});
		vnfInstancesService.save(vnfInstance);
	}

	private static <T extends VnfInstantiatedBase> T copyInstantiedResource(final VnfInstantiatedBase source, final T inst, final VnfLcmOpOccs lcmOpOccs) {
		inst.setChangeType(ChangeType.REMOVED);
		inst.setStatus(InstantiationStatusType.STARTED);
		inst.setVduId(source.getVduId());
		inst.setRemovedInstantiated(source.getId());
		inst.setResourceId(source.getResourceId());
		inst.setInstantiationLevel(source.getInstantiationLevel());
		inst.setVnfLcmOpOccs(lcmOpOccs);
		return inst;
	}

}
