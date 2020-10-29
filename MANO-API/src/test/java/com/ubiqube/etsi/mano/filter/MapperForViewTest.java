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
package com.ubiqube.etsi.mano.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.json.MapperForView;

public class MapperForViewTest {
	@Test
	void simpleExcludeTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final String exclude = "_links";
		final String fields = null;
		final ObjectMapper mapper = MapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vnfPkgInfo);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
	}

	@Test
	void multiExcludeTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final String exclude = "_links,checksum.algorithm";
		final String fields = null;
		final ObjectMapper mapper = MapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vnfPkgInfo);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
		assertEquals(-1, res.indexOf("algorithm"), "algorithm should not been found");
	}

	@Test
	void simpleFieldsTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final String exclude = null;
		final String fields = "id";
		final ObjectMapper mapper = MapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerFor(vnfPkgInfo.getClass()).writeValueAsString(vnfPkgInfo);
		System.out.println(res);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
		assertTrue(res.indexOf("\"id\"") > -1, "Could not find id field.");
	}

	@Test
	void multipleFieldsTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final String exclude = null;
		final String fields = "id,checksum.algorithm";
		final ObjectMapper mapper = MapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerFor(vnfPkgInfo.getClass()).writeValueAsString(vnfPkgInfo);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
		assertTrue(res.indexOf("\"id\"") > -1, "Could not find id field.");
		assertTrue(res.indexOf("\"algorithm\"") > -1, "Could not find id field.");
	}
}
