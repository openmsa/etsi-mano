package com.ubiqube.parser.tosca;

import java.util.List;

public class EntrySchema {
	private String type;
	private String description;
	private List<Object> constraints;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<Object> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Object> constraints) {
		this.constraints = constraints;
	}

}
