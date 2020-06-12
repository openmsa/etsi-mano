package com.ubiqube.parser.tosca;

import org.junit.jupiter.api.Test;

public class ToscaParserTest {

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaRoot root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		System.out.println("" + root);
	}

	@Test
	void testName2() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaRoot root = tp.parse("src/test/resources/custom_types.yaml");
		System.out.println("" + root);
	}
}
