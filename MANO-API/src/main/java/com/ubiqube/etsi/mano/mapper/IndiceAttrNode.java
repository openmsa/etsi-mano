package com.ubiqube.etsi.mano.mapper;

public class IndiceAttrNode implements AttrNode {

	private final int i;

	public IndiceAttrNode(final int _i) {
		i = _i;
	}

	public int getI() {
		return i;
	}

	@Override
	public String toString() {
		return "[" + i + "]";
	}

}
