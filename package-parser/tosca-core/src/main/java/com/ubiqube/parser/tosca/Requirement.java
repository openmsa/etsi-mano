package com.ubiqube.parser.tosca;

import java.util.List;

public class Requirement {
	private String description;
	private String capability;
	private String node;
	// XXX: Could be an object? See tosca_elk.csar
	private String relationship;
	private List<String> occurrences;

	public String getCapability() {
		return capability;
	}

	public void setCapability(final String capability) {
		this.capability = capability;
	}

	public String getNode() {
		return node;
	}

	public void setNode(final String node) {
		this.node = node;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(final String relationship) {
		this.relationship = relationship;
	}

	public List<String> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(final List<String> occurrences) {
		this.occurrences = occurrences;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
