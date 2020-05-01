package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;

public class UbiToscaTest {

	@Test
	void testUbiCsar() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/ubi-tosca.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<VnfVirtualLink> list = toscaApi.getObjects(root, VnfVirtualLink.class);
		assertEquals(3, list.size());
		final VnfVirtualLink elem = list.get(0);
		assertEquals("leftVl01", elem.getInternalName());
		assertEquals("192.168.0.100", elem.getVlProfile().getVirtualLinkProtocolData().get(0).getL3ProtocolData().getIpAllocationPools().get(0).getStartIpAddress());
	}

	@Test
	void testUbiCsarCompute() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/ubi-tosca.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<Compute> list = toscaApi.getObjects(root, Compute.class);
		System.out.println("" + list);
	}
}
