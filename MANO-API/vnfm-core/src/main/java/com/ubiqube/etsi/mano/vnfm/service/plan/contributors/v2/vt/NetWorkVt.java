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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Network;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NetWorkVt extends VnfVtBase<NetworkTask> {

	public NetWorkVt(final NetworkTask networkTask) {
		super(networkTask);
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		return new ArrayList<>();
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return Arrays.asList(new NamedDependency(Network.class, getParameters().getToscaName()));
	}

	@Override
	public String getProviderId() {
		return "NETWORK";
	}

}
