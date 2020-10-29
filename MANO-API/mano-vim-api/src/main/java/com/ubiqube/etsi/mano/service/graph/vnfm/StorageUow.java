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
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.vim.Vim;

public class StorageUow extends AbstractUnitOfWork {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private final VnfStorage vnfStorage;

	private final VnfInstantiatedStorage vnfInstantiedStorage;

	public StorageUow(final VnfInstantiatedStorage _vnfInstantiedStorage, final VnfStorage x) {
		super(_vnfInstantiedStorage, x.getToscaName());
		vnfStorage = x;
		vnfInstantiedStorage = _vnfInstantiedStorage;
	}

	@Override
	public String exec(final VimConnectionInformation vimConnectionInformation, final Vim vim, final Map<String, String> context) {
		return vim.createStorage(vimConnectionInformation, vnfStorage, vnfInstantiedStorage.getAliasName());
	}

	@Override
	public UowType getType() {
		return UowType.VSTORAGE;
	}

	@Override
	protected String getPrefix() {
		return "block_storage";
	}

	@Override
	public String rollback(final VimConnectionInformation vimConnectionInformation, final Vim vim, final String resourceId, final Map<String, String> context) {
		vim.deleteStorage(vimConnectionInformation, resourceId);
		return null;
	}

}
