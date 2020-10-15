package com.ubiqube.etsi.mano.mapper;

import java.util.Deque;

public class AttrHolder {
	private Deque<AttrNode> stack;
	private Object value;

	public Deque<AttrNode> getStack() {
		return stack;
	}

	public void setStack(final Deque<AttrNode> stack) {
		this.stack = stack;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(final Object value) {
		this.value = value;
	}

}
