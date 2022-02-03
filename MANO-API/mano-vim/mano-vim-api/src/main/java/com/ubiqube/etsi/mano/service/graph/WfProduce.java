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
package com.ubiqube.etsi.mano.service.graph;

import java.util.UUID;

import com.ubiqube.etsi.mano.orchestrator.nodes.Node;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class WfProduce {
	private final Class<? extends Node> node;
	private final UUID uuid;
	private final String name;

	public WfProduce(final Class<? extends Node> node, final String name, final UUID uuid) {
		super();
		this.node = node;
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Class<? extends Node> getNode() {
		return node;
	}

	public String getName() {
		return name;
	}

}
