package com.ubiqube.parser.tosca;

import org.junit.jupiter.api.Test;

public class LoadDefaultTest {

	@Test
	void testName() throws Exception {
		final LoadDefault def = new LoadDefault();
		final ToscaRoot root = def.load();
		def.resolvProperties(root.getNodeTypes());
		def.resolvProperties(root.getArtifactTypes());
		def.resolvProperties(root.getCapabilityTypes());
		def.resolvProperties(root.getRelationshipTypes());
		System.out.println("" + root);
	}
}
