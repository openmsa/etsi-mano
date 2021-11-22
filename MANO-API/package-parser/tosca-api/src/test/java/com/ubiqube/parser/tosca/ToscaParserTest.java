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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.ubiqube.parser.tosca.api.ContextResolver;
import com.ubiqube.parser.tosca.api.ToscaApi;

public class ToscaParserTest {

	@Test
	void testName() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/web_mysql_tosca.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final ToscaApi api = new ToscaApi();
		api.getObjects(root, new HashMap<>(), String.class);
		final ContextResolver ctx = new ContextResolver(root, new HashMap<String, String>());
		final List<PolicyDefinition> policies = new ArrayList<>();
		policies.add(new PolicyDefinition());
		ctx.resolvValue("", String.class);
		ctx.mapPoliciesToClass(policies, Bean.class);
	}

	@Test
	void testName2() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/tosca-vnffgd-sample.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

	@Test
	void testName3() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/tacker_nfv_defs.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

	@Test
	void testName4() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/TOSCA_mec_definition_1_0_0.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

	@Test
	void testName5() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/TOSCA_nfv_definition_1_0_0.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

	@Test
	void testName6() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/etsi_nfv_sol001_nsd_types.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

	@Test
	void testName7() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/etsi_nfv_sol001_pnfd_types.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

	@Test
	void testName8() throws Exception {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/etsi_nfv_sol001_vnfd_types.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}
}
