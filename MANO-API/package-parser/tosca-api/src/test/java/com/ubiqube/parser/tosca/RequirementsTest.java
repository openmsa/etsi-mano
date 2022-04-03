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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.parser.tosca.api.ToscaApi;

import tosca.groups.nfv.PlacementGroup;
import tosca.nodes.Compute;
import tosca.policies.nfv.AffinityRule;

@SuppressWarnings("static-method")
class RequirementsTest {

	private static final Logger LOG = LoggerFactory.getLogger(RequirementsTest.class);

	@Test
	void testTrue() {
		assertTrue(true);
	}

	@Test
	void testName() {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/requirements.yaml"));
		final ToscaContext root = tp.getContext();
		final List<Compute> cp = ToscaApi.getObjects(root, null, Compute.class);
		assertEquals(1, cp.size());
		final Compute elem0 = cp.get(0);
		final List<String> localStorages = elem0.getLocalStorageReq();
		assertNotNull(localStorages);
		assertEquals(2, localStorages.size());
	}

	@Test
	void testPoliciesGroups() {
		final ToscaParser tp = new ToscaParser(new File("src/test/resources/policy-group.yml"));
		final ToscaContext root = tp.getContext();
		final List<PlacementGroup> cp = ToscaApi.getObjects(root, null, PlacementGroup.class);
		assertEquals(1, cp.size());
		final List<AffinityRule> cp1 = ToscaApi.getObjects(root, null, AffinityRule.class);
		assertEquals(1, cp1.size());
		LOG.info("{}", root.toString());
	}
}
