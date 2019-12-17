package com.ubiqube.etsi.mano.mapper;

public class NamedAttrNode implements AttrNode {

	private final String name;

	public NamedAttrNode(final String _name) {
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
