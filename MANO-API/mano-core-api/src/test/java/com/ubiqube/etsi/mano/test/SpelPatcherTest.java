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
package com.ubiqube.etsi.mano.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.mapper.JsonWalker;
import com.ubiqube.etsi.mano.mapper.SpelWriter;
import com.ubiqube.etsi.mano.service.SpelPatcher;

import ma.glasnost.orika.impl.DefaultMapperFactory;

public class SpelPatcherTest {

	@Test
	void testName() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final JsonWalker jsonWalker = new JsonWalker(mapper);
		final DefaultMapperFactory mapperFacade = new DefaultMapperFactory.Builder().build();
		final SpelWriter spelWriter = new SpelWriter(mapperFacade.getMapperFacade());
		final SpelPatcher sp = new SpelPatcher(mapper, jsonWalker, spelWriter);
		final TestVim vim = new TestVim();
		sp.patch("""
				{
					"accessInfo": {
						"username": "admin55"
					},
					"geoloc": {
						"lng": 45.75801,
						"lat": 4.8001016
					},
					"basicList": [ "a", "b", "c" ],
					"advList": [
						{
							"lng": 45.75801,
							"lat": 4.8001016
						}
					]
				}
				""", vim);
		assertNotNull(vim.getAccessInfo());
		assertEquals("admin55", vim.getAccessInfo().get("username"));
		assertEquals(45.75801, vim.getGeoloc().getLng());
		assertNotNull(vim.getBasicList());
		assertEquals(3, vim.getBasicList().size());
		assertEquals(1, vim.getAdvList().size());
		assertEquals(4.8001016, vim.getAdvList().get(0).getLat());
	}

	@Test
	void testSpel() {
		final SpelParserConfiguration config = new SpelParserConfiguration(true, true); // auto create objects if null
		final ExpressionParser parser = new SpelExpressionParser(config);
		final VimConnectionInformation entity = new VimConnectionInformation();
		final StandardEvaluationContext modelContext = new StandardEvaluationContext(entity);
		parser.parseExpression("accessInfo[username]").setValue(modelContext, "hello!!!");
		assertNotNull(entity.getAccessInfo());
		assertEquals("hello!!!", entity.getAccessInfo().get("username"));
	}

}
