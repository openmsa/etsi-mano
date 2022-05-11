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
package com.ubiqube.etsi.mano.service.event.flavor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimComputeResourceFlavourEntity;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.pkg.VirtualCpu;
import com.ubiqube.etsi.mano.dao.mano.pkg.VirtualMemory;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;
import com.ubiqube.etsi.mano.vim.dto.Flavor;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class FlavorManager {
	private final VimManager vimManager;

	public FlavorManager(final VimManager vimManager) {
		super();
		this.vimManager = vimManager;
	}

	public List<VimComputeResourceFlavourEntity> getFlavors(final VimConnectionInformation vimConnectionInformation, final Set<VnfCompute> vnfCompute) {
		final Vim vim = vimManager.getVimById(vimConnectionInformation.getId());
		final boolean canCreate = vim.canCreateFlavor();
		return matchExect(vnfCompute, vimConnectionInformation, vim, canCreate);
	}

	private List<VimComputeResourceFlavourEntity> matchExect(final Set<VnfCompute> vnfCompute, final VimConnectionInformation vimConnectionInformation, final Vim vim, final boolean exactMatch) {
		final List<Flavor> flavors = vim.getFlavorList(vimConnectionInformation);
		final List<VimComputeResourceFlavourEntity> listVcrfe = new ArrayList<>();
		final Map<String, VnfCompute> cache = new HashMap<>();
		vnfCompute.forEach(x -> {
			final String flvId = findOrCreateFlavor(vimConnectionInformation, x, x.getDiskSize(), flavors, exactMatch, cache);
			final VimComputeResourceFlavourEntity vcrfe = new VimComputeResourceFlavourEntity();
			vcrfe.setVimConnectionId(vimConnectionInformation.getVimId());
			vcrfe.setResourceProviderId(vim.getType());
			vcrfe.setVimFlavourId(flvId);
			final VimComputeResourceFlavourEntity vcre = new VimComputeResourceFlavourEntity(vcrfe);
			vcre.setVnfdVirtualComputeDescId(x.getToscaName());
			listVcrfe.add(vcre);
		});
		return listVcrfe;
	}

	private String findOrCreateFlavor(final VimConnectionInformation vimConnectionInformation, final VnfCompute comp, final long disk, final List<Flavor> flavors, final boolean exactMatch, final Map<String, VnfCompute> cache) {
		final VirtualCpu vCpu = comp.getVirtualCpu();
		final VirtualMemory vMem = comp.getVirtualMemory();
		final Optional<Entry<String, VnfCompute>> str = isInCache(cache, vCpu, vMem);
		if (str.isPresent()) {
			return str.get().getKey();
		}
		final List<Flavor> basicFlavor = findByBasicAttribute(flavors, vCpu, vMem, comp.getDiskSize(), exactMatch);
		if (basicFlavor.isEmpty()) {
			final String flv = createFlavor(vimConnectionInformation, vCpu, vMem, comp.getToscaName(), disk);
			cache.put(flv, comp);
			return flv;
		}
		if (haveAdditinalRequirement(vCpu, vMem)) {
			final List<Flavor> flv2 = findFlavors(basicFlavor, vCpu, vMem);
			if (flv2.isEmpty()) {
				final String flv = createFlavor(vimConnectionInformation, vCpu, vMem, comp.getToscaName(), disk);
				cache.put(flv, comp);
				return flv;
			}
			return flv2.get(0).getId();
		}
		return basicFlavor.get(0).getId();
	}

	private static Optional<Entry<String, VnfCompute>> isInCache(final Map<String, VnfCompute> cache, final VirtualCpu vCpu, final VirtualMemory vMem) {
		return cache.entrySet().stream().filter(x -> {
			final VnfCompute vnfc = x.getValue();
			final boolean cpuEq = isEqual(vnfc.getVirtualCpu(), vCpu);
			final boolean memEq = isEqual(vnfc.getVirtualMemory(), vMem);
			return cpuEq && memEq;
		}).findFirst();
	}

	private static boolean isEqual(final VirtualMemory virtualMemory, final VirtualMemory vMem) {
		if (!Objects.equals(virtualMemory.getNumaEnabled(), vMem.getNumaEnabled()) || virtualMemory.getVirtualMemSize() != vMem.getVirtualMemSize()) {
			return false;
		}
		final Map<String, String> a = virtualMemory.getVduMemRequirements();
		final Map<String, String> b = vMem.getVduMemRequirements();
		return compareMap(a, b);
	}

	private static boolean isEqual(final VirtualCpu virtualCpu, final VirtualCpu vCpu) {
		if (virtualCpu.getNumVirtualCpu() != vCpu.getNumVirtualCpu()) {
			return false;
		}
		final Map<String, String> a = virtualCpu.getVduCpuRequirements();
		final Map<String, String> b = vCpu.getVduCpuRequirements();
		return compareMap(a, b);
	}

	private static boolean compareMap(final Map<String, String> aIn, final Map<String, String> bIn) {
		final Map<String, String> a = Optional.ofNullable(aIn).orElse(Map.of());
		final Map<String, String> b = Optional.ofNullable(bIn).orElse(Map.of());
		if (a.size() != b.size()) {
			return false;
		}
		final List<Entry<String, String>> res = a.entrySet().stream()
				.filter(x -> b.get(x.getKey()) != null)
				.filter(x -> b.get(x.getKey()).equals(x.getValue()))
				.toList();
		return res.size() == a.size();
	}

	private String createFlavor(final VimConnectionInformation vimConnectionInformation, final VirtualCpu vCpu, final VirtualMemory vMem, final String toscaName, final long disk) {
		final Vim vim = vimManager.getVimById(vimConnectionInformation.getId());
		final Map<String, String> add = new HashMap<>(Optional.ofNullable(vCpu.getVduCpuRequirements()).orElse(Map.of()));
		add.putAll(Optional.ofNullable(vMem.getVduMemRequirements()).orElse(Map.of()));
		return vim.createFlavor(vimConnectionInformation, toscaName, vCpu.getNumVirtualCpu(), vMem.getVirtualMemSize(), disk, add);
	}

	private static List<Flavor> findFlavors(final List<Flavor> basicFlavor, final VirtualCpu vCpu, final VirtualMemory vMem) {
		return basicFlavor.stream().filter(x -> match(x, vCpu, vMem)).toList();
	}

	private static boolean match(final Flavor flv, final VirtualCpu vCpu, final VirtualMemory vMem) {
		final Map<String, String> add = flv.getAdditional();
		final Map<String, String> cpuReq = Optional.ofNullable(vCpu.getVduCpuRequirements()).orElse(Map.of());
		final Map<String, String> memReq = Optional.ofNullable(vMem.getVduMemRequirements()).orElse(Map.of());
		if (null == add) {
			return cpuReq.isEmpty() && memReq.isEmpty();
		}
		for (final Entry<String, String> entry : add.entrySet()) {
			final String k = entry.getKey();
			if (cpuReq.get(k) == null && null == memReq.get(k)) {
				return false;
			}
		}
		return true;
	}

	private static boolean haveAdditinalRequirement(final VirtualCpu vCpu, final VirtualMemory vMem) {
		final boolean vCpuReq = vCpu.getVduCpuRequirements() != null && !vCpu.getVduCpuRequirements().isEmpty();
		final boolean memReq = vMem.getVduMemRequirements() != null && !vMem.getVduMemRequirements().isEmpty();
		return vCpuReq || memReq;
	}

	private static List<Flavor> findByBasicAttribute(final List<Flavor> flavors, final VirtualCpu vCpu, final VirtualMemory vMem, final long diskSize, final boolean exactMatch) {
		if (exactMatch) {
			return flavors.stream().filter(exactMatch(vCpu, vMem, diskSize)).toList();
		}
		return flavors.stream().filter(nearestMatch(vCpu, vMem, diskSize)).toList();
	}

	private static Predicate<? super Flavor> nearestMatch(final VirtualCpu vCpu, final VirtualMemory vMem, final long diskSize) {
		return x -> (vCpu.getNumVirtualCpu() <= x.getVcpus() || vMem.getVirtualMemSize() <= x.getRam() /* || diskSize <= x.getDisk() */);
	}

	private static Predicate<? super Flavor> exactMatch(final VirtualCpu vCpu, final VirtualMemory vMem, final long diskSize) {
		return x -> (vCpu.getNumVirtualCpu() == x.getVcpus() && vMem.getVirtualMemSize() == x.getRam() /* && diskSize == x.getDisk() */);
	}
}
