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
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.ubiqube.parser.tosca.api.ContextResolver;
import com.ubiqube.parser.tosca.api.ToscaApi;

class ToscaParserTest {

	/// TODO remote URL use some tosca 1.0 @Test
	@SuppressWarnings("static-method")
	void testName() {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/web_mysql_tosca.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		ToscaApi.getObjects(root, new HashMap<>(), String.class);
		final ContextResolver ctx = new ContextResolver(root, new HashMap<String, String>());
		final List<PolicyDefinition> policies = new ArrayList<>();
		policies.add(new PolicyDefinition());
		ctx.resolvValue("");
		ctx.mapPoliciesToClass(policies, Bean.class);
	}

	@Test
	void testToscaResolver() {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/ubi-tosca/Definitions/tosca_ubi.yaml"));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
		final ContextResolver ctx = new ContextResolver(root, new HashMap<String, String>());
		final List<GroupDefinition> groups = getGroups();
		ctx.mapGroupsToClass(groups, Bean.class);
		final List<PolicyDefinition> policies = getPolicies();
		ctx.mapPoliciesToClass(policies, Bean.class);
		final List<NodeTemplate> nodes = gestNodes();
		ctx.mapToscaToClass(nodes, Bean.class);
	}

	private List<NodeTemplate> gestNodes() {
		final List<NodeTemplate> nodes = new ArrayList<>();
		final NodeTemplate nt = new NodeTemplate();
		final Map<String, Artifact> artifacts = new HashMap<>();
		nt.setArtifacts(artifacts);
		final Map<String, ValueObject> attributes = new HashMap<>();
		nt.setAttributes(attributes);
		final Object capabilities = new HashMap<>();
		nt.setCapabilities(capabilities);
		nt.setDescription("descr");
		final Map<String, InterfaceType> interfaces = new HashMap<>();
		nt.setInterfaces(interfaces);
		nt.setName("name");
		final Map<String, Object> properties = new HashMap<>();
		nt.setProperties(properties);
		final RequirementDefinition requirements = getRequirement();
		nt.setRequirements(requirements);
		nt.setType("unk");
		nodes.add(nt);
		return nodes;
	}

	private RequirementDefinition getRequirement() {
		final Map<String, Requirement> reqMap = new HashMap<>();
		return new RequirementDefinition(reqMap);
	}

	private static List<PolicyDefinition> getPolicies() {
		final List<PolicyDefinition> policies = new ArrayList<>();
		final PolicyDefinition pd = new PolicyDefinition();
		pd.setDescription("descr");
		final Map<String, String> metadata = new HashMap<>();
		pd.setMetadata(metadata);
		pd.setName("name");
		final Map<String, Object> properties = new HashMap<>();
		pd.setProperties(properties);
		final List<String> targets = new ArrayList<>();
		pd.setTargets(targets);
		final Map<String, TriggerDefinition> triggers = new HashMap<>();
		pd.setTriggers(triggers);
		pd.setType("unk");
		policies.add(pd);
		return policies;
	}

	private static List<GroupDefinition> getGroups() {
		final List<GroupDefinition> groups = new ArrayList<>();
		final GroupDefinition gd = new GroupDefinition();
		gd.setDescription("descr");
		final List<String> members = new ArrayList<>();
		gd.setMembers(members);
		gd.setName("name");
		final Map<String, Object> properties = new HashMap<>();
		gd.setProperties(properties);
		gd.setType("com.ubiqube.groups.Root");
		groups.add(gd);
		return groups;
	}

	@SuppressWarnings("static-method")
	@ParameterizedTest
	@ValueSource(strings = {
			"src/test/resources/tosca-vnffgd-sample.yaml",
			"src/test/resources/tacker_nfv_defs.yaml",
			"src/test/resources/TOSCA_mec_definition_1_0_0.yaml",
			"src/test/resources/TOSCA_nfv_definition_1_0_0.yaml",
			"src/test/resources/etsi_nfv_sol001_nsd_types.yaml",
			"src/test/resources/etsi_nfv_sol001_pnfd_types.yaml",
			"src/test/resources/etsi_nfv_sol001_vnfd_types.yaml"
	})
	void parametrizedTest(final String file) {
		final ToscaParser tp = new ToscaParser(new File(file));
		final ToscaContext root = tp.getContext();
		assertNotNull(root);
	}

}
