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
package com.ubiqube.etsi.mano.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.mapper.JsonWalker;
import com.ubiqube.etsi.mano.mapper.SpelWriter;
import com.ubiqube.etsi.mano.test.TestTools;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

class SpelParserTest {
	private final DefaultMapperFactory mapperFactory;

	public SpelParserTest() {
		mapperFactory = new DefaultMapperFactory.Builder().build();
	}

	@Test
	void testComplexPatch() throws Exception {
		final ObjectMapper _mapper = new ObjectMapper();
		final MapperFacade _mapperFacade = mapperFactory.getMapperFacade();
		final SpelWriter _spelWriter = new SpelWriter(_mapperFacade);
		final JsonWalker _jsonWalker = new JsonWalker(_mapper);
		final SpelPatcher sp = new SpelPatcher(_mapper, _jsonWalker, _spelWriter);
		final VnfPkgInfo vpi = new VnfPkgInfo();
		final byte[] bytes = TestTools.readFile("/json-patch01.json");
		sp.patch(new String(bytes), vpi);
		assertEquals("ENABLED", vpi.getOperationalState().toString());
	}
}
