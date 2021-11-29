/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.tosca;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.CapabilityDeserializer;

@JsonDeserialize(using = CapabilityDeserializer.class)
public class CapabilityDefinition {
	private String type;
	private String description;
	private ToscaProperties properties;
	private Map<String, ValueObject> attributes;
	@JsonProperty("valid_source_types")
	private List<String> validSourceTypes;
	private Occurrences occurrences;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
	}

	public Map<String, ValueObject> getAttributes() {
		return attributes;
	}

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
	}

	public List<String> getValidSourceTypes() {
		return validSourceTypes;
	}

	public void setValidSourceTypes(final List<String> validSourceTypes) {
		this.validSourceTypes = validSourceTypes;
	}

	public Occurrences getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(final Occurrences occurrences) {
		this.occurrences = occurrences;
	}

}
