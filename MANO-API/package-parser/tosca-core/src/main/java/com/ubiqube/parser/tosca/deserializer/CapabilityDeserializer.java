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
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.ubiqube.parser.tosca.CapabilityDefinition;
import com.ubiqube.parser.tosca.Occurrences;
import com.ubiqube.parser.tosca.ParseException;
import com.ubiqube.parser.tosca.ToscaProperties;
import com.ubiqube.parser.tosca.ValueObject;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class CapabilityDeserializer extends StdDeserializer<CapabilityDefinition> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	public CapabilityDeserializer() {
		this(null);
	}

	public CapabilityDeserializer(final Class<?> vc) {
		super(vc);
	}

	@Override
	public CapabilityDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
		final TreeNode value = p.getCodec().readTree(p);
		if (value instanceof TextNode) {
			final CapabilityDefinition cap = new CapabilityDefinition();
			cap.setType(value.toString());
			return cap;
		}
		// return p.getCodec().treeToValue(value, CapabilityDefinition.class);
		final CapabilityDefinition caps = new CapabilityDefinition();
		Optional.ofNullable(value.get("attributes")).map(x -> getTreeValue(p, x, new TypeReference<Map<String, ValueObject>>() {/**/
		})).ifPresent(caps::setAttributes);
		Optional.ofNullable(value.get("description")).map(TreeNode::toString).ifPresent(caps::setDescription);
		Optional.ofNullable(value.get("occurrences")).map(x -> getTreeValue(p, x, Occurrences.class)).ifPresent(caps::setOccurrences);
		Optional.ofNullable(value.get("properties")).map(x -> getTreeValue(p, x, ToscaProperties.class)).ifPresent(caps::setProperties);
		Optional.ofNullable(value.get("type")).map(TreeNode::toString).ifPresent(caps::setType);
		Optional.ofNullable(value.get("valid_source_types")).map(x -> getTreeValue(p, x, new TypeReference<List<String>>() {/**/
		})).ifPresent(caps::setValid_source_types);
		return caps;
	}

	private static <T> T getTreeValue(final JsonParser p, final TreeNode t, final Class<T> clazz) {
		try {
			return p.getCodec().treeToValue(t, clazz);
		} catch (final JsonProcessingException e) {
			throw new ParseException(e);
		}
	}

	private static <T> T getTreeValue(final JsonParser p, final TreeNode t, final TypeReference<T> clazz) {
		final ObjectCodec c = p.getCodec();
		try (final JsonParser p2 = c.treeAsTokens(t)) {
			return c.readValue(p2, clazz);
		} catch (final IOException e) {
			throw new ParseException(e);
		}
	}
}
