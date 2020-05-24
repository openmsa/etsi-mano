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

		VnfLcmOpOccs lcmOpOccs = vnfLcmService.createIntatiateOpOcc(vnfInstance);
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

		mapInstanceResources(lcmOpOccs, grantsResp);
		// lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		vnfLcmService.setGrant(lcmOpOccs, grantsResp.getId());
		vnfInstance.setVimConnectionInfo(grantsResp.getVimConnections());
		// Save LCM

		vnfInstance = vnfInstancesRepository.save(vnfInstance);
		lcmOpOccs = vnfLcmService.save(lcmOpOccs);
		final ListenableGraph<UnitOfWork, ConnectivityEdge> plan = executionPlanner.plan(lcmOpOccs, vnfPkg);
		// XXX Multiple Vim ?
		final VimConnectionInformation vimConnection = grantsResp.getVimConnections().iterator().next();
		final Vim vim = vimManager.getVimById(vimConnection.getId());
		vim.refineExecutionPlan(plan);
		executionPlanner.exportGraph(plan, vnfPkgId, vnfInstance, "create");

		final ExecutionResults<UnitOfWork, String> results = executor.execCreate(plan, vimConnection, vim);
		vnfLcmService.save(lcmOpOccs);
		setResultLcmInstance(lcmOpOccs, vnfInstance.getId(), results, InstantiationStateEnum.INSTANTIATED);
		// XXX Send COMPLETED event.
		LOG.info("VNF instance {} / LCM {} Finished.", vnfInstanceId, lcmOpOccs.getId());
	}

	private void mapInstanceResources(final VnfLcmOpOccs lcmOpOccs, final GrantResponse grantsResp) {
		// XXX need to remap the vim inside our vim.
		final VimConnectionInformation vimConnectionInformation = vimManager.findVimById(grantsResp.getVimConnections().iterator().next().getId());
		// XXX instantiation level cannot be null
		final VduInstantiationLevel instantiationLevel = null;
		grantsResp.getAddResources().forEach(x -> {
			// Get VNFM Grant Resource information ID.
			final UUID grantUuid = UUID.fromString(x.getResourceDefinitionId());
			final GrantInformation grantInformation = grantService.getGrantInformation(grantUuid).orElseThrow(() -> new NotFoundException("Could not find Grant id: " + grantUuid));
			if (x.getType() == TypeEnum.COMPUTE) {
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
			} else if (x.getType() == TypeEnum.VL) {
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
			} else if (x.getType() == TypeEnum.LINKPORT) {
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
			} else if (x.getType() == TypeEnum.STORAGE) {
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

	public void vnfTerminate(@Nonnull final UUID vnfInstanceId) {
		LOG.info("Eecuting Terminate on instance {}", vnfInstanceId);
		final VnfInstance vnfInstance = vnfInstancesRepository.get(vnfInstanceId);
		final UUID vnfPkgId = vnfInstance.getVnfPkg().getId();
		final VnfPackage vnfPkg = vnfPackageRepository.findById(vnfPkgId).orElseThrow(() -> new NotFoundException("Vnf " + vnfPkgId + " not Found."));

		VnfLcmOpOccs lcmOpOccs = vnfLcmService.createTerminateOpOcc(vnfInstance);
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
