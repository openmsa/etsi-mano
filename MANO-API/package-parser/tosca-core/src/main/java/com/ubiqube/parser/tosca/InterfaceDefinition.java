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
import com.fasterxml.jackson.annotation.JsonProperty;

public class InterfaceDefinition {
	@JsonAnySetter
	private Map<String, OperationDefinition> operations;
	@JsonProperty("derived_from")
	private String derivedFrom;
	private String description;

	public Map<String, OperationDefinition> getOperations() {
		return operations;
	}

	public void setOperations(final Map<String, OperationDefinition> operations) {
		this.operations = operations;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getDerivedFrom() {
		return derivedFrom;
	}

	public void setDerivedFrom(final String derivedFrom) {
		this.derivedFrom = derivedFrom;
	}

}
