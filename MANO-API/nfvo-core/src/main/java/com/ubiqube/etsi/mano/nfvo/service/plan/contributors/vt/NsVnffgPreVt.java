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

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSfcTask;
import com.ubiqube.etsi.mano.orchestrator.NamedDependency;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.VnffgPreNode;
import com.ubiqube.etsi.mano.service.graph.vt.NsVtBase;

/**
 * Pre task if needed by implementation of SFC.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class NsVnffgPreVt extends NsVtBase<NsSfcTask> {

	protected NsVnffgPreVt(final NsSfcTask nt) {
		super(nt);
	}

	@Override
	public List<NamedDependency> getNameDependencies() {
		return List.of();
	}

	@Override
	public List<NamedDependency> getNamedProduced() {
		return List.of(new NamedDependency(VnffgPreNode.class, getName()));
	}

	@Override
	public String getFactoryProviderId() {
		return "SFC";
	}

	@Override
	public String getVimProviderId() {
		return "PRE-NETWORK";
	}

}
