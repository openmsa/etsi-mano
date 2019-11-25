package com.ubiqube.etsi.mano.mapper;

public class ListAttrNode implements AttrNode {

	private final String name;

	public ListAttrNode(final String _name) {
		name = _name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return " " + name + " ";
	}

}
