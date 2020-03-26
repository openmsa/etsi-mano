package com.ubiqube.parser.tosca;

import org.junit.jupiter.api.Test;

public class ToscaParserTest {

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/web_mysql_tosca.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName2() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/tosca-vnffgd-sample.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName3() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/tacker_nfv_defs.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName4() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/TOSCA_mec_definition_1_0_0.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName5() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/TOSCA_nfv_definition_1_0_0.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName6() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/etsi_nfv_sol001_nsd_types.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName7() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/etsi_nfv_sol001_pnfd_types.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}

	@Test
	void testName8() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/etsi_nfv_sol001_vnfd_types.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}
}
