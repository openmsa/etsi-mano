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

import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.vim.node.Node;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class WfDependency {
	private Class<? extends Node> node;
	private String name;

	public WfDependency(final Class<? extends Node> _node, final String _name) {
		node = _node;
		name = _name;
	}

	public Class<? extends Node> getNode() {
		return node;
	}

	public void setNode(final Class<? extends Node> node) {
		this.node = node;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean isMatching(final UnitOfWork<?, ?> unitOfWork) {
		for (final WfProduce produce : unitOfWork.getProduce()) {
			if ((node == produce.getNode())
					&& getName().equals(unitOfWork.getToscaName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "WfDependency [node=" + node.getSimpleName() + ", name=" + name + "]";
	}

}
