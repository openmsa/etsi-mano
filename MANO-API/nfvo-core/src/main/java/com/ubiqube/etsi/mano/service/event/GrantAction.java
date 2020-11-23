/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantVimAssetsEntity;
import com.ubiqube.etsi.mano.dao.mano.ResourceTypeEnum;
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
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;
import com.ubiqube.etsi.mano.service.vim.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.utils.SpringUtils;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
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
		} catch (final RuntimeException e) {
			LOG.error("Removing Grand id: {}", objectId, e);
			grantJpa.deleteById(objectId);
		}
	}

	public void grantRequestException(final UUID objectId) throws InterruptedException, ExecutionException {
		LOG.info("Evaluating grant {}", objectId);
		final Optional<GrantResponse> grantsOpt = grantJpa.findById(objectId);
		final GrantResponse grants = grantsOpt.orElseThrow(() -> new NotFoundException("Grant ID " + objectId + " Not found."));
		final ExecutorService executorService = SpringUtils.getFixedThreadPool(10, "grant");
		// Free Lease if any.
		grants.getRemoveResources().forEach(x -> {
			if (x.getReservationId() != null) {
				final VimConnectionInformation vci = vimManager.findVimById(UUID.fromString(x.getVimConnectionId()));
				final Vim vim = vimManager.getVimById(UUID.fromString(x.getVimConnectionId()));
				vim.freeResources(vci, x);
			}
		});
		final VnfPackage vnfPackage = getPackageFromVnfInstanceId(UUID.fromString(grants.getVnfInstanceId()));
		final VimConnectionInformation vimInfo = electVim(vnfPackage.getUserDefinedData().get("vimId"), grants, vnfPackage);
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
			final List<ServerGroup> sg = new ArrayList<>();
			final ServerGroup serverGroup = new ServerGroup("1", "az", "az");
			sg.add(serverGroup);
			// final List<ServerGroup> sg = vim.getServerGroup(vimInfo);
			final Set<String> sgList = sg.stream().map(ServerGroup::getId).collect(Collectors.toSet());
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
			try {
				grantVimAssetsEntity.getComputeResourceFlavours().addAll(getFlavors(vnfPackage, vimInfo, vim));
			} catch (final RuntimeException e) {
				LOG.error("", e);
			}
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
			x.setResourceGroupId(zgi.getZoneId().iterator().next());
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
			final String key = x.getNumVcpu() + "-" + x.getVirtualMemorySize() + "-" + x.getDiskSize();
			final VimComputeResourceFlavourEntity vcretmp = cache.computeIfAbsent(key, y -> {
				final String flavorId = vim.getOrCreateFlavor(vimConnectionInformation, x.getName(), (int) x.getNumVcpu(), x.getVirtualMemorySize(), x.getDiskSize());
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
					// Use or-vi, Vim is not on the same server. Path is given in tosca file.
					return newImg.orElseGet(() -> vim.uploadSoftwareImage(vimInfo, x.getSoftwareImage()));
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

	private VimConnectionInformation electVim(final String vnfPackageVimId, final GrantResponse grantResponse, final VnfPackage vnfPackage) {
		final Set<VimConnectionInformation> vimConns = grantResponse.getVimConnections();
		String vimId;
		if ((null != vimConns) && !vimConns.isEmpty()) {
			LOG.info("Selecting vim via Given one.");
			vimId = vimConns.iterator().next().getVimId();
			return vimManager.findVimByVimId(vimId);
		}
		// XXX: Do some real elections.
		final Set<VimConnectionInformation> vims;
		if (null != vnfPackageVimId) {
			LOG.debug("Getting MSA 2.x VIM");
			vims = vimManager.getVimByType("MSA_20");
			return vims.iterator().next();
		}
		LOG.debug("Getting OS v3 VIM");
		vims = vimManager.getVimByType("OPENSTACK_V3");
		if (vims.isEmpty()) {
			throw new GenericException("Couldn't find a VIM.");
		}
		return doNormalElection(vnfPackage, grantResponse, vims);
	}

	private VimConnectionInformation doNormalElection(final VnfPackage vnfPackage, final GrantResponse grantResponse, final Set<VimConnectionInformation> vims) {
		final QuotaNeeded needed = summarizeResources(grantResponse, vnfPackage);
		final List<VimConnectionInformation> vimsSelected = new ArrayList<>();
		vims.parallelStream().forEach(x -> {
			final Vim vim = vimManager.getVimById(x.getId());
			final ResourceQuota quota = vim.getQuota(x);
			if (needed.getRam() > quota.getRamFree()) {
				LOG.debug("Removing vim {}: RAM needed: {} free: {}", x.getVimId(), needed.getRam(), quota.getRamFree());
				return;
			}
			if (needed.getVcpu() > quota.getVcpuFree()) {
				LOG.debug("Removing vim {}: Vcpu needed: {} free: {}", x.getVimId(), needed.getVcpu(), quota.getVcpuFree());
				return;
			}
			vimsSelected.add(x);
		});
		if (vimsSelected.isEmpty()) {
			throw new GenericException("No Vim found, after quota filtering.");
		}
		return vimsSelected.get(new Random().nextInt(vimsSelected.size()));
	}

	private QuotaNeeded summarizeResources(final GrantResponse grantResponse, final VnfPackage vnfPackage) {
		final Set<GrantInformationExt> adds = grantResponse.getAddResources();
		int disk = 0;
		int vcpu = 0;
		int ram = 0;
		for (final GrantInformationExt grantInformationExt : adds) {
			if (grantInformationExt.getType() == ResourceTypeEnum.COMPUTE) {
				final VnfCompute compute = findCompute(vnfPackage, grantInformationExt.getVduId());
				disk += compute.getDiskSize();
				vcpu += compute.getNumVcpu();
				ram += compute.getVirtualMemorySize();
			} else if (grantInformationExt.getType() == ResourceTypeEnum.STORAGE) {
				// Cinder.
			}
		}
		return new QuotaNeeded(disk, vcpu, ram);
	}

	private static VnfCompute findCompute(final VnfPackage vnfPackage, final UUID vduId) {
		return vnfPackage.getVnfCompute().stream()
				.filter(x -> x.getId().compareTo(vduId) == 0)
				.findFirst()
				.orElseThrow(() -> new NotFoundException("VduId not found " + vduId));
	}

	class QuotaNeeded {
		private int disk = 0;
		private int vcpu = 0;
		private int ram = 0;

		public int getDisk() {
			return disk;
		}

		public void setDisk(final int disk) {
			this.disk = disk;
		}

		public int getVcpu() {
			return vcpu;
		}

		public void setVcpu(final int vcpu) {
			this.vcpu = vcpu;
		}

		public int getRam() {
			return ram;
		}

		public void setRam(final int ram) {
			this.ram = ram;
		}

		public QuotaNeeded(final int disk, final int vcpu, final int ram) {
			super();
			this.disk = disk;
			this.vcpu = vcpu;
			this.ram = ram;
		}

	}
}
