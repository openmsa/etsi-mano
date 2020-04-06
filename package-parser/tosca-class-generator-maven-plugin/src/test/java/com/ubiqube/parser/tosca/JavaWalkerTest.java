package com.ubiqube.parser.tosca;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.generator.JavaWalker;
import com.ubiqube.parser.tosca.generator.ToscaWalker;

public class JavaWalkerTest {

	@Test
	void testName() throws Exception {
		final JavaWalker jw = new JavaWalker("target/tmp");
		final ToscaWalker tw = new ToscaWalker();
		tw.generate("src/test/resources/etsi_nfv_sol001_vnfd_types.yaml", jw);
	}
}
