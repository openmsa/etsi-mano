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

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfParameters extends GenericExecParams {

	public VnfParameters(final VimConnectionInformation vimConnectionInformation, final Vim vim, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final Map<String, String> context) {
		// TODO Auto-generated constructor stub
	}

	public VimConnectionInformation getVimConnectionInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	public VnfLiveInstanceJpa getVnfLiveInstanceJpa() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vim getVim() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVimResourceId() {
		// TODO Auto-generated method stub
		return null;
	}

}
