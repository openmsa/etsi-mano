package com.ubiqube.etsi.mano.service.event;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
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
import com.ubiqube.etsi.mano.dao.mano.common.FailureDetails;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.Nfvo;
import com.ubiqube.etsi.mano.service.VnfInstanceService;
import com.ubiqube.etsi.mano.service.VnfLcmService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.GraphTools;
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

	private final VnfPackageRepository vnfPackageRepository;

	private final Nfvo nfvo;

	public VnfmActions(final VimManager _vimManager, final VnfPackageService _vnfPackageService, final EventManager _eventManager, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor, final VnfLcmService _vnfLcmService, final GrantService _grantService, final VnfInstanceService _vnfInstancesService, final VnfPackageRepository _vnfPackageRepository, final Nfvo _nfvo) {
		super();
		vimManager = _vimManager;
		vnfPackageService = _vnfPackageService;
		eventManager = _eventManager;
		executionPlanner = _executionPlanner;
		executor = _executor;
		vnfLcmService = _vnfLcmService;
		grantService = _grantService;
		vnfInstancesService = _vnfInstancesService;
		vnfPackageRepository = _vnfPackageRepository;
		nfvo = _nfvo;
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
			vnfInstance.setInstantiationState(InstantiationState.NOT_INSTANTIATED);
			lcmOpOccs.setOperationState(InstantiationStatusType.FAILED);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
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
		final GrantsRequest req = grantService.createInstantiateGrantRequest(vnfPkg, vnfInstance, localLcmOpOccs);
		final GrantResponse grantsResp = nfvo.sendSyncGrantRequest(req);

		// Send processing notification.
		lcmOpOccs.setOperationState(InstantiationStatusType.PROCESSING);
		lcmOpOccs.setStateEnteredTime(new Date());
		copyGrantResourcesToInstantiated(localLcmOpOccs, grantsResp);
		lcmOpOccs.setGrantId(grantsResp.getId().toString());
		localLcmOpOccs = vnfLcmService.save(localLcmOpOccs);
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
		GraphTools.exportGraph(removePlan, vnfPkg.getId(), localVnfInstance, "remove", vnfPackageRepository);

		final ExecutionResults<UnitOfWork, String> removeResults = executor.execDelete(removePlan, vimConnection, vim);
		/// XXX split this function for adding / removing live instances.
		setLiveSatus(localLcmOpOccs, vnfInstance, removeResults);

		// Create plan
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> createPlan = executionPlanner.planForCreation(localLcmOpOccs, vnfPkg);
		vim.refineExecutionPlan(createPlan);
		GraphTools.exportGraph(createPlan, vnfPkg.getId(), localVnfInstance, "create", vnfPackageRepository);

		final ExecutionResults<UnitOfWork, String> createResults = executor.execCreate(createPlan, vimConnection, vim, context);
		setResultLcmInstance(localLcmOpOccs, localVnfInstance, createResults, InstantiationState.INSTANTIATED);
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
			if (x.getType() == ResourceTypeEnum.COMPUTE) {
				copyStream(lcmOpOccs.getResourceChanges().getAffectedVnfcs().stream(), grantInformation, copyVnfc(x, vimConnectionInformation, lcmOpOccs, grantsResp, instantiationLevel));
			} else if (x.getType() == ResourceTypeEnum.VL) {
				copyStream(lcmOpOccs.getResourceChanges().getAffectedVirtualLinks().stream(), grantInformation, defaultCopy(x, vimConnectionInformation, lcmOpOccs, instantiationLevel));
			} else if (x.getType() == ResourceTypeEnum.LINKPORT) {
				copyStream(lcmOpOccs.getResourceChanges().getAffectedExtCp().stream(), grantInformation, defaultCopy(x, vimConnectionInformation, lcmOpOccs, instantiationLevel));
			} else if (x.getType() == ResourceTypeEnum.STORAGE) {
				copyStream(lcmOpOccs.getResourceChanges().getAffectedVirtualStorages().stream(), grantInformation, defaultCopy(x, vimConnectionInformation, lcmOpOccs, instantiationLevel));
			}
		});
	}

	private static <T extends VnfInstantiatedBase> void copyStream(final Stream<T> stream, final GrantInformation grantInformation, final Consumer<T> consumer) {
		stream.filter(o -> o.getId().toString().equals(grantInformation.getResourceDefinitionId()))
				.findFirst()
				.ifPresent(consumer);
	}

	private Consumer<VnfInstantiatedCompute> copyVnfc(final GrantInformation x, final VimConnectionInformation vimConnectionInformation, final VnfLcmOpOccs lcmOpOccs, final GrantResponse grantsResp, final VduInstantiationLevel vduInstantiationLevel) {
		return y -> {
			copyResource(y, x, vimConnectionInformation, lcmOpOccs, vduInstantiationLevel);
			final String flavorId = findFlavor(grantsResp, x.getVduId());
			y.setFlavorId(flavorId);
			final String imageId = findImage(grantsResp, x.getVduId());
			y.setImageId(imageId);
			vnfInstancesService.save(y);
		};
	}

	private <T extends VnfInstantiatedBase> Consumer<T> defaultCopy(final GrantInformation x, final VimConnectionInformation vimConnectionInformation, final VnfLcmOpOccs lcmOpOccs, final VduInstantiationLevel vduInstantiationLevel) {
		return y -> {
			copyResource(y, x, vimConnectionInformation, lcmOpOccs, vduInstantiationLevel);
			vnfInstancesService.save(y);
		};
	}

	private static void copyResource(final VnfInstantiatedBase y, final GrantInformation x, final VimConnectionInformation vimConnectionInformation, final VnfLcmOpOccs lcmOpOccs, final VduInstantiationLevel vduInstantiationLevel) {
		y.setResourceProviderId(x.getResourceProviderId());
		y.setStatus(InstantiationStatusType.NOT_STARTED);
		y.setVimConnectionInformation(vimConnectionInformation);
		y.setVnfLcmOpOccs(lcmOpOccs);
		y.setInstantiationLevel(vduInstantiationLevel);
		y.setZoneId(x.getZoneId());
		y.setResourceGroupId(x.getResourceGroupId());
		y.setReservationId(x.getReservationId());
	}

	private static void setResultLcmInstance(@NotNull final VnfLcmOpOccs lcmOpOccs, @NotNull final VnfInstance vnfInstance, final ExecutionResults<UnitOfWork, String> results, @Nonnull final InstantiationState eventType) {
		if (results.getErrored().isEmpty()) {
			lcmOpOccs.setOperationState(InstantiationStatusType.COMPLETED);
			lcmOpOccs.setStateEnteredTime(new Date());
			vnfInstance.setInstantiationState((InstantiationState.INSTANTIATED == eventType) ? InstantiationState.INSTANTIATED : InstantiationState.NOT_INSTANTIATED);
			vnfInstance.getInstantiatedVnfInfo().setVnfState(OperationalStateType.STARTED);
		} else {
			lcmOpOccs.setOperationState(InstantiationStatusType.FAILED);
			lcmOpOccs.setStateEnteredTime(new Date());
		}
		lcmOpOccs.setStateEnteredTime(new Date());
	}

	private void setLiveSatus(@NotNull final VnfLcmOpOccs lcmOpOccs, @NotNull final VnfInstance vnfInstance, final ExecutionResults<UnitOfWork, String> results) {
		LOG.info("Creating / deleting live instances.");
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
					vnfInstancesService.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final VnfLiveInstance vli = vnfInstancesService.findLiveInstanceById(rhe.getRemovedInstantiated()).orElseThrow(() -> new NotFoundException("" + rhe.getId()));
				vnfInstancesService.deleteLiveInstanceById(vli.getId());
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
			lcmOpOccs.setOperationState(InstantiationStatusType.FAILED);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
			vnfLcmService.save(lcmOpOccs);
			vnfInstancesService.save(vnfInstance);
		}
	}

	private void vnfTerminateInner(final VnfLcmOpOccs lcmOpOccs, final VnfInstance vnfInstance) {
		LOG.info("Executing Terminate on lcmOpOccs {}", lcmOpOccs.getId());
		final VnfPackage vnfPkg = vnfPackageService.findById(vnfInstance.getVnfPkg().getId());
		executionPlanner.terminatePlan(lcmOpOccs, vnfInstance);

		VnfLcmOpOccs localLcmOpOccs = vnfLcmService.save(lcmOpOccs);
		// XXX Do it for VnfInfoModifications
		final GrantResponse grant = getTerminateGrants(vnfInstance, localLcmOpOccs, vnfPkg);
		lcmOpOccs.setGrantId(grant.getId().toString());
		localLcmOpOccs = vnfLcmService.save(localLcmOpOccs);
		eventManager.sendNotification(NotificationEvent.VNF_TERMINATE, vnfInstance.getId());
		// Make plan
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> plan = executionPlanner.planForRemoval(localLcmOpOccs, vnfPkg);
		final VimConnectionInformation vimConnection = grant.getVimConnections().iterator().next();
		// XXX Multiple Vim ?
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		// vim.refineExecutionPlan(plan);
		// plan = executionPlanner.revert(plan);
		GraphTools.exportGraph(plan, vnfPkg.getId(), vnfInstance, "delete", vnfPackageRepository);

		final ExecutionResults<UnitOfWork, String> results = executor.execDelete(plan, vimConnection, vim);
		setResultLcmInstance(localLcmOpOccs, vnfInstance, results, InstantiationState.NOT_INSTANTIATED);
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
		final GrantsRequest req = grantService.createTerminateGrantRequest(vnfPkg, vnfInstance, lcmOpOccs);
		return nfvo.sendSyncGrantRequest(req);
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
			lcmOpOccs.setOperationState(InstantiationStatusType.FAILED);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
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
			lcmOpOccs.setOperationState(InstantiationStatusType.FAILED);
			lcmOpOccs.setError(new FailureDetails(500L, e.getMessage()));
			lcmOpOccs.setStateEnteredTime(new Date());
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
			if (lcmOpOccs.getOperateChanges().getTerminationType() == OperationalStateType.STARTED) {
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
