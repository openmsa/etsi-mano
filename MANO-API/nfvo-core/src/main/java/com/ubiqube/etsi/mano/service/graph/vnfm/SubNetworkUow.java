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
package com.ubiqube.etsi.mano.service.graph.vnfm;

import java.util.Map;
import java.util.Optional;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.SubNetworkTask;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SubNetworkUow extends AbstractUnitOfWork {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(SubNetworkUow.class);

	private final SubNetworkTask task;

	public SubNetworkUow(final SubNetworkTask _task) {
		super(_task);
		task = _task;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		final String networkId = context.get(task.getParentName());
		return vim.createSubnet(vimConnectionInformation, task.getL3Data(), task.getIpPool(), networkId);
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteSubnet(vimConnectionInformation, resourceId);
		return null;
	}

	@Override
	public void connect(final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, final Map<String, UnitOfWork> cache) {
		final Optional<UnitOfWork> parent = Optional.ofNullable(cache.get(task.getParentName()));
		LOG.warn("Subnetwork: " + task.getAlias() + " => " + task.getParentName() + " => " + cache);
		parent.ifPresent(x -> {
			g.addEdge(x, this);
		});
	}

	@Override
	protected String getPrefix() {
		return "subnet";
	}

}
