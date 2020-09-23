package com.ubiqube.parser.tosca.constraints;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class InRange implements Constraint {

	private String min;
	private String max;

	public InRange(final ArrayNode key) {
		min = key.get(0).asText();
		max = key.get(1).asText();
	}

	public String getMin() {
		return min;
	}

	public void setMin(final String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(final String max) {
		this.max = max;
	}

}
