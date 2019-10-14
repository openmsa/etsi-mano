package com.ubiqube.parser.tosca;

import java.util.List;

public class GroupDefinition {
	private String type;
	private String description;
	private Object metadata;
	private Object properties;
	private List<String> members;

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

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(final Object metadata) {
		this.metadata = metadata;
	}

	public Object getProperties() {
		return properties;
	}

	public void setProperties(final Object properties) {
		this.properties = properties;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(final List<String> members) {
		this.members = members;
	}
}
