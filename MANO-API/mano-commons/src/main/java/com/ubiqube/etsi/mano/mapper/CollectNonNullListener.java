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
package com.ubiqube.etsi.mano.mapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectNonNullListener implements BeanListener {
	private final LinkedList<AttrNode> stack = new LinkedList<>();
	private final List<AttrHolder> attrs = new ArrayList<>();

	@Override
	public void addProperty(final Object source) {
		final AttrHolder ah = new AttrHolder();
		ah.setStack((LinkedList<AttrNode>) stack.clone());
		ah.setValue(source);
		if (null != source) {
			attrs.add(ah);
		}
	}

	@Override
	public void startList(final String name) {
		stack.push(new ListAttrNode(name));
	}

	@Override
	public void endList() {
		stack.pop();
	}

	@Override
	public void listElementStart(final int i) {
		stack.push(new IndiceAttrNode(i));
	}

	@Override
	public void complexStart(final String name) {
		stack.push(new NamedAttrNode(name));
	}

	@Override
	public void complexEnd() {
		stack.pop();
	}

	@Override
	public void listElementEnd() {
		stack.pop();
	}

	public List<AttrHolder> getAttrs() {
		return attrs;
	}

	@Override
	public void startMap(final String name) {
		stack.push(new NamedAttrNode(name));
	}

	@Override
	public void mapStartEntry(final String key) {
		stack.push(new AttrMapEntryNode(key));
	}

	@Override
	public void mapEndEntry(final String key) {
		stack.pop();
	}

	@Override
	public void endMap(final String name) {
		stack.pop();
	}

}
