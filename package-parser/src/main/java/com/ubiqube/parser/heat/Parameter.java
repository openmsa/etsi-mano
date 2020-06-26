package com.ubiqube.parser.heat;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parameter {
	private String type;
	private String label;
	private String description;
	private boolean hidden;
	private boolean immutable;
	private String def;
	private List<Object> constraints = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(final boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isImmutable() {
		return immutable;
	}

	public void setImmutable(final boolean immutable) {
		this.immutable = immutable;
	}

	@JsonProperty("default")
	public String getDef() {
		return def;
	}

	public void setDef(final String def) {
		this.def = def;
	}

	public List<Object> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Object> constraints) {
		this.constraints = constraints;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Class Parameter {\n");
		sb.append("    type=").append(type).append("\n");
		sb.append("    label=").append(label).append("\n");
		sb.append("    description=").append(description).append("\n");
		sb.append("    hidden=").append(hidden).append("\n");
		sb.append("    immutable=").append(immutable).append("\n");
		sb.append("    default=").append(def).append("\n");
		sb.append("    constraints=").append(toIndentedString(constraints)).append("\n");
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
