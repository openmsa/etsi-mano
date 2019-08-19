package com.ubiqube.etsi.mano.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

public class MapperForViewTest {
	@Test
	void simpleExcludeTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final MapperForView mapperForView = new MapperForView();
		final String exclude = "_links";
		final String fields = null;
		final ObjectMapper mapper = mapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vnfPkgInfo);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
	}

	@Test
	void multiExcludeTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final MapperForView mapperForView = new MapperForView();
		final String exclude = "_links,checksum.algorithm";
		final String fields = null;
		final ObjectMapper mapper = mapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vnfPkgInfo);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
		assertEquals(-1, res.indexOf("algorithm"), "algorithm should not been found");
	}

	@Test
	void simpleFieldsTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final MapperForView mapperForView = new MapperForView();
		final String exclude = null;
		final String fields = "id";
		final ObjectMapper mapper = mapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerFor(vnfPkgInfo.getClass()).writeValueAsString(vnfPkgInfo);
		System.out.println(res);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
		assertTrue(res.indexOf("\"id\"") > -1, "Could not find id field.");
	}

	@Test
	void multipleFieldsTest() throws Exception {
		final ObjectMapper desMapper = new ObjectMapper();
		final VnfPkgInfo vnfPkgInfo = desMapper.readValue(new FileInputStream("src/test/resources/VnfPkgInfo.json"), VnfPkgInfo.class);
		final MapperForView mapperForView = new MapperForView();
		final String exclude = null;
		final String fields = "id,checksum.algorithm";
		final ObjectMapper mapper = mapperForView.getMapperForView(exclude, fields, null, null);
		final String res = mapper.writerFor(vnfPkgInfo.getClass()).writeValueAsString(vnfPkgInfo);
		assertEquals(-1, res.indexOf("_links"), "_links should not been found");
		assertTrue(res.indexOf("\"id\"") > -1, "Could not find id field.");
		assertTrue(res.indexOf("\"algorithm\"") > -1, "Could not find id field.");
	}
}
