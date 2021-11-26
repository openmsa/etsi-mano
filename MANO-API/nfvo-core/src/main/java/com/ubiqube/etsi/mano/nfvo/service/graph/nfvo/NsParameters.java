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

import java.util.Map;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.graph.GenericExecParams;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class NsParameters extends GenericExecParams {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	private transient Vim vim;
	private VimConnectionInformation vimConnectionInformation;

	public NsParameters(final Vim vim, final VimConnectionInformation vimConnectionInformation, final Map<String, String> context, final String vimResourceId) {
		super(context, vimResourceId);
		this.vim = vim;
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public Vim getVim() {
		return vim;
	}

	public void setVim(final Vim vim) {
		this.vim = vim;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

}
