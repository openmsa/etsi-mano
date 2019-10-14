package com.ubiqube.parser.tosca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaClass {

	private ToscaProperties properties;
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
	// Used in relation ship only
	private List<String> valid_target_types;
	private Object credential;

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
	public ToscaProperties getProperties() {
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

	public void setProperties(final ToscaProperties properties) {
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

	public List<String> getValid_target_types() {
		return valid_target_types;
	}

	public void setValid_target_types(final List<String> valid_target_types) {
		this.valid_target_types = valid_target_types;
	}

	public Object getCredential() {
		return credential;
	}

	public void setCredential(final Object credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return "ToscaClass [properties=" + properties + ", derivedFrom=" + derivedFrom + ", attributes=" + attributes + ", description=" + description + ", mimeType=" + mimeType + ", fileExt=" + fileExt + ", requirements=" + requirements + ", interfaces=" + interfaces + ", capabilities=" + capabilities + ", artifacts=" + artifacts + ", metadata=" + metadata + ", valid_target_types=" + valid_target_types + ", credential=" + credential + "]";
	}

}
