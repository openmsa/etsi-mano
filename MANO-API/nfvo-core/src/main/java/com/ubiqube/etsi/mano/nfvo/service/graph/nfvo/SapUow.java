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
package com.ubiqube.etsi.mano.nfvo.service.graph.nfvo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSapTask;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.NsVlNode;
import com.ubiqube.etsi.mano.orchestrator.nodes.nfvo.SapNode;
import com.ubiqube.etsi.mano.service.graph.WfDependency;
import com.ubiqube.etsi.mano.service.graph.WfProduce;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SapUow extends AbstractNsUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	private final NsSapTask nsSapd;

	public SapUow(final NsSapTask _taskEntity) {
		super(_taskEntity);
		nsSapd = _taskEntity;
	}

	@Override
	protected String getPrefix() {
		return "sap";
	}

	@Override
	public String exec(final NsParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rollback(final NsParameters params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WfDependency> getDependencies() {
		final WfDependency ext = new WfDependency(NsVlNode.class, nsSapd.getNsSap().getExternalVirtualLink());
		final WfDependency inte = new WfDependency(NsVlNode.class, nsSapd.getNsSap().getInternalVirtualLink());
		final List<WfDependency> arr = new ArrayList<>();
		arr.add(inte);
		arr.add(ext);
		return arr;
	}

	@Override
	public List<WfProduce> getProduce() {
		return Arrays.asList(new WfProduce(SapNode.class, nsSapd.getToscaName(), nsSapd.getId()));
	}

}
