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

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.OperationDeserializer;

@JsonDeserialize(using = OperationDeserializer.class)
public class OperationDefinition {

	private String description;
	private Object implementation;
	private Map<String, Object> inputs;
	private String type;

	@JsonAnySetter
	private Map<String, JsonNode> operations;

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Object getImplementation() {
		return implementation;
	}

	public void setImplementation(final Object implementation) {
		this.implementation = implementation;
	}

	public Map<String, Object> getInputs() {
		return inputs;
	}

	public void setInputs(final Map<String, Object> inputs) {
		this.inputs = inputs;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public Map<String, JsonNode> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, JsonNode> operations) {
		this.operations = operations;
	}

}
