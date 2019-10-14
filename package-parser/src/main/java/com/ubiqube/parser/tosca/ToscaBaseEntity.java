package com.ubiqube.parser.tosca;

/**
 * @doc 3.7.1 Entity Type Schema
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ToscaBaseEntity {

	private String derived_from;
	private String version;
	private Object metadata;
	private String description;

	public final String getDerived_from() {
		return derived_from;
	}

	public final void setDerived_from(final String derived_from) {
		this.derived_from = derived_from;
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

}
