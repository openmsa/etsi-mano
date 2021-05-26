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

public class Requirement {
	private String description;
	private String capability;
	private String node;
	// XXX: Could be an object? See tosca_elk.csar
	private String relationship;
	private List<String> occurrences;

	public String getCapability() {
		return capability;
	}

	public void setCapability(final String capability) {
		this.capability = capability;
	}

	public String getNode() {
		return node;
	}

	public void setNode(final String node) {
		this.node = node;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(final String relationship) {
		this.relationship = relationship;
	}

	public List<String> getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(final List<String> occurrences) {
		this.occurrences = occurrences;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
