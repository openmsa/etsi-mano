package com.ubiqube.parser.tosca;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.csar.CsarParser;

public class CsarTest {

	@Test
	void testGetFiles() throws Exception {
		final CsarParser csar = new CsarParser("src/test/resources/csar_elk.csar");
		final List<?> list = csar.getFiles();
	}
}
