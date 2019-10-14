package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToscaClass extends ToscaBaseEntity {

	private ToscaProperties properties;
	private Map<String, ValueObject> attributes;
	private String mimeType;
	private List<String> fileExt;
	private RequirementDefinition requirements;
	private Object interfaces;

	private Map<String, CapabilityDefinition> capabilities;
	private Map<String, Artifact> artifacts;
	// Used in relation ship only
	private List<String> valid_target_types;
	private Object credential;

	public void setMimeType(final String _mimeType) {
		mimeType = _mimeType;
	}

	public void setFileExt(final List<String> _fileExt) {
		fileExt = _fileExt;
	}

	public void setRequirement(final RequirementDefinition _requirements) {
		requirements = _requirements;
	}

	public void setCapabilities(final Map<String, CapabilityDefinition> _capabilities) {
		capabilities = _capabilities;

	}

	public void setArtifacts(final Map<String, Artifact> _artifacts) {
		artifacts = _artifacts;
	}

	public Object getRequirements() {
		return requirements;
	}

	public void setRequirements(final RequirementDefinition requirements) {
		this.requirements = requirements;
	}

	@JsonProperty("properties")
	public ToscaProperties getProperties() {
		return properties;
	}

	@JsonProperty("mime_type")
	public String getMimeType() {
		return mimeType;
	}

	@JsonProperty("file_ext")
	public List<String> getFileExt() {
		return fileExt;
	}

	public Map<String, CapabilityDefinition> getCapabilities() {
		return capabilities;
	}

	public Map<String, Artifact> getArtifacts() {
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

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
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
		final StringBuilder sb = new StringBuilder();
		sb.append("ToscaClass [");
		sb.append(super.toString());
		if (null != properties) {
			sb.append("properties=" + properties + ", ");
		}

		if (null != attributes) {
			sb.append(", attributes=" + attributes + ", ");
		}

		if (null != mimeType) {
			sb.append("mimeType=" + mimeType + ", ");
		}
		if (null != fileExt) {
			sb.append("fileExt=" + fileExt + ", ");
		}
		if (null != requirements) {
			sb.append("requirements=" + requirements + ", ");
		}
		if (null != interfaces) {
			sb.append("interfaces=" + interfaces + ", ");
		}
		if (null != capabilities) {
			sb.append("capabilities=" + capabilities + ", ");
		}
		if (null != artifacts) {
			sb.append("artifacts=" + artifacts + ", ");
		}

		if (null != valid_target_types) {
			sb.append("valid_target_types=" + valid_target_types + ", ");
		}
		if (null != credential) {
			sb.append("credential=" + credential).append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

}
