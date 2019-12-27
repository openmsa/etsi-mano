package com.ubiqube.parser.tosca;

public class ToscaClassHolder {
	private ToscaClassHolder parent;
	private final ToscaClass node;
	private final String name;

	public ToscaClassHolder(final String _name, final ToscaClass _node) {
		node = _node;
		name = _name;
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

	public boolean isInstanceOf(final String clazz) {
		if (name.equals(clazz)) {
			return true;
		}
		if (null == node.getDerivedFrom()) {
			return false;
		}
		if (node.getDerivedFrom().equals(clazz)) {
			return true;
		}
		if (parent != null) {
			return parent.isInstanceOf(clazz);
		}
		return false;
	}
}
