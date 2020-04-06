package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.nodes.nfv.VnfVirtualLink;

public class UbiToscaTest {

	@Test
	void testUbiCsar() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/ubi-tosca.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<VnfVirtualLink> list = toscaApi.getObjects(root, VnfVirtualLink.class);
		assertEquals(1, list.size());
		final VnfVirtualLink elem = list.get(0);
		assertEquals("vl01", elem.getInternalName());
	}
}
