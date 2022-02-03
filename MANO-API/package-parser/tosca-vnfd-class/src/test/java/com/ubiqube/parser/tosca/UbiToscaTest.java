/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.parser.tosca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.ZipUtil.Entry;
import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.nodes.nfv.VnfExtCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;
import tosca.policies.nfv.VduScalingAspectDeltas;
import tosca.policies.nfv.VnfIndicator;

class UbiToscaTest {
	private final Map<String, String> parameters = new HashMap<>();

	@Test
	void testUbiCsar() throws Exception {
		ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar", Entry.of("ubi-tosca/Definitions/tosca_ubi.yaml", "Definitions/tosca_ubi.yaml"),
				Entry.of("ubi-tosca/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		final ToscaParser toscaParser = new ToscaParser(new File("/tmp/ubi-tosca.csar"));
		final ToscaContext root = toscaParser.getContext();

		final List<VnfVirtualLink> list = ToscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		assertEquals(3, list.size());
		final VnfVirtualLink elem = list.get(0);
		assertEquals("leftVl01", elem.getInternalName());
		assertEquals("192.168.0.100", elem.getVlProfile().getVirtualLinkProtocolData().get(0).getL3ProtocolData().getIpAllocationPools().get(0).getStartIpAddress());

		final List<VnfIndicator> l2 = ToscaApi.getObjects(root, parameters, VnfIndicator.class);
		assertEquals(2, l2.size());
	}

	@Test
	void testUbiCsarCompute() throws Exception {
		ZipUtil.makeToscaZip("/tmp/ubi-tosca.csar", Entry.of("ubi-tosca/Definitions/tosca_ubi.yaml", "Definitions/tosca_ubi.yaml"),
				Entry.of("ubi-tosca/TOSCA-Metadata/TOSCA.meta", "TOSCA-Metadata/TOSCA.meta"));
		final ToscaParser toscaParser = new ToscaParser(new File("/tmp/ubi-tosca.csar"));
		final ToscaContext root = toscaParser.getContext();

		final List<Compute> list = ToscaApi.getObjects(root, parameters, Compute.class);
		System.out.println("" + list);
		final List<VnfExtCp> extCp = ToscaApi.getObjects(root, parameters, VnfExtCp.class);
		System.out.println("" + extCp);
		final List<VduScalingAspectDeltas> vsad = ToscaApi.getObjects(root, parameters, VduScalingAspectDeltas.class);
		System.out.println("vsad " + vsad);
		assertNotNull(vsad);
	}
}
