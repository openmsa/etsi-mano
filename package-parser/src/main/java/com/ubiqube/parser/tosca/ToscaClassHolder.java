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

	public boolean isInstanceOf(final String clazz) {
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
