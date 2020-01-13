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

	@Override
	public void mapValue(final Object source) {
		final AttrMapHolder amh = new AttrMapHolder();
		amh.setStack((LinkedList<AttrNode>) stack.clone());
		amh.setValue(source);
		if (null != source) {
			attrs.add(amh);
		}
	}

}
