package com.ubiqube.parser.tosca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaClass {

	private Object properties;
	private String derivedFrom;
	private Object attributes;
	private String description;
	private String mimeType;
	private List<String> fileExt;
	private Object requirements;
	private Object interfaces;

	private Object capabilities;
	private Object artifacts;
	private Object metadata;

	public void setDerivedFrom(final String _derivedFrom) {
		derivedFrom = _derivedFrom;
	}

	public void setDescription(final String _description) {
		description = _description;
	}

	public void setMimeType(final String _mimeType) {
		mimeType = _mimeType;
	}

	public void setFileExt(final List<String> _fileExt) {
		fileExt = _fileExt;
	}

	public void setRequirement(final Object _requirements) {
		requirements = _requirements;
	}

	public void setCapabilities(final Object _capabilities) {
		capabilities = _capabilities;

	}

	public void setArtifacts(final Object _artifacts) {
		artifacts = _artifacts;
	}

	public Object getRequirements() {
		return requirements;
	}

	public void setRequirements(final Object requirements) {
		this.requirements = requirements;
	}

	@JsonProperty("properties")
	public Object getProperties() {
		return properties;
	}

	@JsonProperty("derived_from")
	public String getDerivedFrom() {
		return derivedFrom;
	}

	public Object getAttributes() {
		return attributes;
	}

	public String getDescription() {
		return description;
	}

	@JsonProperty("mime_type")
	public String getMimeType() {
		return mimeType;
	}

	@JsonProperty("file_ext")
	public List<String> getFileExt() {
		return fileExt;
	}

	public Object getCapabilities() {
		return capabilities;
	}

	public Object getArtifacts() {
		return artifacts;
	}

	public Object getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Object interfaces) {
		this.interfaces = interfaces;
	}

	public void setProperties(final Object properties) {
		this.properties = properties;
	}

	public void setAttributes(final Object attributes) {
		this.attributes = attributes;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(final Object metadata) {
		this.metadata = metadata;
	}

}
