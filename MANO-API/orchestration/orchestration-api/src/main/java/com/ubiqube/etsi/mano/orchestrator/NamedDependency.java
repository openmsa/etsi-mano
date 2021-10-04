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

import com.ubiqube.etsi.mano.orchestrator.nodes.Node;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
public class NamedDependency {
	private final String name;
	private final Class<? extends Node> type;

	public NamedDependency(final Class<? extends Node> type, final String name) {
		this.name = name;
		this.type = type;
	}

	public boolean match(final NamedDependency y) {
		if (y.getType() != type) {
			return false;
		}
		if (!y.getName().equals(name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "NamedDependency [name=" + name + ", type=" + type + "]";
	}

}
