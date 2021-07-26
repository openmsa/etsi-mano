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
package com.ubiqube.parser.heat;

import java.util.List;
import java.util.Map;

public class Resource {
	private String type;
	private Map<String, Object> properties;
	private List<String> dependsOn;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public List<String> getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(final List<String> dependsOn) {
		this.dependsOn = dependsOn;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(final Map<String, Object> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Class Resource {\n");
		sb.append("    type=").append(type).append("\n");
		sb.append("    label=").append(toIndentedString(dependsOn)).append("\n");
		sb.append("    constraints=").append(toIndentedString(properties)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private static String toIndentedString(final Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
