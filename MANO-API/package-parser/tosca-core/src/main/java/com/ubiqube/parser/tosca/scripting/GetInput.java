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
package com.ubiqube.parser.tosca.scripting;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.ubiqube.parser.tosca.ParseException;

public class GetInput extends ScriptingValue {

	private List<String> properties = new ArrayList<>();

	public GetInput(final JsonNode value) {
		if (value.isTextual()) {
			properties.add(value.asText());
		} else {
			throw new ParseException("Unable to parse GetInput value : " + value);
		}
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(final List<String> properties) {
		this.properties = properties;
	}

}
