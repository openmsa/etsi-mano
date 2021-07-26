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
package com.ubiqube.etsi.mano.orchestrator.uow;

public class UnitOfWorkConnectivity {
	private UnitOfWork source;

	private UnitOfWork target;

	public UnitOfWorkConnectivity() {
		// Nothing.
	}

	public UnitOfWorkConnectivity(final UnitOfWork _source, final UnitOfWork _target) {
		source = _source;
		target = _target;
	}

	public UnitOfWork getSource() {
		return source;
	}

	public void setSource(final UnitOfWork source) {
		this.source = source;
	}

	public UnitOfWork getTarget() {
		return target;
	}

	public void setTarget(final UnitOfWork target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ConnectivityEdge [source=" + source + ", target=" + target + "]";
	}

}
