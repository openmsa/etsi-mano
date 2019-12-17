package com.ubiqube.etsi.mano.mapper;

import java.util.LinkedList;

public class AttrHolder {
	private LinkedList<AttrNode> stack;
	private Object value;

	public LinkedList<AttrNode> getStack() {
		return stack;
	}

	public void setStack(final LinkedList<AttrNode> stack) {
		this.stack = stack;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

}
