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
package com.ubiqube.etsi.mano.nfvo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VimTask;
import com.ubiqube.etsi.mano.dao.mano.v2.Blueprint;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.VimResourceService;
import com.ubiqube.etsi.mano.service.vim.VimManager;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NfvoVimAllocate implements VimResourceService {
	private final VimManager vimManager;

	public NfvoVimAllocate(final VimManager vimManager) {
		super();
		this.vimManager = vimManager;
	}

	@Override
	public void allocate(final Blueprint plan) {
		final Iterable<VimConnectionInformation> ite = vimManager.findAllVimconnections();
		if (!ite.iterator().hasNext()) {
			throw new GenericException("No vim have been registered.");
		}
		final HashSet<VimConnectionInformation> vim = new HashSet<>();
		final VimConnectionInformation vimConnection = ite.iterator().next();
		vim.add(vimConnection);
		fixUnknownTask(plan.getTasks(), vim);
		plan.setVimConnections(vim);
	}

	private static void fixUnknownTask(final Set<? extends VimTask> tasks, final Set<VimConnectionInformation> vimConnections) {
		final VimConnectionInformation vimConn = vimConnections.iterator().next();
		tasks.stream()
				.filter(x -> x.getVimConnectionId() == null)
				.forEach(x -> x.setVimConnectionId(vimConn.getVimId()));
	}

}
