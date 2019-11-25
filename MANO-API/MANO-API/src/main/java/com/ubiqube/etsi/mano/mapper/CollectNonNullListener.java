package com.ubiqube.etsi.mano.mapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectNonNullListener implements BeanListener {
	final LinkedList<AttrNode> stack = new LinkedList<>();
	List<AttrHolder> attrs = new ArrayList<>();

	@Override
	public void addProperty(final Object source) {
		final AttrHolder ah = new AttrHolder();
		ah.setStack((LinkedList<AttrNode>) stack.clone());
		ah.setValue(source);
		attrs.add(ah);
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

}
