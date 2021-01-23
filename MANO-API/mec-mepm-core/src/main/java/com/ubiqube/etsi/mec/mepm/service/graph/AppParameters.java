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
package com.ubiqube.etsi.mec.mepm.service.graph;

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mec.mepm.repositories.AppLiveInstanceJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class AppParameters extends GenericExecParams {
	private final VimConnectionInformation vimConnectionInformation;
	private final Vim vim;
	private final AppLiveInstanceJpa vnfLiveInstanceJpa;
	private final Map<String, String> context;

	public AppParameters(final VimConnectionInformation vimConnectionInformation, final Vim vim, final AppLiveInstanceJpa vnfLiveInstanceJpa, final Map<String, String> context, final String _vimResourceId) {
		super(context, _vimResourceId);
		this.vimConnectionInformation = vimConnectionInformation;
		this.vim = vim;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.context = context;
	}

}
