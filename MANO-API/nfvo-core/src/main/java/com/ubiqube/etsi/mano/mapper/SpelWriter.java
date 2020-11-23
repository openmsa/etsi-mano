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

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.FilterAttributes;
import com.ubiqube.etsi.mano.exception.GenericException;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;

@Service
public class SpelWriter {
	private final MapperFacade mapper;

	public SpelWriter(final MapperFacade _mapperFacade) {
		super();
		mapper = _mapperFacade;
	}

	public List<FilterAttributes> getFilterAttrs(final List<AttrHolder> attrs) {
		return attrs.stream()
				.map(this::handle)
				.collect(Collectors.toList());
	}

	private FilterAttributes handle(final AttrHolder attrHolder) {
		final StringBuilder sb = new StringBuilder();
		final Deque<AttrNode> stack = attrHolder.getStack();
		AttrNode prev = null;
		final Iterator<AttrNode> ite = stack.descendingIterator();
		while (ite.hasNext()) {
			final AttrNode elem = ite.next();
			final String res = handleElement(elem, prev);
			sb.append(res);
			prev = elem;
		}
		final FilterAttributes filterAttributes = new FilterAttributes();
		filterAttributes.setAttribute(sb.toString());
		if (null != attrHolder.getValue()) {
			filterAttributes.setValue(mapper.convert(attrHolder.getValue(), String.class, null, new MappingContext(new HashMap<>())));
		}

		return filterAttributes;
	}

	private static String handleElement(final AttrNode elem, final AttrNode prev) {
		if (elem instanceof NamedAttrNode) {
			if (prev == null) {
				return ((NamedAttrNode) elem).getName();
			}
			// XXX Is there another way to adress map.
			if (prev instanceof NamedAttrNode) {
				final NamedAttrNode previous = (NamedAttrNode) prev;
				if ("userDefinedData".equals(previous.getName())) {
					return '[' + ((NamedAttrNode) elem).getName() + ']';
				}
				return '.' + ((NamedAttrNode) elem).getName();
			}
			return '.' + ((NamedAttrNode) elem).getName();
		} else if (elem instanceof IndiceAttrNode) {
			return elem.toString();
		} else if (elem instanceof ListAttrNode) {
			if (prev == null) {
				return ((ListAttrNode) elem).getName();
			}
			return '.' + ((ListAttrNode) elem).getName();
		} else if (elem instanceof AttrMapEntryNode) {
			return '[' + ((AttrMapEntryNode) elem).getName() + ']';
		} else {
			throw new GenericException("Unknown Node instance: " + elem.getClass());
		}
	}
}
