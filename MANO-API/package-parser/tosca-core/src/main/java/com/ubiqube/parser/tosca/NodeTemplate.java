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

import java.util.Map;

public class NodeTemplate implements ToscaBase {
	private String type;
	private String name;
	private String description;
	private Object capabilities;
	private Map<String, Object> properties;
	private RequirementDefinition requirements;
	private Map<String, ValueObject> attributes;
	private Map<String, Artifact> artifacts;
	private Map<String, InterfaceType> interfaces;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public RequirementDefinition getRequirements() {
		return requirements;
	}

	public void setRequirements(final RequirementDefinition requirements) {
		this.requirements = requirements;
	}

	public Object getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(final Object capabilities) {
		this.capabilities = capabilities;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
		this.properties = properties;
	}

	public Map<String, ValueObject> getAttributes() {
		return attributes;
	}

	public void setAttributes(final Map<String, ValueObject> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Artifact> getArtifacts() {
		return artifacts;
	}

	public void setArtifacts(final Map<String, Artifact> artifacts) {
		this.artifacts = artifacts;
	}

	public Map<String, InterfaceType> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(final Map<String, InterfaceType> interfaces) {
		this.interfaces = interfaces;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
