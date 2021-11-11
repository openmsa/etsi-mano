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
package com.ubiqube.etsi.mano.service.pkg.vnf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.AffinityRule;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.service.pkg.bean.InstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.ProviderData;
import com.ubiqube.etsi.mano.service.pkg.bean.VduInitialDelta;
import com.ubiqube.etsi.mano.service.pkg.bean.VduInstantiationLevels;
import com.ubiqube.etsi.mano.service.pkg.bean.VduScalingAspectDeltas;

public class DefaultVnfPackageReader implements VnfPackageReader {
	@Override
	public ProviderData getProviderPadata() {
		final ProviderData providerData = new ProviderData();
		providerData.setVnfProductName("Ubiqube");
		providerData.setVnfProvider("Ubiqube Openstack HEAT.");
		providerData.setVnfSoftwareVersion("0.0.1");
		providerData.setVnfVersion("0.0.1");
		return providerData;
	}

	@Override
	public Set<AdditionalArtifact> getAdditionalArtefacts(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfCompute> getVnfComputeNodes(final Map<String, String> parameters) {
		final HashSet<VnfCompute> set = new HashSet<>();
		// Create One VDU. for one call on OS createStack.
		final VnfCompute vnfCompute = new VnfCompute();
		vnfCompute.setToscaName("nonet");
		set.add(vnfCompute);
		return set;
	}

	@Override
	public Set<VnfStorage> getVnfStorages(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfVl> getVnfVirtualLinks(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<VnfLinkPort> getVnfVduCp(final Map<String, String> parameters) {
		final Set<VnfLinkPort> ret = new HashSet<>();
		final VnfLinkPort linkPort = new VnfLinkPort();
		linkPort.setVirtualBinding("nonet");
		ret.add(linkPort);
		return ret;
	}

	@Override
	public Set<VnfExtCp> getVnfExtCp(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public Set<ScalingAspect> getScalingAspects(final Map<String, String> parameters) {
		return new HashSet<>();
	}

	@Override
	public List<InstantiationLevels> getInstatiationLevels(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public List<VduInstantiationLevels> getVduInstantiationLevels(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public List<VduInitialDelta> getVduInitialDelta(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public List<VduScalingAspectDeltas> getVduScalingAspectDeltas(final Map<String, String> parameters) {
		return new ArrayList<>();
	}

	@Override
	public Set<SecurityGroup> getSecurityGroup(final Map<String, String> userDefinedData) {
		return new HashSet<>();
	}

	@Override
	public Set<AffinityRule> getAffinityRule(final Map<String, String> userDefinedData) {
		return new HashSet<>();
	}

}
