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
package com.ubiqube.etsi.mano.nfvo.service.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.pkg.OsContainerDeployableUnit;
import com.ubiqube.etsi.mano.dao.mano.vnfi.VimCapability;
import com.ubiqube.etsi.mano.dao.mano.vrqan.VrQan;
import com.ubiqube.etsi.mano.service.event.AbstractGrantAction.QuotaNeeded;
import com.ubiqube.etsi.mano.service.event.PreVimSelection;
import com.ubiqube.etsi.mano.service.vim.VimManager;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VrQanVimSelector implements PreVimSelection {

	private static final String VIM_REJECTED_DUE_TO_MISSING = "Vim rejected due to missing {}: {}";

	private static final Logger LOG = LoggerFactory.getLogger(VrQanVimSelector.class);

	private final VimManager vimManager;
	private final VrQanService vrQanService;

	public VrQanVimSelector(final VimManager vimManager, final VrQanService vrQanService) {
		super();
		this.vimManager = vimManager;
		this.vrQanService = vrQanService;
	}

	@Override
	public List<VimConnectionInformation> selectVims(final VnfPackage vnfPackage, final GrantResponse grantResponse, final QuotaNeeded needed) {
		final Iterable<VimConnectionInformation> it = vimManager.findAllVimconnections();
		final ArrayList<VimConnectionInformation> list = new ArrayList<>();
		it.forEach(list::add);

		return list.stream()
				.filter(x -> checkQuota(x, needed))
				.filter(x -> checkFeature(vnfPackage, x))
				.toList();
	}

	private static boolean checkFeature(final VnfPackage vnfPackage, final VimConnectionInformation x) {
		final Set<VimCapability> caps = x.getVimCapabilities();
		final Set<OsContainerDeployableUnit> ocdu = Optional.ofNullable(vnfPackage.getOsContainerDeployableUnits()).orElse(Set.of());
		if (!ocdu.isEmpty() && !caps.contains(VimCapability.HAVE_CNF)) {
			LOG.debug(VIM_REJECTED_DUE_TO_MISSING, VimCapability.HAVE_CNF, x.getId());
			return false;
		}
		final boolean haveVxNet = haveVxLan(vnfPackage);
		if (haveVxNet && !caps.contains(VimCapability.HAVE_VXNET)) {
			LOG.debug(VIM_REJECTED_DUE_TO_MISSING, VimCapability.HAVE_VXNET, x.getId());
			return false;
		}
		final boolean vlanTrans = haveVlanTransparency(vnfPackage);
		if (vlanTrans && !caps.contains(VimCapability.HAVE_VLAN_TRANSPARENT)) {
			LOG.debug(VIM_REJECTED_DUE_TO_MISSING, VimCapability.HAVE_VLAN_TRANSPARENT, x.getId());
			return false;
		}
		final boolean haveNetMtu = haveMtu(vnfPackage);
		if (haveNetMtu && !caps.contains(VimCapability.HAVE_NET_MTU)) {
			LOG.debug(VIM_REJECTED_DUE_TO_MISSING, VimCapability.HAVE_NET_MTU, x.getId());
			return false;
		}
		return true;
	}

	private static boolean haveMtu(final VnfPackage vnfPackage) {
		return vnfPackage.getVnfVl().stream()
				.flatMap(x -> x.getVlProfileEntity().getVirtualLinkProtocolData().stream())
				.map(x -> x.getL2ProtocolData().getMtu())
				.anyMatch(Objects::nonNull);
	}

	private static boolean haveVlanTransparency(final VnfPackage vnfPackage) {
		return vnfPackage.getVnfVl().stream()
				.flatMap(x -> x.getVlProfileEntity().getVirtualLinkProtocolData().stream())
				.map(x -> x.getL2ProtocolData().getVlanTransparent())
				.filter(Objects::nonNull)
				.anyMatch(Boolean::booleanValue);
	}

	private static boolean haveVxLan(final VnfPackage vnfPackage) {
		return vnfPackage.getVnfVl().stream()
				.flatMap(x -> x.getVlProfileEntity().getVirtualLinkProtocolData().stream())
				.map(x -> x.getL2ProtocolData().getNetworkType())
				.anyMatch("VXLAN"::equalsIgnoreCase);
	}

	private boolean checkQuota(final VimConnectionInformation x, final QuotaNeeded needed) {
		final Optional<VrQan> ovq = vrQanService.findByVimId(x.getId());
		// If not get it on vim
		final VrQan vrQan = ovq.get();
		if (vrQan.getRamFree() < needed.getRam()) {
			LOG.debug("Vim rejected due to RAM {}", x.getId());
			return false;
		}
		if (vrQan.getVcpuFree() < needed.getVcpu()) {
			LOG.debug("Vim rejected due to VCPU {}", x.getId());
			return false;
		}
		return true;
	}

}
