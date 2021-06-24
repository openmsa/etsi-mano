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
package com.ubiqube.etsi.mano.service.graph;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.ListenableGraph;
import org.junit.jupiter.api.Test;

import com.ubiqube.etsi.mano.orchestrator.nodes.Node;
import com.ubiqube.etsi.mano.orchestrator.nodes.NodeConnectivity;
import com.ubiqube.etsi.mano.service.graph.contributors.ComputeCont;
import com.ubiqube.etsi.mano.service.graph.contributors.NetworkCont;
import com.ubiqube.etsi.mano.service.graph.uow.CompTestUow;
import com.ubiqube.etsi.mano.service.graph.uow.VlUow;
import com.ubiqube.etsi.mano.service.graph.wfe2.WfConfiguration;
import com.ubiqube.etsi.mano.service.plan.contributors.PlanContributor;

public class WfConfigurationTest {

	@Test
	void testBlankCall01() throws Exception {
		final List<? extends PlanContributor> planContributors = new ArrayList<>();
		final WfConfiguration wfc = new WfConfiguration(planContributors);
		final ListenableGraph<Class<? extends Node>, NodeConnectivity> g = wfc.getConfigurationGraph();
		assertNotNull(g);
		final List uows = new ArrayList<>();
		wfc.autoConnect(uows);
	}

	@Test
	void testBlankCall02() throws Exception {
		final List planContributors = new ArrayList<>();
		planContributors.add(new NetworkCont());
		final WfConfiguration wfc = new WfConfiguration(planContributors);
		final ListenableGraph<Class<? extends Node>, NodeConnectivity> g = wfc.getConfigurationGraph();
		assertNotNull(g);
		final List uows = new ArrayList<>();
		uows.add(new VlUow());
		wfc.autoConnect(uows);
	}

	@Test
	void testBlankCall03() throws Exception {
		final List planContributors = new ArrayList<>();
		planContributors.add(new NetworkCont());
		planContributors.add(new ComputeCont());
		final WfConfiguration wfc = new WfConfiguration(planContributors);
		final ListenableGraph<Class<? extends Node>, NodeConnectivity> g = wfc.getConfigurationGraph();
		assertNotNull(g);
		System.out.println("" + g);
		final List uows = new ArrayList<>();
		uows.add(new VlUow());
		uows.add(new CompTestUow());
		wfc.autoConnect(uows);
	}
}
