package com.ubiqube.parser.tosca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @doc 3.7.1 Entity Type Schema
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaBaseEntity {

	private String derivedFrom;
	private String version;
	private Object metadata;
	private String description;

	@JsonProperty("derived_from")
	public final String getDerivedFrom() {
		return derivedFrom;
	}

	public final void setDerivedFrom(final String derivedFrom) {
		this.derivedFrom = derivedFrom;
	}

	public final String getVersion() {
		return version;
	}

	public final void setVersion(final String version) {
		this.version = version;
	}

	public final Object getMetadata() {
		return metadata;
	}

	public final void setMetadata(final Object metadata) {
		this.metadata = metadata;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\tToscaBaseEntity [");
		sb.append("\t\tderived_from: ").append(derivedFrom).append("\n");
		if (null != description) {
			sb.append("\t\tdescription: ").append(description).append("\n");
		}
		if (null != version) {
			sb.append("\t\tversion: ").append(version).append("\n");
		}
		if (null != metadata) {
			sb.append("metadata=" + metadata + ", ");
		}
		sb.append("]\n");
		return super.toString();
	}

}
