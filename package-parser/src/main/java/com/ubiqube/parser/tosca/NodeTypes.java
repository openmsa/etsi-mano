package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeTypes {
	private ToscaProperties properties;
	private String derived_from;
	private Object attributes;
	private String description;
	private String mime_type;
	private Object file_ext;
	private Object requirements;
	private Object interfaces;
	private Object capabilities;
	private Object artifacts;

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
	}

	@JsonProperty("derived_from")
	public String getDerivedFrom() {
		return derived_from;
	}

	public void setDerivedFrom(final String derived_from) {
		this.derived_from = derived_from;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(final Object attributes) {
		this.attributes = attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(final String mime_type) {
		this.mime_type = mime_type;
	}

	public Object getFile_ext() {
		return file_ext;
	}

	public void setFile_ext(final Object file_ext) {
		this.file_ext = file_ext;
	}

	public Object getRequirements() {
		return requirements;
	}

	public void setRequirements(final Object requirements) {
		this.requirements = requirements;
	}

	public Object getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Object interfaces) {
		this.interfaces = interfaces;
	}

	public Object getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(final Object capabilities) {
		this.capabilities = capabilities;
	}

	public Object getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final Object artifacts) {
		this.artifacts = artifacts;
	}

}
