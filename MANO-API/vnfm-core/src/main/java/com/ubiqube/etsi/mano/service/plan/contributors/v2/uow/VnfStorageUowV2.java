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
package com.ubiqube.etsi.mano.service.plan.contributors.v2.uow;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.orchestrator.Context;
import com.ubiqube.etsi.mano.orchestrator.Task;
import com.ubiqube.etsi.mano.orchestrator.nodes.vnfm.Storage;
import com.ubiqube.etsi.mano.service.vim.Vim;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class VnfStorageUowV2 extends AbstractUowV2<StorageTask> {
	private Vim vim;
	private VimConnectionInformation vimConnectionInformation;

	public VnfStorageUowV2(final Task<StorageTask> task) {
		super(task, Storage.class);
	}

	@Override
	public String execute(final Context context) {
		final StorageTask params = getTask().getParameters();
		return vim.storage(vimConnectionInformation).createStorage(params.getVnfStorage(), params.getAlias());
	}

	@Override
	public String rollback(final Context context) {
		final StorageTask params = getTask().getParameters();
		vim.storage(vimConnectionInformation).deleteStorage(params.getVimResourceId());
		return null;
	}

}
