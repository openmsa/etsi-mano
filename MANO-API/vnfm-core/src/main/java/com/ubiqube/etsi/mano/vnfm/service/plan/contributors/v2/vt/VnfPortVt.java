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
package com.ubiqube.etsi.mano.vnfm.service.plan.contributors.v2.vt;

import java.util.Arrays;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.VnfPortTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.VnfPortNode;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfPortVt extends VnfVtBase<VnfPortTask> {

	public VnfPortVt(final VnfPortTask nt) {
		super(nt);
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		return Arrays.asList(new NamedDependency(Network.class, getParameters().getVnfLinkPort().getVirtualLink()));
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return Arrays.asList(new NamedDependency(VnfPortNode.class, getParameters().getToscaName()));
	}

	@Override
	public String getFactoryProviderId() {
		return "PORT";
	}

	@Override
	public String getVimProviderId() {
		return "PORT";
	}

}
