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
package com.ubiqube.etsi.mano.service.graph.vt;

import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;
import com.ubiqube.etsi.mano.orchestrator.SystemBuilder;
import com.ubiqube.etsi.mano.orchestrator.vt.VirtualTask;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class NsVtBase<U extends NsTask> implements VirtualTask<U> {

	private U nt;
	private SystemBuilder db;

	protected NsVtBase(final U nt) {
		this.nt = nt;
	}

	@Override
	public final U getParameters() {
		return nt;
	}

	@Override
	public void setParameters(final U u) {
		nt = u;
	}

	@Override
	public final void setSystemBuilder(final SystemBuilder db) {
		this.db = db;
	}

	@Override
	public final SystemBuilder getSystemBuilder() {
		return db;
	}

	@Override
	public final boolean isDeleteTask() {
		return nt.getChangeType() == ChangeType.REMOVED;
	}

	@Override
	public final String getVimConnectionId() {
		return nt.getVimConnectionId();
	}

	@Override
	public final String getName() {
		return nt.getToscaName();
	}

	@Override
	public final String getAlias() {
		return nt.getAlias();
	}

}
