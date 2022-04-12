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

import java.util.Optional;
import java.util.function.Consumer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.ubiqube.parser.tosca.ParseException;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public final class DeserializerHelper {
	private DeserializerHelper() {
		// Nothing.
	}

	public static <U> void fillIfNeeded(final JsonParser p, final JsonNode node, final String element, final Class<U> clazz, final Consumer<U> setter) {
		Optional.ofNullable(node.findValue(element)).ifPresent(x -> {
			final U obj = getObject(p, x, clazz);
			setter.accept(obj);
		});
	}

	public static <U> U getObject(final JsonParser root, final TreeNode x, final Class<U> clazz) {
		try {
			return root.getCodec().treeToValue(x, clazz);
		} catch (final JsonProcessingException e) {
			throw new ParseException(e);
		}
	}
}
