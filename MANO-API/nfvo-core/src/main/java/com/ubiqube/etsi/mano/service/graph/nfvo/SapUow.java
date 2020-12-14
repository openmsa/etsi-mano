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
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsSapTask;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;

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
	public void connect(final ListenableGraph<UnitOfWork<NsTask, NsParameters>, ConnectivityEdge<UnitOfWork<NsTask, NsParameters>>> g, final Map<String, UnitOfWork<NsTask, NsParameters>> cache) {
		final UnitOfWork<NsTask, NsParameters> extVl = cache.get(nsSapd.getNsSap().getExternalVirtualLink());
		final UnitOfWork<NsTask, NsParameters> intVl = cache.get(nsSapd.getNsSap().getInternalVirtualLink());
		if ((intVl != null) && (extVl != null)) {
			g.addEdge(extVl, intVl);
		}
	}

}
