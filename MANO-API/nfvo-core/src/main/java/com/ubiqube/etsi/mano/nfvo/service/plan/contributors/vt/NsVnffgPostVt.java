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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ubiqube.etsi.mano.dao.mano.nsd.CpPair;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSfcTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnffgPostNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;
import com.ubiqube.etsi.mano.service.graph.vt.NsVtBase;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsVnffgPostVt extends NsVtBase<NsSfcTask> {

	public NsVnffgPostVt(final NsSfcTask nt) {
		super(nt);
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		final ArrayList<NamedDependency> ret = new ArrayList<>();
		final NsSfcTask task = getParameters();
		final List<CpPair> cpPairs = task.getVnffg().getNfpd()
				.stream()
				.flatMap(x -> x.getInstancces().stream())
				.flatMap(x -> x.getPairs().stream())
				.toList();
		cpPairs.forEach(x -> {
			Optional.ofNullable(x.getIngress()).ifPresent(y -> ret.add(new NamedDependency(VnfPortNode.class, y)));
			Optional.ofNullable(x.getEgress()).ifPresent(y -> ret.add(new NamedDependency(VnfPortNode.class, y)));
		});
		// ret.add(new NamedDependency(Network.class,
		// task.getVnffg().getVirtualLinkId()));
		return ret;
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return List.of(new NamedDependency(VnffgPostNode.class, getName()));
	}

	@Override
	public String getFactoryProviderId() {
		return "SFC";
	}

	@Override
	public String getVimProviderId() {
		return "NETWORK";
	}

}
