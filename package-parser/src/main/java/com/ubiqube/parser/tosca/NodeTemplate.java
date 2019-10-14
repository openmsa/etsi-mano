package com.ubiqube.parser.tosca;

import java.util.Map;

public class NodeTemplate {
	private String type;
	private String description;
	private Object capabilities;
	private Map<String, Object> properties;
	private Object requirements;
	private Object attributes;
	private Object artifacts;
	private Object interfaces;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Object getRequirements() {
		return requirements;
	}

	public void setRequirements(final Object requirements) {
		this.requirements = requirements;
	}

	public Object getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(final Object capabilities) {
		this.capabilities = capabilities;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
		this.properties = properties;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(final Object attributes) {
		this.attributes = attributes;
	}

	public Object getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final Object artifacts) {
		this.artifacts = artifacts;
	}

	public Object getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Object interfaces) {
		this.interfaces = interfaces;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
