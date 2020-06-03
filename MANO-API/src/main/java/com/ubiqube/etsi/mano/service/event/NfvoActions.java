package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.ZoneGroupInformation;
import com.ubiqube.etsi.mano.dao.mano.ZoneInfoEntity;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.GrantsResponseJpa;
import com.ubiqube.etsi.mano.jpa.NsLiveInstanceJpa;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.ExecutionPlanner;
import com.ubiqube.etsi.mano.service.graph.PlanExecutor;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork;
import com.ubiqube.etsi.mano.service.vim.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimImage;
import com.ubiqube.etsi.mano.service.vim.VimManager;

@Service
public class NfvoActions {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoActions.class);

	private final NsInstanceRepository nsInstanceRepository;
	private final NsLcmOpOccsService nsLcmOpOccsService;
	private final NsdRepository nsdRepository;
	private final VnfmInterface vnfm;
	private final VimManager vimManager;
	private final EventManager eventManager;
	private final VnfPackageRepository vnfPackageRepository;
	private final GrantsResponseJpa grantJpa;
	private final VnfInstancesRepository vnfInstancesRepository;
	private final NsLiveInstanceJpa nsLiveInstanceJpa;
	private final PlanExecutor executor;

	private final ExecutionPlanner executionPlanner;

	public NfvoActions(final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final VnfmInterface _vnfm, final VimManager _vimManager, final EventManager _eventManager, final VnfPackageRepository _vnfPackageRepository, final GrantsResponseJpa _grantJpa, final VnfInstancesRepository _vnfInstancesRepository, final ExecutionPlanner _executionPlanner, final PlanExecutor _executor, final NsLcmOpOccsService _nsLcmOpOccsService, final NsLiveInstanceJpa _nsLiveInstanceJpa) {
		super();
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		vnfm = _vnfm;
		vimManager = _vimManager;
		eventManager = _eventManager;
		vnfPackageRepository = _vnfPackageRepository;
		grantJpa = _grantJpa;
		vnfInstancesRepository = _vnfInstancesRepository;
		executionPlanner = _executionPlanner;
		executor = _executor;
		nsLcmOpOccsService = _nsLcmOpOccsService;
		nsLiveInstanceJpa = _nsLiveInstanceJpa;
	}

	public void nsTerminate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-NT");
		final NsLcmOpOccs lcmOpOccs = nsLcmOpOccsService.findById(lcmOpOccsId);
		final NsdInstance nsInstance = nsInstanceRepository.get(lcmOpOccs.getNsInstance().getId());
		try {
			nsTerminateInner(lcmOpOccs, nsInstance);
		} catch (final RuntimeException e) {
			LOG.error("NS Instantiate fail.", e);
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			nsInstanceRepository.save(nsInstance);
			nsLcmOpOccsService.save(lcmOpOccs);
			eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
		}
	}

	public void nsTerminateInner(final NsLcmOpOccs lcmOpOccs, final NsdInstance nsInstance) {
		// XXX This is not the correct way/
		final VimConnectionInformation vimInfo = electVim(null, null);

		final NsdPackage nsdInfo = nsdRepository.get(nsInstance.getNsdInfo().getId());
		executionPlanner.terminateNsPlan(lcmOpOccs, nsdInfo);

		final Vim vim = vimManager.getVimById(vimInfo.getId());

		ListenableGraph<NsUnitOfWork, NsConnectivityEdge> plan = executionPlanner.plan(lcmOpOccs, nsInstance);
		plan = executionPlanner.revertNs(plan);

		executionPlanner.exportNsGraph(plan, nsdInfo.getId(), nsInstance, "delete");

		final ExecutionResults<NsUnitOfWork, String> results = executor.execDeleteNs(plan, vimInfo, vim);
		setResultLcmInstance(lcmOpOccs, nsInstance.getId(), results, InstantiationStateEnum.NOT_INSTANTIATED);
		LOG.info("VNF instance {} / LCM {} Finished.", nsInstance.getId(), lcmOpOccs.getId());
	}

	public void nsInstantiate(@Nonnull final UUID lcmOpOccsId) {
		Thread.currentThread().setName(lcmOpOccsId + "-NI");
		final NsLcmOpOccs lcmOpOccs = nsLcmOpOccsService.findById(lcmOpOccsId);
		final NsdInstance nsInstance = nsInstanceRepository.get(lcmOpOccs.getNsInstance().getId());

		try {
			nsInstantiateInner(lcmOpOccs, nsInstance);
		} catch (final RuntimeException e) {
			LOG.error("NS Instantiate fail.", e);
			// We can't save here, we must do an atomic update.
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			nsInstance.setNsState(InstantiationStateEnum.NOT_INSTANTIATED);
			nsInstanceRepository.save(nsInstance);
			nsLcmOpOccsService.save(lcmOpOccs);
			eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
		}
	}

	public void nsInstantiateInner(@Nonnull final NsLcmOpOccs lcmOpOccs, final NsdInstance nsInstance) {
		final UUID nsdId = nsInstance.getNsdInfo().getId();
		final NsdPackage nsdInfo = nsdRepository.get(nsdId);
		// Make plan in lcmOpOccs
		executionPlanner.makePrePlan(nsInstance, nsdInfo, lcmOpOccs);
		final NsLcmOpOccs localLcmOpOccs = nsLcmOpOccsService.save(lcmOpOccs);
		final VimConnectionInformation vimInfo = electVim(null, null);
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		// Create Ns.
		final Map<String, String> userData = nsdInfo.getUserDefinedData();
		// XXX elect vim?
		final Map<String, String> pubNet = vim.getPublicNetworks(vimInfo);
		final ListenableGraph<NsUnitOfWork, NsConnectivityEdge> plan = executionPlanner.plan(localLcmOpOccs, nsInstance);
		executionPlanner.exportNsGraph(plan, nsdId, nsInstance, "create");
		final ExecutionResults<NsUnitOfWork, String> results = executor.execCreateNs(plan, vimInfo, vim, pubNet);
		LOG.debug("Done, Saving ...");
		setResultLcmInstance(localLcmOpOccs, nsInstance.getId(), results, InstantiationStateEnum.INSTANTIATED);
		// XXX Send COMPLETED event.
		LOG.info("NSD instance {} / LCM {} Finished.", nsdId, localLcmOpOccs.getId());
		eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstance.getId());
	}

	private void setResultLcmInstance(final NsLcmOpOccs lcmOpOccs, @Nonnull final UUID nsInstanceId, final ExecutionResults<NsUnitOfWork, String> results, final InstantiationStateEnum eventType) {
		final NsdInstance nsdInstance = nsInstanceRepository.get(nsInstanceId);
		if (results.getErrored().isEmpty()) {
			lcmOpOccs.setOperationState(LcmOperationStateType.COMPLETED);
			nsdInstance.setNsState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.INSTANTIATED : InstantiationStateEnum.NOT_INSTANTIATED);
			LOG.info("NS result COMPLETED");
		} else {
			lcmOpOccs.setOperationState(LcmOperationStateType.FAILED);
			nsdInstance.setNsState((InstantiationStateEnum.INSTANTIATED == eventType) ? InstantiationStateEnum.NOT_INSTANTIATED : InstantiationStateEnum.INSTANTIATED);
			LOG.info("NS result FAILED");
		}
		results.getSuccess().forEach(x -> {
			final NsInstantiatedBase rhe = x.getId().getResourceHandleEntity();
			final ChangeType ct = rhe.getChangeType();
			if (ct == ChangeType.ADDED) {
				final String il = rhe.getInstantiationLevel();
				if (null != rhe.getId()) {
					final NsLiveInstance vli = new NsLiveInstance(rhe.getResourceId(), il, rhe, lcmOpOccs, lcmOpOccs.getNsInstance());
					nsLiveInstanceJpa.save(vli);
				} else {
					LOG.warn("Could not store: {}", x.getId().getName());
				}
			} else if (ct == ChangeType.REMOVED) {
				LOG.info("Removing {}", rhe.getId());
				final NsLiveInstance vli = nsLiveInstanceJpa.findByNsInstantiatedBaseResourceId(rhe.getResourceId());
				nsLiveInstanceJpa.deleteById(vli.getId());
			}
		});
		LOG.info("Saving NS Instance.");
		nsInstanceRepository.save(nsdInstance);
		LOG.info("Saving NS LCM.");
		nsLcmOpOccsService.save(lcmOpOccs);
	}

	public void grantRequest(final UUID objectId) {
		LOG.info("Evaluating grant {}", objectId);
		final Optional<GrantResponse> grantsOpt = grantJpa.findById(objectId);
		final GrantResponse grants = grantsOpt.orElseThrow(() -> new NotFoundException("Grant ID " + objectId + " Not found."));

		// Free Lease if any.
		grants.getRemoveResources().forEach(x -> {
			if (x.getReservationId() != null) {
				final VimConnectionInformation vci = vimManager.findVimById(UUID.fromString(x.getVimConnectionId()));
				final Vim vim = vimManager.getVimById(UUID.fromString(x.getVimConnectionId()));
				vim.freeResources(vci, x);
			}
		});
		final VnfPackage vnfPackage = getPackageFromVnfInstanceId(UUID.fromString(grants.getVnfInstanceId()));
		final VimConnectionInformation vimInfo = electVim(vnfPackage.getUserDefinedData().get("vimId"), grants.getAddResources());
		// Zones.
		final String zoneId = chooseZone(vimInfo);
		final Set<ZoneInfoEntity> zones = Collections.singleton(mapZone(zoneId, vimInfo));
		grants.setZones(zones);
		// Zone Group
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		final List<ServerGroup> sg = vim.getServerGroup(vimInfo);
		final List<String> sgList = sg.stream().map(x -> x.getId()).collect(Collectors.toList());
		final ZoneGroupInformation zgi = new ZoneGroupInformation();
		zgi.setZoneId(sgList);
		grants.setZoneGroups(Collections.singleton(zgi));
		// XXX It depends on Grant policy GRANT_RESERVE_SINGLE.
		grants.getAddResources().forEach(x -> {
			vim.allocateResources(vimInfo, x);
			x.setResourceProviderId(vim.getType());
			x.setVimConnectionId(vimInfo.getId().toString());
			x.setZoneId(zoneId);
			x.setResourceGroupId(zgi.getZoneId().get(0));
		});

		grants.setVimConnections(Collections.singleton(vimInfo));

		final GrantVimAssetsEntity grantVimAssetsEntity = new GrantVimAssetsEntity();
		// XXX Push only needed ones. ( in case of terminate no need to push assets.)
		grantVimAssetsEntity.setSoftwareImages(getSoftwareImage(vnfPackage, vimInfo, vim));
		grantVimAssetsEntity.getComputeResourceFlavours().addAll(getFlavors(vnfPackage, vimInfo, vim));
		grants.setVimAssets(grantVimAssetsEntity);
		grants.setAvailable(Boolean.TRUE);
		// Add public networks.
		vim.getPublicNetworks(vimInfo).entrySet().forEach(x -> {
			final ExtManagedVirtualLinkDataEntity extVl = new ExtManagedVirtualLinkDataEntity();
			extVl.setResourceId(x.getValue());
			extVl.setResourceProviderId(vim.getType());
			extVl.setVimConnectionId(vimInfo.getId().toString());
			extVl.setVnfVirtualLinkDescId(x.getKey());
			extVl.setGrants(grants);
			grants.addExtManagedVl(extVl);
		});
		grantJpa.save(grants);
		LOG.info("Grant {} Available.", grants.getId());
	}

	private static ZoneInfoEntity mapZone(final String zoneId, final VimConnectionInformation vimInfo) {
		final ZoneInfoEntity zoneInfoEntity = new ZoneInfoEntity();
		zoneInfoEntity.setVimConnectionId(vimInfo.getId().toString());
		zoneInfoEntity.setResourceProviderId(vimInfo.getVimType());
		zoneInfoEntity.setZoneId(zoneId);
		return zoneInfoEntity;
	}

	private String chooseZone(final VimConnectionInformation vimInfo) {
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		final List<String> list = vim.getZoneAvailableList(vimInfo);
		return list.get(0);
	}

	private static List<VimComputeResourceFlavourEntity> getFlavors(final VnfPackage vnfPackage, final VimConnectionInformation vimConnectionInformation, final Vim vim) {
		final List<VimComputeResourceFlavourEntity> listVcrfe = new ArrayList<>();
		vnfPackage.getVnfCompute().forEach(x -> {
			final String flavorId = vim.getOrCreateFlavor(vimConnectionInformation, x.getName(), (int) x.getNumVcpu(), x.getVirtualMemorySize(), 10);
			final VimComputeResourceFlavourEntity vcrfe = new VimComputeResourceFlavourEntity();
			vcrfe.setVimConnectionId(vimConnectionInformation.getId().toString());
			vcrfe.setResourceProviderId(vim.getType());
			vcrfe.setVimFlavourId(flavorId);
			vcrfe.setVnfdVirtualComputeDescId(x.getId().toString());
			listVcrfe.add(vcrfe);
		});
		return listVcrfe;
	}

	private VnfPackage getPackageFromVnfInstanceId(@NotNull final UUID vnfInstanceId) {
		final VnfInstance instance = vnfInstancesRepository.get(vnfInstanceId);
		return vnfPackageRepository.get(instance.getVnfPkg().getId());
	}

	private static Set<VimSoftwareImageEntity> getSoftwareImage(final VnfPackage vnfPackage, final VimConnectionInformation vimInfo, final Vim vim) {
		final Set<VimSoftwareImageEntity> listVsie = new HashSet<>();
		final Set<VnfCompute> vnfc = vnfPackage.getVnfCompute();
		vnfc.forEach(x -> {
			SoftwareImage img = x.getSoftwareImage();
			if (null != img) {
				// Get Vim or create vim resource via Or-Vi
				final Optional<SoftwareImage> newImg = vim.getSwImageMatching(vimInfo, img);
				img = newImg.orElseGet(() -> {
					// Use or-vi, Vim is not on the same server. and where is the path ?
					return vim.uploadSoftwareImage(vimInfo, x.getSoftwareImage());
				});
				listVsie.add(mapSoftwareImage(img, x.getId(), vimInfo, vim));
			}
		});
		final Set<VnfStorage> storage = vnfPackage.getVnfStorage();
		storage.forEach(x -> {
			final SoftwareImage img = x.getSoftwareImage();
			if (null != img) {
				listVsie.add(mapSoftwareImage(img, x.getId(), vimInfo, vim));
			}
		});
		return listVsie;
	}

	private static VimSoftwareImageEntity mapSoftwareImage(final SoftwareImage softwareImage, final UUID vduId, final VimConnectionInformation vimInfo, final Vim vim) {
		final VimSoftwareImageEntity vsie = new VimSoftwareImageEntity();
		vsie.setVimSoftwareImageId(softwareImage.getVimId());
		vsie.setVnfdSoftwareImageId(vduId.toString());
		vsie.setVimConnectionId(vimInfo.getId().toString());
		vsie.setResourceProviderId(vim.getType());
		if (null != softwareImage.getVimId()) {
			// XXX
		} else {
			final VimImage vimImage = vim.getImagesInformations(vimInfo, softwareImage.getName());
			vsie.setVimSoftwareImageId(vimImage.getId());
		}
		return vsie;
	}

	private VimConnectionInformation electVim(final String vimId, final Set<GrantInformationExt> set) {
		// XXX: Do some real elections.
		final Set<VimConnectionInformation> vims;
		if (null != vimId) {
			LOG.debug("Getting MSA 2.x VIM");
			vims = vimManager.getVimByType("MSA_20");
		} else {
			LOG.debug("Getting OS v3 VIM");
			vims = vimManager.getVimByType("OPENSTACK_V3");
		}
		if (vims.isEmpty()) {
			throw new GenericException("Couldn't find a VIM.");
		}
		return vims.iterator().next();
	}

}
