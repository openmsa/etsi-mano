package com.ubiqube.etsi.mano.service.event;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.Grants;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VimSoftwareImageEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.VnfInstanceFactory;
import com.ubiqube.etsi.mano.jpa.GrantsJpa;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdUsageStateType;
import com.ubiqube.etsi.mano.model.nslcm.InstantiationStateEnum;
import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;
import com.ubiqube.etsi.mano.model.nslcm.NsLcmOpType;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOcc;
import com.ubiqube.etsi.mano.repository.NsInstanceRepository;
import com.ubiqube.etsi.mano.repository.NsLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.NsdRepository;
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfLcmOpOccsRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimImage;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.service.vim.VimStatus;

@Service
public class NfvoActions {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoActions.class);

	private final NsLcmOpOccsRepository lcmOpOccsRepository;
	private final VnfLcmOpOccsRepository vnfLcmOpOccsRepository;
	private final NsInstanceRepository nsInstanceRepository;
	private final NsdRepository nsdRepository;
	private final VnfmInterface vnfm;
	private final VimManager vimManager;
	private final EventManager eventManager;
	private final VnfPackageRepository vnfPackageRepository;
	private final NsLcmOpOccsRepository nsLcmOpOccsRepository;
	private final GrantsJpa grantJpa;
	private final VnfInstancesRepository vnfInstancesRepository;

	public NfvoActions(final NsLcmOpOccsRepository _lcmOpOccsRepository, final VnfLcmOpOccsRepository _vnfLcmOpOccsRepository, final NsInstanceRepository _nsInstanceRepository, final NsdRepository _nsdRepository, final VnfmInterface _vnfm, final VimManager _vimManager, final EventManager _eventManager, final VnfPackageRepository _vnfPackageRepository, final NsLcmOpOccsRepository _nsLcmOpOccsRepository, final GrantsJpa _grantJpa, final VnfInstancesRepository _vnfInstancesRepository) {
		super();
		lcmOpOccsRepository = _lcmOpOccsRepository;
		vnfLcmOpOccsRepository = _vnfLcmOpOccsRepository;
		nsInstanceRepository = _nsInstanceRepository;
		nsdRepository = _nsdRepository;
		vnfm = _vnfm;
		vimManager = _vimManager;
		eventManager = _eventManager;
		vnfPackageRepository = _vnfPackageRepository;
		nsLcmOpOccsRepository = _nsLcmOpOccsRepository;
		grantJpa = _grantJpa;
		vnfInstancesRepository = _vnfInstancesRepository;
	}

	public void nsTerminate(final String nsInstanceId) {
		// XXX This is not the correct way/
		final VimConnectionInformation vimInfo = electVim(null, null);

		final NsLcmOpOcc lcmOpOccs = nsLcmOpOccsRepository.createLcmOpOccs(nsInstanceId, NsLcmOpType.TERMINATE);
		final NsdInstance nsInstance = nsInstanceRepository.get(nsInstanceId);

		final String nsdId = nsInstance.getNsdId();
		final NsdInfo nsdInfo = nsdRepository.get(nsdId);
		// Delete VNF
		final List<String> vnfs = nsdInfo.getVnfPkgIds();
		// Correct if talking with a Mano VNFM ( can we pass nsInstanceId ?)
		List<VnfLcmOpOccs> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfs) {
			final VnfLcmOpOccs vnfLcmOpOccs = vnfm.vnfTerminate(nsInstanceId, vnfId);
			vnfLcmOpOccsIds.add(vnfLcmOpOccs);
		}
		waitForCompletion(vnfLcmOpOccsIds);
		vnfLcmOpOccsIds = refreshVnfLcmOpOccsIds(vnfLcmOpOccsIds);
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		final LcmOperationStateType status = computeStatus(vnfLcmOpOccsIds);
		if (LcmOperationStateType.COMPLETED != status) {
			updateOperationState(lcmOpOccs, status);
			eventManager.sendNotification(NotificationEvent.NS_TERMINATE, nsInstanceId);
			return;
		}
		// Release the NS.
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		final String processId = vim.onNsInstanceTerminate(nsInstance.getProcessId(), nsdInfo.getUserDefinedData());
		vim.waitForCompletion(processId, 5 * 60);

		nsInstanceRepository.changeNsdUpdateState(nsInstance, InstantiationStateEnum.NOT_INSTANTIATED);
	}

	private static LcmOperationStateType computeStatus(final List<VnfLcmOpOccs> vnfLcmOpOccsIds) {
		for (final VnfLcmOpOccs vnfLcmOpOcc : vnfLcmOpOccsIds) {
			if (LcmOperationStateType.COMPLETED != vnfLcmOpOcc.getOperationState()) {
				return vnfLcmOpOcc.getOperationState();
			}
		}
		return LcmOperationStateType.COMPLETED;
	}

	private void updateOperationState(final NsLcmOpOcc lcmOpOccs, final LcmOperationStateType status) {
		lcmOpOccs.setStateEnteredTime(OffsetDateTime.now());
		lcmOpOccs.setOperationState(status);
		lcmOpOccsRepository.save(lcmOpOccs);
	}

	public void nsInstantiate(final String nsInstanceId) {
		final NsdInstance nsInstance = nsInstanceRepository.get(nsInstanceId);
		final String nsdId = nsInstance.getNsdId();
		final NsLcmOpOcc lcmOpOccs = nsLcmOpOccsRepository.createLcmOpOccs(nsdId, NsLcmOpType.INSTANTIATE);

		final NsdInfo nsdInfo = nsdRepository.get(nsdId);
		final VimConnectionInformation vimInfo = electVim((String) nsdInfo.getUserDefinedData().get("vimId"), null);
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		// Create Ns.
		final Map<String, Object> userData = nsdInfo.getUserDefinedData();
		final String processId = vim.onNsInstantiate(nsdId, userData);
		LOG.info("Creating a MSA Job: {}", processId);
		// Save Process Id with lcm, XXX/ Don't!!! Save in instance.
		nsLcmOpOccsRepository.attachProcessIdToLcmOpOccs(lcmOpOccs.getId(), processId);
		final VimStatus status = vim.waitForCompletion(processId, 1 * 60);
		if (status.getLcmOperationStateType() != LcmOperationStateType.COMPLETED) {
			// update Lcm OpOccs
			// send Notification.
			LOG.warn("Instance #{} => {}", nsInstance.getId(), status);
			return;
		}
		nsdRepository.changeNsdUpdateState(nsdInfo, NsdUsageStateType.IN_USE);
		// Instantiate each VNF.
		final List<String> vnfPkgIds = nsdInfo.getVnfPkgIds();
		final List<VnfLcmOpOccs> vnfLcmOpOccsIds = new ArrayList<>();
		for (final String vnfId : vnfPkgIds) {
			VnfInstance nsVnfInstance = nsInstance.getVnfInstance().stream().filter(x -> x.getVnfPkg().toString().equals(vnfId)).findFirst().orElse(null);
			if (null == nsVnfInstance) {
				final VnfPackage vnfPackage = vnfPackageRepository.get(UUID.fromString(vnfId));
				final VnfInstance vnfInstance = vnfm.createVnfInstance(vnfPackage, "", "Sub-instance " + nsInstanceId);
				nsVnfInstance = VnfInstanceFactory.createNsInstancesNsInstanceVnfInstance(vnfInstance, "vimId?");
				nsInstance.getVnfInstance().add(nsVnfInstance);
				nsInstanceRepository.save(nsInstance);
			}
			final VnfLcmOpOccs vnfLcmOpOccs = vnfm.vnfInstatiate(nsVnfInstance.getId().toString(), vnfId);
			vnfLcmOpOccsIds.add(vnfLcmOpOccs);
		}
		// Link VNF lcm OP OCCS to this operation.
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		// wait for completion
		waitForCompletion(vnfLcmOpOccsIds);
		// update lcm op occs
		refreshVnfLcmOpOccsIds(vnfLcmOpOccsIds);
		vnfLcmOpOccsRepository.save(vnfLcmOpOccsIds);
		final LcmOperationStateType resultStatus = computeStatus(vnfLcmOpOccsIds);
		updateOperationState(lcmOpOccs, resultStatus);
		// event->create (we have lcm op occs.)
		eventManager.sendNotification(NotificationEvent.NS_INSTANTIATE, nsInstanceId);
	}

	private List<VnfLcmOpOccs> refreshVnfLcmOpOccsIds(final List<VnfLcmOpOccs> vnfLcmOpOccsIds) {
		final List<VnfLcmOpOccs> res = new ArrayList<>();
		for (final VnfLcmOpOccs vnfLcmOpOcc : vnfLcmOpOccsIds) {
			final VnfLcmOpOccs newLcmOpOc = vnfm.getVnfLcmOpOccs(vnfLcmOpOcc.getId());
			res.add(newLcmOpOc);
		}
		return res;
	}

	private void waitForCompletion(@Nonnull final List<VnfLcmOpOccs> vnfLcmOpOccss) {
		List<VnfLcmOpOccs> ret = new ArrayList<>(vnfLcmOpOccss);
		while (true) {
			ret = vnfCycle(ret);
			if (ret.isEmpty()) {
				break;
			}
			sleepSeconds(1 * 60L);
		}
	}

	private static void sleepSeconds(final long seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (final InterruptedException e) {
			LOG.warn("Interrupted exception.", e);
			Thread.currentThread().interrupt();
		}
	}

	private List<VnfLcmOpOccs> vnfCycle(final List<VnfLcmOpOccs> vnfLcmOpOccss) {
		final List<VnfLcmOpOccs> ret = new ArrayList<>(vnfLcmOpOccss);
		for (final VnfLcmOpOccs vnfLcmOpOcc : vnfLcmOpOccss) {
			final VnfLcmOpOccs res = vnfm.getVnfLcmOpOccs(vnfLcmOpOcc.getId());
			if (res.getOperationState() == LcmOperationStateType.PROCESSING) {
				ret.add(vnfLcmOpOcc);
			}
		}
		return ret;
	}

	public void grantRequest(final String objectId) {
		final Optional<Grants> grantsOpt = grantJpa.findById(UUID.fromString(objectId));
		final Grants grants = grantsOpt.orElseThrow(() -> new NotFoundException("Grant ID " + objectId + " Not found."));

		// XXX We should use planner API for this.
		grants.getRemoveResources().forEach(x -> {
			if (x.getReservationId() != null) {
				final VimConnectionInformation vci = vimManager.findVimById(UUID.fromString(x.getVimConnectionId()));
				final Vim vim = vimManager.getVimById(UUID.fromString(x.getVimConnectionId()));
				vim.freeResources(vci, x);
			}
		});
		final VnfPackage vnfPackage = getPackageFromVnfInstanceId(UUID.fromString(grants.getVnfInstanceId()));
		final VimConnectionInformation vimInfo = electVim(vnfPackage.getUserDefinedData().get("vimId"), grants.getAddResources());
		// XXX One timeor for each resources?
		final String zoneId = chooseZone(vimInfo);
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		grants.getAddResources().forEach(x -> {
			vim.allocateResources(vimInfo, x);
			x.setResourceDefinitionId(x.getVduId().toString());
			x.setResourceProviderId(vim.getType());
			x.setVimConnectionId(vimInfo.getId().toString());
			x.setZoneId(zoneId);
		});

		grants.setVimConnections(Collections.singleton(vimInfo));

		final GrantVimAssetsEntity grantVimAssetsEntity = new GrantVimAssetsEntity();
		grantVimAssetsEntity.setSoftwareImages(getSoftwareImage(vnfPackage, vimInfo, vim));
		grantVimAssetsEntity.getComputeResourceFlavours().addAll(getFlavors(vnfPackage, vimInfo, vim));
		grants.setVimAssets(grantVimAssetsEntity);
		grants.setAvailable(Boolean.TRUE);
		grantJpa.save(grants);
		LOG.info("Grant {} Available.", grants.getId());
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
		if (null != softwareImage.getVimId()) {
			// XXX
		} else {
			final VimImage vimImage = vim.getImagesInformations(vimInfo, softwareImage.getName());
			vsie.setVimSoftwareImageId(vimImage.getId());
		}
		return vsie;
	}

	private VimConnectionInformation electVim(final String vimId, final Set<GrantInformation> set) {
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
