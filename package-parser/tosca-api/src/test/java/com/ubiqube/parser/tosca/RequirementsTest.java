package com.ubiqube.parser.tosca;

import org.junit.jupiter.api.Test;

public class RequirementsTest {

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser("src/test/resources/requirements.yaml");
		final ToscaContext root = tp.getContext();
		System.out.println("" + root);
	}
}
