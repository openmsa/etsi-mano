package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.search.util.impl.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
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
import com.ubiqube.etsi.mano.repository.VnfInstancesRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.vim.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

@Service
public class GrantAction {

	private static final Logger LOG = LoggerFactory.getLogger(GrantAction.class);

	private final GrantsResponseJpa grantJpa;

	private final VimManager vimManager;

	private final VnfPackageRepository vnfPackageRepository;

	private final VnfInstancesRepository vnfInstancesRepository;

	public GrantAction(final GrantsResponseJpa _grantJpa, final VimManager _vimManager, final VnfPackageRepository _vnfPackageRepository, final VnfInstancesRepository _vnfInstancesRepository) {
		grantJpa = _grantJpa;
		vimManager = _vimManager;
		vnfPackageRepository = _vnfPackageRepository;
		vnfInstancesRepository = _vnfInstancesRepository;
	}

	public void grantRequest(final UUID objectId) {
		try {
			grantRequestException(objectId);
		} catch (InterruptedException | ExecutionException e) {
			Thread.currentThread().interrupt();
			throw new GenericException(e);
		}
	}

	public void grantRequestException(final UUID objectId) throws InterruptedException, ExecutionException {
		LOG.info("Evaluating grant {}", objectId);
		final Optional<GrantResponse> grantsOpt = grantJpa.findById(objectId);
		final GrantResponse grants = grantsOpt.orElseThrow(() -> new NotFoundException("Grant ID " + objectId + " Not found."));
		final ExecutorService executorService = Executors.newFixedThreadPool(10, "grant-" + objectId);
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
		final Callable<String> getZone = () -> {
			final String zoneId = chooseZone(vimInfo);
			final ZoneInfoEntity zones = mapZone(zoneId, vimInfo);
			grants.addZones(zones);
			return zoneId;
		};
		final Future<String> futureZone = executorService.submit(getZone);
		// Zone Group
		final Vim vim = vimManager.getVimById(vimInfo.getId());
		final Callable<ZoneGroupInformation> getServerGroup = () -> {
			final List<ServerGroup> sg = vim.getServerGroup(vimInfo);
			final List<String> sgList = sg.stream().map(x -> x.getId()).collect(Collectors.toList());
			final ZoneGroupInformation zgi = new ZoneGroupInformation();
			zgi.setZoneId(sgList);
			grants.setZoneGroups(Collections.singleton(zgi));
			return zgi;
		};
		final Future<ZoneGroupInformation> futureSg = executorService.submit(getServerGroup);

		grants.setVimConnections(Collections.singleton(vimInfo));

		final GrantVimAssetsEntity grantVimAssetsEntity = new GrantVimAssetsEntity();
		// XXX Push only needed ones. ( in case of terminate no need to push assets.)
		final Callable<Void> getSoftwareImages = () -> {
			grantVimAssetsEntity.setSoftwareImages(getSoftwareImage(vnfPackage, vimInfo, vim));
			return null;
		};
		executorService.submit(getSoftwareImages);

		final Callable<Void> getComputeResourceFlavours = () -> {
			grantVimAssetsEntity.getComputeResourceFlavours().addAll(getFlavors(vnfPackage, vimInfo, vim));
			return null;
		};
		executorService.submit(getComputeResourceFlavours);

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
		final String zoneId = futureZone.get();
		final ZoneGroupInformation zgi = futureSg.get();
		// XXX It depends on Grant policy GRANT_RESERVE_SINGLE.
		grants.getAddResources().forEach(x -> {
			vim.allocateResources(vimInfo, x);
			x.setResourceProviderId(vim.getType());
			x.setVimConnectionId(vimInfo.getId().toString());
			x.setZoneId(zoneId);
			x.setResourceGroupId(zgi.getZoneId().get(0));
		});
		grants.setVimAssets(grantVimAssetsEntity);
		grants.setAvailable(Boolean.TRUE);
		LOG.debug("Shutdown Grant executor.");
		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.MINUTES);
		LOG.debug("Saving...");
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
		final Map<String, VimComputeResourceFlavourEntity> cache = new HashMap<>();
		vnfPackage.getVnfCompute().forEach(x -> {
			final String key = x.getNumVcpu() + "-" + x.getVirtualMemorySize();
			final VimComputeResourceFlavourEntity vcretmp = cache.computeIfAbsent(key, y -> {
				final String flavorId = vim.getOrCreateFlavor(vimConnectionInformation, x.getName(), (int) x.getNumVcpu(), x.getVirtualMemorySize(), 10);
				final VimComputeResourceFlavourEntity vcrfe = new VimComputeResourceFlavourEntity();
				vcrfe.setVimConnectionId(vimConnectionInformation.getId().toString());
				vcrfe.setResourceProviderId(vim.getType());
				vcrfe.setVimFlavourId(flavorId);
				return vcrfe;
			});
			final VimComputeResourceFlavourEntity vcre = new VimComputeResourceFlavourEntity(vcretmp);
			vcre.setVnfdVirtualComputeDescId(x.getId().toString());
			listVcrfe.add(vcre);
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
		final Map<String, SoftwareImage> cache = new HashMap<>();
		vnfc.forEach(x -> {
			final SoftwareImage img = x.getSoftwareImage();
			if (null != img) {
				// Get Vim or create vim resource via Or-Vi
				final SoftwareImage imgCached = cache.computeIfAbsent(img.getName(), y -> {
					final Optional<SoftwareImage> newImg = vim.getSwImageMatching(vimInfo, img);
					return newImg.orElseGet(() -> {
						// Use or-vi, Vim is not on the same server. and where is the path ?
						return vim.uploadSoftwareImage(vimInfo, x.getSoftwareImage());
					});
				});
				listVsie.add(mapSoftwareImage(imgCached, x.getId(), vimInfo, vim));
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
		vsie.setVimSoftwareImageId(softwareImage.getId().toString());
		vsie.setVnfdSoftwareImageId(vduId.toString());
		vsie.setVimConnectionId(vimInfo.getId().toString());
		vsie.setResourceProviderId(vim.getType());
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
