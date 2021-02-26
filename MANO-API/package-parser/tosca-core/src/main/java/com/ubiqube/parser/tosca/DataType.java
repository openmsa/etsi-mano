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

import com.ubiqube.parser.tosca.constraints.Constraint;

public class DataType extends ToscaBaseEntity {
	private List<Constraint> constraints;
	private ToscaProperties properties;
	// 1.3
	private Object key_schema;
	// 1.3
	private Object entry_schema;

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Constraint> constraints) {
		this.constraints = constraints;
	}

	public ToscaProperties getProperties() {
		return properties;
	}

	public void setProperties(final ToscaProperties properties) {
		this.properties = properties;
	}

	public Object getKey_schema() {
		return key_schema;
	}

	public void setKey_schema(final Object key_schema) {
		this.key_schema = key_schema;
	}

	public Object getEntry_schema() {
		return entry_schema;
	}

	public void setEntry_schema(final Object entry_schema) {
		this.entry_schema = entry_schema;
	}

}
