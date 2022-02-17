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
package com.ubiqube.etsi.mano.nfvo.service.plan.contributors.vt;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsVnfTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnfCreateNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.service.graph.vt.NsVtBase;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsVnfCreateVt extends NsVtBase<NsVnfTask> {

	public NsVnfCreateVt(final NsVnfTask nt) {
		super(nt);
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		return Optional.ofNullable(getParameters())
				.map(NsVnfTask::getExternalNetworks)
				.orElseGet(LinkedHashSet::new)
				.stream()
				.map(x -> new NamedDependency(Network.class, x.getToscaName()))
				.toList();
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return Arrays.asList(new NamedDependency(VnfCreateNode.class, getParameters().getAlias()));
	}

	@Override
	public String getFactoryProviderId() {
		return "VNF-CREATE";
	}

	@Override
	public String getVimProviderId() {
		return "VNF-CREATE";
	}

}
