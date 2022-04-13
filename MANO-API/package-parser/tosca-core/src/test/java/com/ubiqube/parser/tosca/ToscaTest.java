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

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
class ToscaTest {
	private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	@Test
	void testName() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/etsi_nfv_sol001_common_types.yaml")) {
			final ToscaRoot obj = mapper.readValue(is, ToscaRoot.class);
			assertNotNull(obj);
		}
	}

	@Test
	void testDefinition13() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/TOSCA_definition_1_3.yaml")) {
			final ToscaRoot obj = mapper.readValue(is, ToscaRoot.class);
			assertNotNull(obj);
		}
	}

	@Test
	void testTosca() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/tosca-test.yaml")) {
			final ToscaRoot obj = mapper.readValue(is, ToscaRoot.class);
			assertNotNull(obj);
		}
	}

	@Test
	void testTosca2() throws Exception {
		try (InputStream is = getClass().getResourceAsStream("/tosca-13-full.yaml")) {
			final ToscaRoot obj = mapper.readValue(is, ToscaRoot.class);
			assertNotNull(obj);
			final SubstitutionMapping sm = obj.getTopologyTemplate().getSubstitutionMapping();
			assertNotNull(sm);
		}
	}
}
