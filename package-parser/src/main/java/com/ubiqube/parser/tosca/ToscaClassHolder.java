package com.ubiqube.parser.tosca;

public class ToscaClassHolder {

	private ToscaClassHolder parent;
	private final ToscaClass node;

	public ToscaClassHolder(final ToscaClass _node) {
		node = _node;
	}

	public ToscaClassHolder getParent() {
		return parent;
	}

	public void setParent(final ToscaClassHolder parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ToscaClassHolder [parent=" + parent + ", node=" + node + "]\n";
	}

}
