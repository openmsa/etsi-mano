package com.ubiqube.parser.heat;

import java.util.List;
import java.util.Map;

public class Resource {
	private String type;
	private Map<String, Object> properties;
	private List<String> dependsOn;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public List<String> getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(final List<String> dependsOn) {
		this.dependsOn = dependsOn;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Class Resource {\n");
		sb.append("    type=").append(type).append("\n");
		sb.append("    label=").append(toIndentedString(dependsOn)).append("\n");
		sb.append("    constraints=").append(toIndentedString(properties)).append("\n");
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
