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

import java.io.Serializable;
import java.util.Map;

import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.Task;
import com.ubiqube.etsi.mano.service.graph.UnitOfWorkBase;
import com.ubiqube.etsi.mano.service.vim.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface UnitOfWork extends UnitOfWorkBase, Serializable {

	String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, Map<String, String> context);

	Task getTaskEntity();

	String rollback(VimConnectionInformation vimConnectionInformation, Vim vim, String resourceId, Map<String, String> context);

	void connect(ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g, Map<String, UnitOfWork> cache);
}
