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

public class RelationshipType extends ToscaBaseEntity {
	private ToscaProperties properties;
	private Map<String, ValueObject> attributes;
	private Object interfaces;
	@JsonProperty("valid_target_types")
	private List<String> validTargetTypes;

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

	public Object getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Object interfaces) {
		this.interfaces = interfaces;
	}

	public List<String> getValidTargetTypes() {
		return validTargetTypes;
	}

	public void setValidTargetTypes(final List<String> validTargetTypes) {
		this.validTargetTypes = validTargetTypes;
	}

}
