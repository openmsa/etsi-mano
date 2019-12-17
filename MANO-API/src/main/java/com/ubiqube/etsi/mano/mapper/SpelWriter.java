package com.ubiqube.etsi.mano.mapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
		final LinkedList<AttrNode> stack = attrHolder.getStack();
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
			return '.' + ((NamedAttrNode) elem).getName();
		} else if (elem instanceof IndiceAttrNode) {
			return elem.toString();
		} else if (elem instanceof ListAttrNode) {
			if (prev == null) {
				return ((ListAttrNode) elem).getName();
			}
			return '.' + ((ListAttrNode) elem).getName();
		} else {
			throw new GenericException("Unknown Node instance: " + elem.getClass());
		}
	}
}
