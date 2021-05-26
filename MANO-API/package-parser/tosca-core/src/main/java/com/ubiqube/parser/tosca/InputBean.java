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

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputBean {

	private String type;
	private String def;

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@JsonProperty("default")
	public String getDef() {
		return def;
	}

	public void setDef(final String def) {
		this.def = def;
	}

	@Override
	public String toString() {
		return "InputBean [type=" + type + ", def=" + def + "]";
	}

}
