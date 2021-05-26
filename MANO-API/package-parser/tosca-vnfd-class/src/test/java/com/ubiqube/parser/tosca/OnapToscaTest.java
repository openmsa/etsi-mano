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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.nodes.nfv.VduCp;
import tosca.nodes.nfv.VnfExtCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;
import tosca.policies.nfv.VduScalingAspectDeltas;

public class OnapToscaTest {
	private final Map<String, String> parameters = new HashMap<>();

	@Test
	void testInfra() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/infra.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<VnfVirtualLink> list = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		assertEquals(2, list.size());
		final VnfVirtualLink elem = list.get(0);
		assertEquals("VL_cpe_signal", elem.getInternalName());
	}

	@Test
	void testVbng() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/vbng.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		System.out.println("" + list);
		final List<VnfExtCp> extCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		System.out.println("" + extCp);
		final List<VnfVirtualLink> vl = toscaApi.getObjects(root, parameters, VnfVirtualLink.class);
		System.out.println("" + vl);
		final List<VduCp> vduCp = toscaApi.getObjects(root, parameters, VduCp.class);
		System.out.println("" + vduCp);
	}

	@Test
	void testVbrgemu() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/vbrgemu.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		System.out.println("" + list);
		final List<VnfExtCp> extCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		System.out.println("" + extCp);
		final List<VduScalingAspectDeltas> vsad = toscaApi.getObjects(root, parameters, VduScalingAspectDeltas.class);
		System.out.println("vsad " + vsad);
	}

	@Test
	void testVgmux() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/vgmux.csar");
		final ToscaContext root = toscaParser.getContext();
		final ToscaApi toscaApi = new ToscaApi();

		final List<Compute> list = toscaApi.getObjects(root, parameters, Compute.class);
		System.out.println("" + list);
		final List<VnfExtCp> extCp = toscaApi.getObjects(root, parameters, VnfExtCp.class);
		System.out.println("" + extCp);
		final List<VduScalingAspectDeltas> vsad = toscaApi.getObjects(root, parameters, VduScalingAspectDeltas.class);
		System.out.println("vsad " + vsad);
	}

	@Test
	void testVgw() throws Exception {
		final ToscaParser toscaParser = new ToscaParser("src/test/resources/vgw.csar");
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
