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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ubiqube.parser.tosca.deserializer.RequirementMappingDeserializer;

import lombok.Getter;
import lombok.Setter;

/**
 * 3.8.13 Substitution mapping
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class SubstitutionMapping {

	@JsonProperty("node_type")
	private String nodeType;
	@JsonProperty("substitution_filter")
	private NodeFilter substitutionFilter;

	private String properties;

	private CapabilityMapping capabilities;

	@JsonDeserialize(using = RequirementMappingDeserializer.class)
	private Map<String, RequirementMapping> requirements;

	private AttributeMapping attributes;

	private InterfaceMapping interfaces;

}
