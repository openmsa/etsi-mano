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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.RequirementDeserialization;

@JsonDeserialize(using = RequirementDeserialization.class)
public class RequirementDefinition {
	Map<String, Requirement> requirements;

	public RequirementDefinition(final Map<String, Requirement> reqMap) {
		requirements = reqMap;
	}

	public Map<String, Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(final Map<String, Requirement> requirements) {
		this.requirements = requirements;
	}

}
