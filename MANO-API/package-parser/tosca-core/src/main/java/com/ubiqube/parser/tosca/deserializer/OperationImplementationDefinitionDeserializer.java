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
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.ubiqube.parser.tosca.OperationImplementationDefinition;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OperationImplementationDefinitionDeserializer extends StdDeserializer<OperationImplementationDefinition> {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(OperationImplementationDefinitionDeserializer.class);

	protected OperationImplementationDefinitionDeserializer() {
		this(null);
	}

	public OperationImplementationDefinitionDeserializer(final Class<?> object) {
		super(object);
	}

	@Override
	public OperationImplementationDefinition deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
		final JsonNode value = p.getCodec().readTree(p);
		LOG.debug("value {}<=>{}", value.getClass(), value);
		if (value.isTextual()) {
			final TextNode t = (TextNode) value;
			final OperationImplementationDefinition r = new OperationImplementationDefinition();
			r.setPrimary(t.asText());
			return r;
		}
		final OperationImplementationDefinition r = new OperationImplementationDefinition();
		Optional.ofNullable(value.findValue("primary")).ifPresent(x -> r.setPrimary(x.asText()));
		DeserializerHelper.fillIfNeeded(p, value, "dependencies", List.class, r::setDependencies);
		Optional.ofNullable(value.findValue("timeout")).ifPresent(x -> r.setTimeout(x.asInt()));
		Optional.ofNullable(value.findValue("operation_host")).ifPresent(x -> r.setOperationHost(x.asText()));
		return r;
	}

}
