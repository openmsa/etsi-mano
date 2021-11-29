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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.parser.tosca.constraints.Constraint;

public class DataType extends ToscaBasePropertiesEntity {
	private List<Constraint> constraints;
	// 1.3
	@JsonProperty("key_schema")
	private Object keySchema;
	// 1.3
	@JsonProperty("entry_schema")
	private Object entrySchema;

	public List<Constraint> getConstraints() {
		return constraints;
	}

	public void setConstraints(final List<Constraint> constraints) {
		this.constraints = constraints;
	}

	public Object getKeySchema() {
		return keySchema;
	}

	public void setKeySchema(final Object keySchema) {
		this.keySchema = keySchema;
	}

	public Object getEntrySchema() {
		return entrySchema;
	}

	public void setEntrySchema(final Object entrySchema) {
		this.entrySchema = entrySchema;
	}

}
