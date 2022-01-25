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
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsdTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsdCreateNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsCreateVt extends NsVtBase<NsdTask> {

	public NsCreateVt(final NsdTask nt) {
		super(nt);
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		return getParameters().getVirtualLinks().stream().map(x -> new NamedDependency(Network.class, x)).toList();
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return Arrays.asList(new NamedDependency(NsdCreateNode.class, getParameters().getToscaName()));
	}

	@Override
	public String getFactoryProviderId() {
		return "NSD-CREATE";
	}

	@Override
	public String getVimProviderId() {
		return "NSD-CREATE";
	}

}
