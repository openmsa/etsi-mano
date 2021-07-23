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
package com.ubiqube.etsi.mano.orchestrator;

public class TaskConnectivity {
	private Task source;

	private Task target;

	public TaskConnectivity() {
		// Nothing.
	}

	public TaskConnectivity(final Task _source, final Task _target) {
		source = _source;
		target = _target;
	}

	public Task getSource() {
		return source;
	}

	public void setSource(final Task source) {
		this.source = source;
	}

	public Task getTarget() {
		return target;
	}

	public void setTarget(final Task target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "ConnectivityEdge [source=" + source + ", target=" + target + "]";
	}

}
