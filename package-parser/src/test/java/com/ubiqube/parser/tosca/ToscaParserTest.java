package com.ubiqube.parser.tosca;

import org.junit.jupiter.api.Test;

public class ToscaParserTest {

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaContext root = tp.parse("src/test/resources/web_mysql_tosca.yaml");
		System.out.println("" + root);
	}

	@Test
	void testName2() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaContext root = tp.parse("src/test/resources/tosca-vnffgd-sample.yaml");
		System.out.println("" + root);
	}

	@Test
	void testName3() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaContext root = tp.parse("src/test/resources/tacker_nfv_defs.yaml");
		System.out.println("" + root);
	}

	@Test
	void testName4() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaContext root = tp.parse("src/test/resources/TOSCA_mec_definition_1_0_0.yaml");
		System.out.println("" + root);
	}

	@Test
	void testName5() throws Exception {
		final ToscaParser tp = new ToscaParser();
		final ToscaContext root = tp.parse("src/test/resources/TOSCA_nfv_definition_1_0_0.yaml");
		System.out.println("" + root);
	}
}
