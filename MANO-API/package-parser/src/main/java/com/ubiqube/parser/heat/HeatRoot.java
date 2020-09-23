package com.ubiqube.parser.heat;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeatRoot {

	private String version;
	private String description;
	private final Map<String, Parameter> parameters = new HashMap<>();
	private final Map<String, Resource> resources = new HashMap<>();
	private final Map<String, Output> outputs = new HashMap<>();

	@JsonProperty("heat_template_version")
	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	public Map<String, Resource> getResources() {
		return resources;
	}

	public Map<String, Output> getOutputs() {
		return outputs;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Class HeatRoot {\n");
		sb.append("    version=" + version + "\n");
		sb.append("    description=" + description + "\n");
		sb.append("    parameters=" + toIndentedString(parameters) + "\n");
		sb.append("    resources=" + toIndentedString(resources) + "\n");
		sb.append("    outputs=" + outputs + "]\n");
		sb.append("}");
		return sb.toString();
	}

	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
