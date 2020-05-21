package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.nodes.nfv.VnfExtCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;
import tosca.policies.nfv.VduScalingAspectDeltas;

public class UbiToscaTest {
	private final Map<String, String> parameters = new HashMap<>();

	@Test
	void testUbiCsar() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/ubi-tosca.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<VnfVirtualLink> list = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
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

		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		System.out.println("" + list);
		final List<VnfExtCp> extCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		System.out.println("" + extCp);
		final List<VduScalingAspectDeltas> vsad = toscaApi.getObjects(root, parameters, VduScalingAspectDeltas.class);
		System.out.println("vsad " + vsad);
	}
}
