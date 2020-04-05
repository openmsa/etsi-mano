package com.ubiqube.parser.tosca;

import java.util.List;

import com.ubiqube.parser.tosca.constraints.Constraint;

public class EntrySchema {
	private String type;
	private String description;
	private List<Constraint> constraints;

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

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Constraint> constraints) {
		this.constraints = constraints;
	}

}
