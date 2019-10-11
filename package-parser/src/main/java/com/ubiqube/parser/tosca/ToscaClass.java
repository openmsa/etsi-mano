package com.ubiqube.parser.tosca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.yaml.snakeyaml.introspector.Property;

public class ToscaClass {

	private Object properties = new ArrayList<>();
	private String derivedFrom;
	private Object attributes = new ArrayList<>();
	private String description;
	private String mimeType;
	private List<String> fileExt = new ArrayList<>();
	private Object requirements = new HashMap<>();
	private Object capabilities = new HashMap<>();
	private Object artifacts = new HashMap<>();
	private String name;
	private ToscaClass derived;

	public void setProperties(final List<Property> _properties) {
		properties = _properties;
	}

	public void setDerivedFrom(final String _derivedFrom) {
		derivedFrom = _derivedFrom;
	}

	public void setAttributes(final List<Property> _attributes) {
		attributes = _attributes;
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

	public Object getProperties() {
		return properties;
	}

	public String getDerivedFrom() {
		return derivedFrom;
	}

	public Object getAttributes() {
		return attributes;
	}

	public String getDescription() {
		return description;
	}

	public String getMimeType() {
		return mimeType;
	}

	public List<String> getFileExt() {
		return fileExt;
	}

	public Object getCapabilities() {
		return capabilities;
	}

	public Object getArtifacts() {
		return artifacts;
	}

	public void setName(final String _name) {
		name = _name;
	}

	public String getName() {
		return name;
	}

	public void setDerived(final ToscaClass _derived) {
		derived = _derived;
	}

	public ToscaClass getDerived() {
		return derived;
	}
}
