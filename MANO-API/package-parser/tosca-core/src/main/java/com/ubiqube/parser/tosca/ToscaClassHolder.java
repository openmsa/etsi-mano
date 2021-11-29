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
package com.ubiqube.parser.tosca;

public class ToscaClassHolder {
	private ToscaClassHolder parent;
	private final ToscaClass node;
	private final String name;

	public ToscaClassHolder(final String name, final ToscaClass node) {
		this.node = node;
		this.name = name;
	}

	public ToscaClassHolder getParent() {
		return parent;
	}

	public void setParent(final ToscaClassHolder parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ToscaClassHolder [parent=" + parent + ", node=" + node + "]\n";
	}

	public boolean isInstanceOf(final String clazz) {
		if (name.equalsIgnoreCase(clazz)) {
			return true;
		}
		if (null == node.getDerivedFrom()) {
			return false;
		}
		if (node.getDerivedFrom().equalsIgnoreCase(clazz)) {
			return true;
		}
		if (parent != null) {
			return parent.isInstanceOf(clazz);
		}
		return false;
	}
}
