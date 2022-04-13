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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class TopologyTemplate {

	private Map<String, InputBean> inputs = new LinkedHashMap<>();
	@JsonProperty("node_templates")
	private Map<String, NodeTemplate> nodeTemplate = new LinkedHashMap<>();
	private Map<String, GroupDefinition> groups = new LinkedHashMap<>();
	@JsonProperty("substitution_mappings")
	private SubstitutionMapping substitutionMapping;
	private Map<String, PolicyDefinition> policies;

	@Override
	public String toString() {
		return "TopologyTemplate [inputs=" + inputs + ", nodeTemplate=" + nodeTemplate + "]";
	}

	public void putAll(final TopologyTemplate topologyTemplate) {
		nodeTemplate.putAll(topologyTemplate.getNodeTemplate());
		groups.putAll(topologyTemplate.getGroups());
		Optional.ofNullable(topologyTemplate.getSubstitutionMapping()).ifPresent(x -> this.substitutionMapping = x);
	}

}
