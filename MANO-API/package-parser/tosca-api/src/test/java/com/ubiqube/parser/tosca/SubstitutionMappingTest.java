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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ContextResolver;
import com.ubiqube.parser.tosca.api.ToscaApi;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SubstitutionMappingTest {

	@Test
	void testSubstitutionMapping() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/substitution-mapping.yml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		ToscaApi.getObjects(root, new HashMap<>(), String.class);
		final ContextResolver ctx = new ContextResolver(root, new HashMap<String, String>());
		ctx.resolvValue("");
	}
}
