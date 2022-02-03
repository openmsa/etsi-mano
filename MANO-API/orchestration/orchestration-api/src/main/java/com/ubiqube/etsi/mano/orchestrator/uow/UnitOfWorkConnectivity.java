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

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 * @param <U>
 * @deprecated use ConnectivityEdge<UnitOfWork<U>>
 */
@Deprecated(forRemoval = true)
public class UnitOfWorkConnectivity<U> {
	private UnitOfWork<U> source;

	private UnitOfWork<U> target;

	public UnitOfWorkConnectivity() {
		// Nothing.
	}

	public UnitOfWorkConnectivity(final UnitOfWork<U> source, final UnitOfWork<U> target) {
		this.source = source;
		this.target = target;
	}

	public UnitOfWork<U> getSource() {
		return source;
	}

	public void setSource(final UnitOfWork<U> source) {
		this.source = source;
	}

	public UnitOfWork<U> getTarget() {
		return target;
	}

	public void setTarget(final UnitOfWork<U> target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ConnectivityEdge [source=" + source + ", target=" + target + "]";
	}

}
