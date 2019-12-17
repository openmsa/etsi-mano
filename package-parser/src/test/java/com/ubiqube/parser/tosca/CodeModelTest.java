package com.ubiqube.parser.tosca;

import com.ubiqube.parser.tosca.generator.JavaGenerator;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CodeModelTest {
	private static final Logger LOG = LoggerFactory.getLogger(CodeModelTest.class);

	@Test
	void testName() throws Exception {
        LOG.debug("Testing Java Generator.");
		final JavaGenerator jg = new JavaGenerator();
		// src/test/resources/web_mysql_tosca.yaml
		jg.generate("src/test/resources/web_mysql_tosca.yaml");
	}
}
