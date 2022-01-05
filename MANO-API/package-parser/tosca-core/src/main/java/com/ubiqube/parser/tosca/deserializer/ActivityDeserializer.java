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
package com.ubiqube.parser.tosca.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubiqube.parser.tosca.ActivityListDefinition;
import com.ubiqube.parser.tosca.CallActivityDelegateDefinition;
import com.ubiqube.parser.tosca.CallActivityOperationDefinition;
import com.ubiqube.parser.tosca.ParseException;

public class ActivityDeserializer extends StdDeserializer<ActivityListDefinition> {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	public ActivityDeserializer() {
		this(null);
	}

	protected ActivityDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public ActivityListDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
		final ObjectNode value = p.getCodec().readTree(p);
		if (value.has("delegate")) {
			final JsonNode v = value.get("delegate");
			if (v.isTextual()) {
				return new ActivityListDefinition(new CallActivityDelegateDefinition(v.textValue()));
			}
			return new ActivityListDefinition(p.getCodec().treeToValue(v, CallActivityDelegateDefinition.class));
		}
		if (value.has("call_operation")) {
			final JsonNode v = value.get("call_operation");
			if (v.isTextual()) {
				return new ActivityListDefinition(new CallActivityOperationDefinition(v.textValue()));
			}
			return new ActivityListDefinition(p.getCodec().treeToValue(v, CallActivityOperationDefinition.class));
		}
		throw new ParseException("Unknow activity type: " + value);
	}

}
